package com.thread;


/**
 * Created with IntelliJ IDEA.
 * User: lhl
 * Date: 2019-03-14
 * Time: 22:23
 * Description: Join
 */ 
public class JoinABC {
    
    private static Object object = new Object();
    
    static class Thread1 extends Thread {
        @Override
        public void run() {
            System.out.println("A");
        }
    }
    
    static class Thread2 extends Thread {
        private Thread1 thread1;
        
        Thread2(Thread1 thread1) {
            this.thread1 = thread1;
        }

        @Override
        public void run() {
            try {
                thread1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("B");
        }
    } 
    
    static class Thread3 extends Thread {
        private Thread2 thread2;
        
        Thread3(Thread2 thread2) {
            this.thread2 = thread2;
        }

        @Override
        public void run() {
            try {
                thread2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("C");
        }
    }

    public static void main(String[] args) {
        Thread1 thread1 = new Thread1();
        Thread2 thread2 = new Thread2(thread1);
        Thread3 thread3 = new Thread3(thread2);
        thread1.start();
        thread2.start();
        thread3.start();
    }
}
