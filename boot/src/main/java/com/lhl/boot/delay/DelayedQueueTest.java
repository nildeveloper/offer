package com.lhl.boot.delay;

import io.vavr.control.Try;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA
 *
 * @author liuhaolu01
 * @date 2021-02-25
 * @time 14:43
 * @describe:
 */
public class DelayedQueueTest {

    public static void main(String[] args) throws InterruptedException {

//        DelayQueue<DelayedTask> delayQueue = new DelayQueue<>();
//
//        Thread consumer = new Thread(() -> {
//            while (true) {
//                DelayedTask task = Try.of(() -> delayQueue.take()).getOrElse((DelayedTask) null);
//                if (task == null) {
//                    System.out.println("nothing...");
//                    continue;
//                }
//                System.out.println("time=" + System.currentTimeMillis() + "  task=" + task.toString());
//            }
//        });
//        consumer.start();
//
//        for (int i = 0; i < 10; i++) {
//            DelayedTask task = new DelayedTask("task" + i, TimeUnit.MILLISECONDS.convert((i + 1) * 10, TimeUnit.SECONDS));
//            delayQueue.put(task);
//        }

        DelayAsyncTaskManager taskManager = DelayAsyncTaskManager.taskManager;
        for (int i = 1; i <= 20; i++) {
            taskManager.putTask(() -> System.out.println("Hello World!"), i * 5, TimeUnit.SECONDS);
        }

        Thread.currentThread().join();
    }
}
