package com.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created with IntelliJ IDEA.
 * User: lhl
 * Date: 2019-03-14
 * Time: 21:33
 * Description: Lock + Condition + 标志
 */
public class LockConditionABC {
    
    private static Lock lock = new ReentrantLock();
    private static Condition conditionA = lock.newCondition();
    private static Condition conditionB = lock.newCondition();
    private static Condition conditionC = lock.newCondition();
    
    private static volatile int number = 1;
    
    static class Thread1 extends Thread {
        @Override
        public void run() {
            int i = 0;
            while (i < 10) {
                try {
                    lock.lock();
                    while (number != 1) {
                        conditionA.await();
                    }
                    System.out.print("A");
                    i++;
                    number = 2;
                    conditionB.signal();
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
                try {
                    lock.lock();
                    while (number != 2) {
                        conditionB.await();
                    }
                    System.out.print("B");
                    i++;
                    number = 3;
                    conditionC.signal();
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
                    while (number != 3) {
                        conditionC.await();
                    }
                    System.out.println("C");
                    i++;
                    number = 1;
                    conditionA.signal();
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
