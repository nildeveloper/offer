package com.lhl.boot.parallel;

//import com.alibaba.csp.sentinel.Entry;
//import com.alibaba.csp.sentinel.SphU;
//import com.alibaba.csp.sentinel.Tracer;
//import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
public class ParallelInvoker {

    protected static <R> CompletableFuture<R> asyncTask(String name, Function0<R> supplier) {
        return CompletableFuture.supplyAsync(() -> {
            NamedThreadFactory.setNamedThreadName(Thread.currentThread(), name);
            return supplier.getWithSneakyThrow();
        }, ParallelThreadPool.getThreadPoolExecutor());
    }

    @SuppressWarnings("unchecked")
    protected static <R> R invokeAbstractFunctions(AbstractFunctions handler, Object[] argValues) throws Throwable {
        final Class<?>[] argTypes = Arrays.stream(argValues).map(e -> Object.class).toArray(Class[]::new);
        final Method method = handler.getClass().getMethod("apply", argTypes);
        method.setAccessible(true);
        return (R) method.invoke(handler, argValues);
    }

    /**
     * 并行执行且同步等待，在handler中处理各任务结果，如果某个任务执行异常仅打印异常日志，任务结果返回null。推荐在各任务内部控制执行耗时
     *
     * @param name      资源名称，通常为类名#方法名
     * @param handler   结果处理逻辑，各任务返回执行结果
     * @param suppliers 待并行执行的任务
     * @param <R>       返回结果类型
     * @return 返回结果
     * @throws Throwable 执行异常
     */
    protected static <R> R executeTask(String name,
                                       AbstractFunctions handler,
                                       Function0<?>... suppliers) throws Throwable {
        return wrapper(name, () -> {

            final CompletableFuture<?>[] completableFutures = Arrays.stream(suppliers)
                    .map(s -> asyncTask(name, s))
                    .toArray(CompletableFuture[]::new);

            // 同步等待各任务执行结果
            CompletableFuture.allOf(completableFutures).join();

            final Object[] argValues = Arrays.stream(completableFutures).map(e -> {
                try {
                    return e.get();
                } catch (Throwable t) {
                    log.error(String.format("%s, %s execute error!", name, e), e);
                }
                return null;
            }).toArray(Object[]::new);

            return invokeAbstractFunctions(handler, argValues);
        });

    }


    protected static <R> R parallel(ParallelInvokerConfig config, AbstractFunctions handler,
                                    Function0<?>... suppliers) throws Throwable {
        return wrapper(config.getName(), () -> {

            final CompletableFuture<?>[] completableFutures = Arrays.stream(suppliers)
                    .map(s -> asyncTask(config.getName(), s))
                    .toArray(CompletableFuture[]::new);

            final Object[] values;

            if (ParallelInvokerConfig.Type.await == config.getType()) {
                // 同步等待各任务执行完毕，返回各任务执行结果
                CompletableFuture.allOf(completableFutures).join();


                values = IntStream.range(0, completableFutures.length)
                        .boxed()
                        .map(i -> getFromCompletableFuture(config, i, () -> completableFutures[i].get()))
                        .toArray();

            } else if (ParallelInvokerConfig.Type.timeout == config.getType()) {
                // 同步等待指定时间，返回各任务执行结果
                values = IntStream.range(0, completableFutures.length)
                        .boxed()
                        .map(i -> getFromCompletableFuture(config, i, () -> completableFutures[i].get(config.getTimeout(), TimeUnit.MILLISECONDS)))
                        .toArray();
            } else {
                // 手动处理
                values = completableFutures;
            }

            return invokeAbstractFunctions(handler, values);
        });

    }

    private static <T> T getFromCompletableFuture(ParallelInvokerConfig config, int index, Function0<T> func) {
        long t = System.currentTimeMillis();
        try {
            return func.apply();
        } catch (Throwable e) {
            log.error(String.format("%s lambda %s error execute:%s", config.getName(), index, (System.currentTimeMillis() - t)), e);
        }
        return null;
    }

