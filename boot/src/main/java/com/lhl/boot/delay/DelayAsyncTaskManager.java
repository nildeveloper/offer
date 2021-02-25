package com.lhl.boot.delay;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA
 *
 * @author liuhaolu01
 * @date 2021-02-25
 * @time 15:12
 * @describe:
 */
public class DelayAsyncTaskManager {

    public static DelayAsyncTaskManager taskManager = new DelayAsyncTaskManager();

    private ExecutorService executorService;

    private Thread daemonThread;

    private DelayQueue<DelayedAsyncTask<?>> delayQueue;

    private DelayAsyncTaskManager() {
        executorService = Executors.newFixedThreadPool(5);
        delayQueue = new DelayQueue<>();
        daemonThread = new Thread(() -> execute());
        daemonThread.setName("DelayAsyncTaskManagerDaemonThread");
        daemonThread.start();
    }

    /**
     * putTask
     * @param task
     * @param delayTime 延迟时间
     * @param timeUnit 单位
     */
    public void putTask(Runnable task, long delayTime, TimeUnit timeUnit) {
        long time = TimeUnit.MILLISECONDS.convert(delayTime, timeUnit);
        DelayedAsyncTask delayedAsyncTask = new DelayedAsyncTask(task, time);
        delayQueue.put(delayedAsyncTask);
    }

    private void execute() {
        while (true) {
            Map<Thread, StackTraceElement[]> map = Thread.getAllStackTraces();
            System.out.println("当前存活线程数量:" + map.size());
            int taskNum = delayQueue.size();
            System.out.println("当前延时任务数量:" + taskNum);
            try {
                DelayedAsyncTask<?> delayedAsyncTask = delayQueue.take();
                if (delayedAsyncTask != null) {
                    Runnable task = delayedAsyncTask.getTask();
                    if (null == task) {
                        continue;
                    }
                    executorService.execute(task);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
