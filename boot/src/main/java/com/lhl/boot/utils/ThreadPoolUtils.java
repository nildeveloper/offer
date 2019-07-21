package com.lhl.boot.utils;

import java.util.concurrent.*;

/**
 * Created with IntellJ IDEA.
 * User: lhl
 * Date: 2018-11-08
 * Time: 16:32
 * Description:
 */
public class ThreadPoolUtils {

    //工具类，构造方法私有化
    private ThreadPoolUtils() {
        System.out.println("禁止初始化！");
    }

    //根据cpu的数量动态的配置核心线程数和最大线程数
    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    // 线程池核心线程数
    private final static Integer COREPOOLSIZE = CPU_COUNT + 1;
    // 最大线程数
    private final static Integer MAXIMUMPOOLSIZE = CPU_COUNT * 2 + 1;
    // 空闲线程存活时间
    private final static Integer KEEPALIVETIME = 3 * 60;
    // 线程等待队列
    private static BlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(10);
    // 线程池对象
    private static ThreadPoolExecutor threadPool = new ThreadPoolExecutor(COREPOOLSIZE, MAXIMUMPOOLSIZE,
            KEEPALIVETIME, TimeUnit.SECONDS, queue, new ThreadPoolExecutor.AbortPolicy());;
    
    private static ExecutorService cachedThreadPool;
    private static ExecutorService fixedThreadPool;
    private static ScheduledExecutorService scheduledThreadPool;
    private static ExecutorService singleThreadPool;
    
    
    /**
     * 向线程池提交一个任务,返回线程结果
     * @param r
     * @return
     */
    public static Future<?> submit(Callable<?> r) {
        return threadPool.submit(r);
    }

    /**
     * 向线程池提交一个任务，不关心处理结果
     * @param r
     */
    public static void execute(Runnable r) {
        threadPool.execute(r);
    }

    /**
     * 获取当前线程池线程数量
     */
    public static int getSize() {
        return threadPool.getPoolSize();
    }

    /**
     * 获取当前活动的线程数量
     */
    public static int getActiveCount() {
        return threadPool.getActiveCount();
    }
    
    public static void shutdown() {
        threadPool.shutdown();
    }

    /**
     * cachedThreadPool
     * @param runnable
     * @return
     */
    public static ExecutorService cachedThreadPoolRun(Runnable runnable) {
        if (cachedThreadPool == null) cachedThreadPool = Executors.newCachedThreadPool();
        cachedThreadPool.submit(runnable);
        return cachedThreadPool;
    }

    /**
     * fixedThreadPool
     * @param runnable
     * @return
     */
    public static ExecutorService fixedThreadPoolRun(Runnable runnable) {
        if (fixedThreadPool == null)
            fixedThreadPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        fixedThreadPool.submit(runnable);
        return fixedThreadPool;
    }

    /**
     * scheduledThreadPool
     * @param runnable
     * @param delay
     * @param unit
     * @returns
     */
    public static ExecutorService scheduledThreadPoolRun(Runnable runnable, long delay, TimeUnit unit) {
        if (scheduledThreadPool == null)
            scheduledThreadPool = Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors());
        scheduledThreadPool.schedule(runnable, delay, unit);
        return scheduledThreadPool;
    }

    /**
     * singleThreadPool
     * @param runnable
     * @return
     */
    public static ExecutorService singleThreadPoolRun(Runnable runnable) {
        if (singleThreadPool == null) singleThreadPool = Executors.newSingleThreadExecutor();
        singleThreadPool.submit(runnable);
        return singleThreadPool;
    }

}
