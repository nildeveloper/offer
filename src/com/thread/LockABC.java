package com.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created with IntelliJ IDEA.
 * User: lhl
 * Date: 2019-03-14
 * Time: 21:19
 * Description: lock + state 标志
 */
public class LockABC {
    
    private static Lock lock = new ReentrantLock();
    private static int state = 0; // 通过state 值确定是否打印
    
    static class Thread1 extends Thread {
        @Override
        public void run() {
            int i = 0;
            while ( i < 10) {
                try {
                    lock.lock();
                    while (state % 3 == 0) {
                        System.out.print("A");
                        state++;
                        i++;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }
    }
    
    
    static class Thread2 extends Thread {
        @Override
        public void run() {
            int i = 0;
            while (i < 10) {
                lock.lock();
                try {
                    while (state % 3 == 1) {
                        System.out.print("B");
                        state++;
                        i++;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }
    }
    
    static class Thread3 extends Thread {
        @Override
        public void run() {
            int i = 0;
            while (i < 10) {
                try {
                    lock.lock();
                    while (state % 3 == 2) {
                        System.out.println("C");
                        state++;
                        i++;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    public static void main(String[] args) {
        new Thread1().start();
        new Thread2().start();
        new Thread3().start();
    }
    
}
