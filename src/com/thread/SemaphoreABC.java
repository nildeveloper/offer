package com.thread;

import java.util.concurrent.Semaphore;

/**
 * Created with IntelliJ IDEA.
 * User: lhl
 * Date: 2019-03-14
 * Time: 21:55
 * Description:
 */
public class SemaphoreABC {
    
    private static Semaphore semaphoreA = new Semaphore(1);
    private static Semaphore semaphoreB = new Semaphore(0);
    private static Semaphore semaphoreC = new Semaphore(0);
    
    static class Thread1 extends Thread {
        @Override
        public void run() {
            try {
                for (int i = 0; i < 10; i++) {
                    semaphoreA.acquire();  // A 获取信号执行，A信号量 -1
                    System.out.print("A");
                    semaphoreB.release();  // B 释放信号量，B信号量 + 1
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    static class Thread2 extends Thread {
        @Override
        public void run() {
            try {
                for (int i = 0; i < 10; i++) {
                    semaphoreB.acquire();
                    System.out.print("B");
                    semaphoreC.release();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    static class Thread3 extends Thread {
        @Override
        public void run() {
            try {
                for (int i = 0; i < 10; i++) {
                    semaphoreC.acquire();
                    System.out.println("C");
                    semaphoreA.release();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new Thread1().start();
        new Thread2().start();
        new Thread3().start();
    }
}
