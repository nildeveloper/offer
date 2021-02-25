package com.lhl.boot.parallel;

//import com.alibaba.csp.sentinel.Entry;
//import com.alibaba.csp.sentinel.SphU;
//import com.alibaba.csp.sentinel.Tracer;
//import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.google.common.base.Stopwatch;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;
import java.util.function.BinaryOperator;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Slf4j
public class ParallelInvoker2 {

    private static final int DEFAULT_TIME_MILLISECONDS = 500;

    @SneakyThrows
    protected static <R, H extends AbstractFunctions> R execute(TaskWrapper<?>[] taskWrappers, H handler) {

        final Stopwatch stopwatch = Stopwatch.createStarted();

        Object[] argValues;
        try {
            // 使每个任务在独立线程获取任务结果, 并进行汇总, 由于希望任务子线程可以被追踪, 此处未使用 Stream.parallel()
            final CompletableFuture<?>[] completableFutures = Stream.of(taskWrappers)
                    .map(taskWrapper -> submitAsyncTask(() -> Try.ofSupplierWithThrow(
                            () -> submitAsyncTask(
                                    () -> sentinelWrapper(taskWrapper.getName(), taskWrapper.getExecutor())
                            ).get(DEFAULT_TIME_MILLISECONDS, TimeUnit.MILLISECONDS),
                            (t) -> taskWrapper.getDefaulter().get(t))))
                    .toArray(CompletableFuture<?>[]::new);
            log.info("ParallelInvoker2 submit tasks time: {}", stopwatch.elapsed(TimeUnit.MILLISECONDS));

            argValues = IntStream.range(0, taskWrappers.length)
                    .mapToObj(i -> Try.ofSupplierWithThrow(() -> completableFutures[i].get(), (t) -> taskWrappers[i].getDefaulter().get(t)))
                    .toArray();
            log.info("ParallelInvoker2 fetch tasks time: {}", stopwatch.elapsed(TimeUnit.MILLISECONDS));
        } catch (Throwable t) {
            argValues = Stream.of(taskWrappers)
                    .map(taskWrapper -> taskWrapper.getDefaulter().get(null))
                    .toArray();
            log.error("ParallelInvoker2 fetch tasks error: " + t.getMessage(), t);
        }
        // 使用反射执行业务处理
        final Class<?>[] argTypes = Arrays.stream(argValues).map(e -> Object.class).toArray(Class[]::new);
        final Method method = handler.getClass().getMethod("apply", argTypes);
        method.setAccessible(true);
        @SuppressWarnings("unchecked") final R result = (R) method.invoke(handler, argValues);
        log.info("ParallelInvoker2 invoke time: {}", stopwatch.elapsed(TimeUnit.MILLISECONDS));
        return result;
    }

    /**
     * 独立线程池执行子任务<br/>
     * 线程池被包装保证线程可追踪
     *
     * @param supplier 子任务
     * @param <R>      子任务结果类型
     * @return 子任务结果CompletableFuture包装类
     */
    private static <R> CompletableFuture<R> submitAsyncTask(Supplier<R> supplier) {
        return CompletableFuture.supplyAsync(supplier, ParallelThreadPool.getThreadPoolExecutor());
    }

    /**
     * 委托资源执行时受Sentinel监控、保护
     *
     * @param resource   资源标识
     * @param executable 被监控、受保护的业务逻辑
     * @param <R>        返回结果类型
     * @return 返回结果
     */
    @SneakyThrows
    protected static <R> R sentinelWrapper(String resource, Function0<R> executable) {
        R result = null;
//        Entry entry = null;
//
//        final Stopwatch stopwatch = Stopwatch.createStarted();
//        try {
//
//            // 开启资源，可使用任意有业务语义的字符串, 业务逻辑超时或异常, 下一次进入时会被拒绝
//            entry = SphU.entry(resource);
//            log.info("ParallelInvoker2 task: {}, entry time: {}", resource, stopwatch.elapsed(TimeUnit.MILLISECONDS));

            // 执行被保护的业务逻辑
            result = executable.apply();

//        } catch (BlockException e) {
//            //仅打印限流告警
//            throw e;
//        } catch (Throwable e) {
//            // 记录资源执行异常
//            Tracer.trace(e);
//        } finally {
//
//            // 务必保证finally会被执行, 释放资源
//            if (entry != null) {
//                entry.exit();
//            }
//        }
//        log.info("ParallelInvoker2 task: {}, execute time: {}", resource, stopwatch.elapsed(TimeUnit.MILLISECONDS));

        return result;

    }

    /**
     * 单任务包装对象<br/>
     * 注意: 如返回类型中存在泛型时, 初始化对象时需要明确指出泛型, 如: new TaskWrapper&lt;List&lt;Integer&gt;&gt;(...)
     *
     * @param <R> 任务返回类型
     */
    @Getter
    @AllArgsConstructor
    public static class TaskWrapper<R> {
        String name;

        Function0<? extends R> executor;

        Function1<? super Throwable, R> defaulter;

        public static <R> TaskWrapper<R> of(String name, Function0<R> executor, Function1<? super Throwable, R> defaulter) {
            return new TaskWrapper<>(name, executor, defaulter);
        }

    }

    public static <R, R1, R2> R execute(TaskWrapper<? extends R1> s1, TaskWrapper<? extends R2> s2, Function2<R1, R2, R> handler) {
        return execute(new TaskWrapper[]{s1, s2}, handler);
    }

    public static class Try {
        @SneakyThrows
        public static <R> R of(Function0<R> supplier) {
            return supplier.apply();
        }

        public static <R> R ofSupplier(Function0<R> supplier, R def) {
            try {
                return of(supplier);
            } catch (Throwable ignore) {
                return def;
            }
        }

        public static <R> R ofSupplierWithThrow(Function0<R> supplier, Function1<? super Throwable, R> def) {
            try {
                return of(supplier);
            } catch (Throwable t) {
                return def.get(t);
            }
        }
    }

    public interface AbstractFunctions {

    }

    public interface Function0<R> extends AbstractFunctions {

        R apply() throws Throwable;

        @SneakyThrows
        default R get() {
            return apply();
        }

    }

    public interface Function1<A1, R> extends AbstractFunctions {

        R apply(A1 a1) throws Throwable;

        @SneakyThrows
        default R get(A1 a1) {
            return apply(a1);
        }

    }

    public interface Function2<A1, A2, R> extends AbstractFunctions {

        R apply(A1 a1, A2 a2) throws Throwable;

        @SneakyThrows
        default R get(A1 a1, A2 a2) {
            return apply(a1, a2);
        }

        static <R> BinaryOperator<R> first() {
            return (r1, r2) -> r1;
        }

        static <R> BinaryOperator<R> last() {
            return (r1, r2) -> r2;
        }

    }


    private static void test1() {
        final Stopwatch stopwatch = Stopwatch.createStarted();
        final Integer execute = ParallelInvoker2.execute(
                new TaskWrapper<>(
                        "m1",
                        () -> {
                            LockSupport.parkNanos(TimeUnit.MILLISECONDS.toNanos(200));
                            return 1 + 1;
                        },
                        (t) -> 0),
                new TaskWrapper<>(
                        "m2",
                        () -> {
                            LockSupport.parkNanos(TimeUnit.MILLISECONDS.toNanos(100));
                            return "1";
                        },
                        (t) -> "0"),
                (a, b) -> a + Integer.parseInt(b)
        );

        log.info("==========>name: test execute time: " + stopwatch.elapsed(TimeUnit.MILLISECONDS) + "， value: " + execute);


    }

    public static void main(String[] args) {
        test1();
        System.exit(0);
    }

}
