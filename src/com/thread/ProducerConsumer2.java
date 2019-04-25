package com.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created with IntelliJ IDEA.
 * User: lhl
 * Date: 2019-04-25
 * Time: 10:53 AM
 * Description:
 */
public class ProducerConsumer2 {
    
    static class Buffer {
        LinkedBlockingDeque list = new LinkedBlockingDeque(10);
        
        public void produce() {
            try {
                list.put(new Object());
                System.out.println("生产者：" + Thread.currentThread().getName() + " 生产商品 size = " + list.size());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        public void consume() {
            try {
                list.take();
                System.out.println("消费者：" + Thread.currentThread().getName() + " 消费商品 size = " + list.size());
            } catch (InterruptedException e) {
                e.printStackTrace();
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
