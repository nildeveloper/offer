package com.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: lhl
 * Date: 2019-09-26
 * Time: 10:50
 * Description:
 */
public class ThreadPoolTest {
    
    
    public void test() {
        Executors.newCachedThreadPool();
        Executors.newFixedThreadPool(3);
        Executors.newSingleThreadExecutor();
        ThreadPoolExecutor executor = new
                ThreadPoolExecutor(4, 6, 
                10L, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(100));

    }
}
