package com.atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created with IntelliJ IDEA.
 * User: lhl
 * Date: 2019-04-10
 * Time: 7:43 PM
 * Description:
 */

class Counter {

    private volatile int count = 0;
    
    private AtomicInteger number = new AtomicInteger(0);

    public void inc() {
        try {
            Thread.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        count++;  // volatile 不能保证变量原子性
        number.incrementAndGet();  // Atomic 包内原子变量内部使用volatile与CAS操作保证原子性
    }

    @Override
    public String toString() {
        return "[count=" + count + "], [number=" + number + "]";
    }
}


public class VolatileTest {

    public static void main(String[] args) {

        final Counter counter = new Counter();
        ExecutorService threadPool = Executors.newFixedThreadPool(1000);
        for (int i = 0; i < 1000; i++) {
            threadPool.execute(() -> {
                counter.inc();
            });
        }
        threadPool.shutdown();
        System.out.println(counter);  // [count=982], [number=1000]
    }
}
