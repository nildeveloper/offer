package com.thread;

import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created with IntelliJ IDEA.
 * User: lhl
 * Date: 2019-04-25
 * Time: 10:18 AM
 * Description: 1. wait() / notify()  方法2. await() / signal()方法  
 * 3. BlockingQueue阻塞队列  方法4. 信号量  5. 管道
 * 
 */
public class ProducerConsumer {

    /**
     * 缓冲区
     */
    static class Buffer {
        
        private AtomicInteger MAXSIZE = new AtomicInteger(10);
        
        private LinkedList<Object> list = new LinkedList<>();
        
        public void produce() {
            synchronized (list) {
                while (list.size() + 1 > MAXSIZE.get()) {
                    System.out.println("生产者：" + Thread.currentThread().getName() + " 仓库已满 等待");
                    try {
                        list.wait();  // 缓冲区已满， 生产者等待
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                list.add(new Object());  // 成功生产
                System.out.println("生产者：" + Thread.currentThread().getName() + " 生产商品 size = " + list.size());
                list.notify();
            }
        }
        
        public void consume() {
            synchronized (list) {
                while (list.size() == 0) {
                    System.out.println("消费者：" + Thread.currentThread().getName() + " 仓库已空 等待");
                    try {
                        list.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                list.remove();
                System.out.println("消费者：" + Thread.currentThread().getName() + " 消费商品 size = " + list.size());
                list.notify();
            }
        }
        
    }

    /**
     * 生产者
     */
    static class Producer implements Runnable {
        Buffer buffer;

        public Producer(Buffer buffer) {
            this.buffer = buffer;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(1000);
                    buffer.produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 消费者
     */
    static class Consumer implements Runnable{
        Buffer buffer;
        
        public Consumer(Buffer buffer) {
            this.buffer = buffer;
        }


        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(3000);
                    buffer.consume();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        Buffer buffer = new Buffer();
        executorService.submit(new Producer(buffer));
        executorService.submit(new Producer(buffer));
        executorService.submit(new Consumer(buffer));
        executorService.submit(new Consumer(buffer));
//        executorService.submit(new Consumer(buffer));
        executorService.shutdown();
    }
}
