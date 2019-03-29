package com.map;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: lhl
 * Date: 2019-03-17
 * Time: 17:10
 * Description:
 */
public class FailFastTest {
    
    private static HashMap<String, Integer> map = new HashMap<>();
    
    private static final String PREFIX = "TEST-";
    
    static  class ThreadA implements Runnable {
        @Override
        public void run() {
            System.out.println("ThreadA start to add test...");
            for (int i = 0; i < 10000; i++) {
                map.put(PREFIX + i, i);
            }
            System.out.println("ThreadA done!");
        }
    }
    
    static class ThreadB implements Runnable {
        @Override
        public void run() {
            System.out.println("ThreadB start to get test...");
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                System.out.println("Key = " + entry.getKey() + "  Value = " + entry.getValue());
            }
            System.out.println("ThreadB done!");
        }
    }

    // fail-fast 事件
    // 产生 java.util.ConcurrentModificationException
    // modCount 不等于 expectedModCount 则抛出
    public static void main(String[] args) throws InterruptedException {
        Thread threadA = new Thread(new ThreadA());
        Thread threadB = new Thread(new ThreadB());
        threadA.start();
        threadB.start();
        threadA.join();
        threadB.join();
    }
}