    /**
     * 并行执行，在handler中处理并行任务{@link CompletableFuture}包装结果<BR/>
     * <p>
     * 当<b>await=true</b>时，推荐使用{@link ParallelInvoker#executeTask(String, AbstractFunctions, Function0[])}方法，只有在需要明确获取各个任务运行异常时才推荐使用此方法<BR/>
     * 当<b>await=true</b>时，仅在handler中返回{@link CompletableFuture}包装结果，各任务执行控制可以自由控制
     *
     * @param name      资源名称，通常为类名#方法名
     * @param await     是否同步返回并行任务结果
     * @param handler   结果处理逻辑，各任务返回执行的{@link CompletableFuture}包装结果
     * @param suppliers 待并行执行的任务
     * @param <R>       返回结果类型
     * @return 返回结果
     * @throws Throwable 执行异常
     */
    protected static <R> R submitTask(String name,
                                      boolean await,
                                      AbstractFunctions handler,
                                      Function0<?>... suppliers) throws Throwable {
        return wrapper(name, () -> {

            final CompletableFuture<?>[] completableFutures = Arrays.stream(suppliers)
                    .map(s -> asyncTask(name, s))
                    .toArray(CompletableFuture[]::new);

            if (await) {
                CompletableFuture.allOf(completableFutures).join();
            }

            return invokeAbstractFunctions(handler, completableFutures);
        });

    }


    /**
     * 委托资源执行时受Sentinel监控、保护
     *
     * @param name     资源名称
     * @param supplier 被监控、保护的业务逻辑
     * @param <R>      返回结果类型
     * @return 返回结果
     * @throws Throwable 执行异常
     */
    protected static <R> R wrapper(String name, Function0<R> supplier) throws Throwable {
//        Entry entry = null;
//        // 务必保证finally会被执行
//        try {
//            // 开启资源，可使用任意有业务语义的字符串, 业务逻辑超时或异常, 下一次进入时会被拒绝
//            entry = SphU.entry(name);
//
//            // 执行被保护的业务逻辑
//            {
                return supplier.apply();
//            }
//        } catch (BlockException e) {
//            throw e;
//        } catch (Throwable e) {
//            // 记录资源执行异常
//            Tracer.trace(e);
//            throw e;
//        } finally {
//            if (entry != null) {
//                entry.exit();
//            }
//        }

    }


    public static <R, R1, R2> R execute(ParallelInvokerConfig config, Function0<R1> s1, Function0<R2> s2, Function2<R1, R2, R> handler) throws Throwable {
        return parallel(config, handler, s1, s2);
    }

    public static <R, R1, R2, R3> R execute(ParallelInvokerConfig config, Function0<R1> s1, Function0<R2> s2, Function0<R3> s3, Function3<R1, R2, R3, R> handler) throws Throwable {
        return parallel(config, handler, s1, s2, s3);
    }

    public static <R, R1, R2, R3, R4, R5> R execute(ParallelInvokerConfig config, Function0<R1> s1, Function0<R2> s2, Function0<R3> s3, Function0<R4> s4, Function0<R5> s5, Function5<R1, R2, R3, R4, R5, R> handler) throws Throwable {
        return parallel(config, handler, s1, s2, s3, s4, s5);
    }

    public static <R, R1, R2, R3, R4, R5, R6> R execute(ParallelInvokerConfig config, Function0<R1> s1, Function0<R2> s2, Function0<R3> s3, Function0<R4> s4, Function0<R5> s5, Function0<R6> s6, Function6<R1, R2, R3, R4, R5, R6, R> handler) throws Throwable {
        return parallel(config, handler, s1, s2, s3, s4, s5, s6);
    }

    public static <R, R1, R2, R3, R4, R5, R6, R7, R8> R execute(ParallelInvokerConfig config, Function0<R1> s1, Function0<R2> s2, Function0<R3> s3, Function0<R4> s4, Function0<R5> s5, Function0<R6> s6, Function0<R7> s7, Function0<R8> s8, Function8<R1, R2, R3, R4, R5, R6, R7, R8, R> handler) throws Throwable {
        return parallel(config, handler, s1, s2, s3, s4, s5, s6, s7, s8);
    }

    public static <R, R1, R2, R3, R4, R5, R6, R7, R8, R9, R10, R11, R12> R execute(ParallelInvokerConfig config,
                                                                              Function0<R1> s1,
                                                                              Function0<R2> s2,
                                                                              Function0<R3> s3,
                                                                              Function0<R4> s4,
                                                                              Function0<R5> s5,
                                                                              Function0<R6> s6,
                                                                              Function0<R7> s7,
                                                                              Function0<R8> s8,
                                                                              Function0<R9> s9,
                                                                              Function0<R10> s10,
                                                                              Function0<R11> s11,
                                                                              Function0<R12> s12,
                                                                              Function12<R1, R2, R3, R4, R5, R6, R7, R8, R9, R10, R11, R12, R> handler) throws Throwable {

        return parallel(config, handler, s1, s2, s3, s4, s5, s6, s7, s8, s9, s10, s11, s12);
    }


