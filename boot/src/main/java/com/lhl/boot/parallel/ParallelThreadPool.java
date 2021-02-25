package com.lhl.boot.parallel;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

@Slf4j
public class ParallelThreadPool {
    private static int MAX_POOL_SIZE = 1000;
    private static ExecutorService threadPoolExecutor;
    private static ScheduledExecutorService parallelThreadPoolMonitor =
            Executors.newSingleThreadScheduledExecutor();
    private static final String PARALLEL_THREAD_POOL_NAME = "ParallelThreadPool";

    public static ExecutorService getThreadPoolExecutor() {
        if (threadPoolExecutor == null) {
            synchronized (ParallelThreadPool.class) {
                if (threadPoolExecutor == null) {
                    threadPoolExecutor = new ThreadPoolExecutor(
                            MAX_POOL_SIZE,
                            MAX_POOL_SIZE,
                            300,
                            TimeUnit.SECONDS,
                            new ArrayBlockingQueue<>(1000),
                            new NamedThreadFactory(PARALLEL_THREAD_POOL_NAME));
                    parallelThreadPoolMonitor.scheduleWithFixedDelay(monitorParallelThreadPool((ThreadPoolExecutor) threadPoolExecutor), 1, 1, TimeUnit.SECONDS);
                }
            }
        }
        return threadPoolExecutor;
    }

    public static void init(int poolSize) {
//        if (poolSize > 0) {
//            MAX_POOL_SIZE = poolSize;
//        }
//
//        CountDownLatch countDownLatch = new CountDownLatch(MAX_POOL_SIZE);
//
//        log.info("线程池开始预热");
//
//        IntStream.range(0, MAX_POOL_SIZE).boxed().forEach(i -> getThreadPoolExecutor().submit(() -> {
//            countDownLatch.countDown();
//            log.info(String.format("线程name=[%s] index=[%s] 预热中，占用当前工作线程", Thread.currentThread().getName(), i));
//            try {
//                boolean result = countDownLatch.await(60, TimeUnit.SECONDS);
//                log.info(String.format("线程name=[%s] index=[%s] 预热%s释放当前占用线程", Thread.currentThread().getName(), i, result ? "完毕" : "超时"));
//            } catch (InterruptedException e) {
//                log.error(String.format("线程name=[%s] index=[%s] 预热interrupted，释放当前占用线程", Thread.currentThread().getName(), i), e);
//            }
//        }));
//
//        try {
//            boolean result = countDownLatch.await(60, TimeUnit.SECONDS);
//            log.info(String.format("线程池预热完毕，主线程%s继续", result ? "" : "超时"));
//        } catch (InterruptedException e) {
//            log.error("线程池预热interrupted", e);
//        }
    }

    private static String info = "";

    private static Runnable monitorParallelThreadPool(ThreadPoolExecutor threadPoolExecutor) {
        return () -> {
            final String thisInfo = String.format("ParallelThreadPool: PoolSize=%s, ActiveTaskCount=%s, QueueTaskCount=%s",
                    threadPoolExecutor.getPoolSize(),
                    threadPoolExecutor.getActiveCount(),
                    threadPoolExecutor.getQueue().size()
            );
            if (!info.equals(thisInfo)) {
                log.info(info = thisInfo);
            }
        };
    }

}