    public static <S, R> List<R> parallel(String name, List<? extends S> list, Function2<Integer, S, R> handler) {
        if (list == null || list.isEmpty()) {
            return Collections.emptyList();
        }

        final List<CompletableFuture<R>> completableFutures = IntStream.range(0, list.size()).boxed()
                .map(i -> asyncTask(name, () -> handler.apply(i, list.get(i))))
                .collect(Collectors.toList());

        CompletableFuture.allOf(completableFutures.toArray(new CompletableFuture[list.size()]));

        return completableFutures.stream()
                .map(f -> ((Function0<R>) f::get).getWithSneakyThrow())
                .collect(Collectors.toList());
    }

    public interface AbstractFunctions {

        default <T extends Throwable> void sneakyThrow(T t) throws T {
            throw t;
        }

    }

    public static class Try {

        public static <R> R of(Function0<R> supplier, R def) {
            try {
                return supplier.getWithSneakyThrow();
            } catch (Throwable ignore) {
                return def;
            }
        }
    }

    public interface Function0<R> extends AbstractFunctions {

        R apply() throws Throwable;

        default R getWithSneakyThrow() {
            try {
                return apply();
            } catch (Throwable t) {
                sneakyThrow(new RuntimeException(t));
            }
            return null;
        }

        default R getQuietly() {
            try {
                return apply();
            } catch (Throwable t) {
                sneakyThrow(new RuntimeException(t));
            }
            return null;
        }


    }

    public interface Function1<A1, R> extends AbstractFunctions {
        R apply(A1 a1) throws Throwable;
    }

    public interface Function2<A1, A2, R> extends AbstractFunctions {
        R apply(A1 a1, A2 a2) throws Throwable;
    }

    public interface Function3<A1, A2, A3, R> extends AbstractFunctions {
        R apply(A1 a1, A2 a2, A3 a3) throws Throwable;
    }

    public interface Function4<A1, A2, A3, A4, R> extends AbstractFunctions {
        R apply(A1 a1, A2 a2, A3 a3, A4 a4) throws Throwable;
    }

    public interface Function5<A1, A2, A3, A4, A5, R> extends AbstractFunctions {
        R apply(A1 a1, A2 a2, A3 a3, A4 a4, A5 a5) throws Throwable;
    }

    public interface Function6<A1, A2, A3, A4, A5, A6, R> extends AbstractFunctions {
        R apply(A1 a1, A2 a2, A3 a3, A4 a4, A5 a5, A6 a6) throws Throwable;
    }

    public interface Function7<A1, A2, A3, A4, A5, A6, A7, R> extends AbstractFunctions {
        R apply(A1 a1, A2 a2, A3 a3, A4 a4, A5 a5, A6 a6, A7 a7) throws Throwable;
    }

    public interface Function8<A1, A2, A3, A4, A5, A6, A7, A8, R> extends AbstractFunctions {
        R apply(A1 a1, A2 a2, A3 a3, A4 a4, A5 a5, A6 a6, A7 a7, A8 a8) throws Throwable;
    }

    public interface Function9<A1, A2, A3, A4, A5, A6, A7, A8, A9, R> extends AbstractFunctions {
        R apply(A1 a1, A2 a2, A3 a3, A4 a4, A5 a5, A6 a6, A7 a7, A8 a8, A9 a9) throws Throwable;
    }

    public interface Function10<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, R> extends AbstractFunctions {
        R apply(A1 a1, A2 a2, A3 a3, A4 a4, A5 a5, A6 a6, A7 a7, A8 a8, A9 a9, A10 a10) throws Throwable;
    }

    public interface Function11<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, R> extends AbstractFunctions {
        R apply(A1 a1, A2 a2, A3 a3, A4 a4, A5 a5, A6 a6, A7 a7, A8 a8, A9 a9, A10 a10, A11 a11) throws Throwable;
    }

    public interface Function12<A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, R> extends AbstractFunctions {
        R apply(A1 a1, A2 a2, A3 a3, A4 a4, A5 a5, A6 a6, A7 a7, A8 a8, A9 a9, A10 a10, A11 a11, A12 a12) throws Throwable;
    }

}
