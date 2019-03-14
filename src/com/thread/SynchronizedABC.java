package com.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created with IntelliJ IDEA.
 * User: lhl
 * Date: 2019-03-14
 * Time: 20:12
 * Description: Synchronized + 两个对象锁
 */
public class SynchronizedABC {
    
    public static class ThreadPrint implements Runnable {
        
        private String name;
        private Object pre;
        private Object self;

        private ThreadPrint(String name, Object pre, Object self) {
            this.name = name;
            this.pre = pre;
            this.self = self;
        }

        @Override
        public void run() {
            int count = 10;
            while (count > 0) {
                synchronized (pre) {  // 先获取pre锁
                    synchronized (self) {  // 在获取self 锁
//                        for (int i = 0; i < 10; i++)  // 连续打印10次
                        System.out.print(name + "　　");  // 打印
                        if (name.equals("C")) {
                            System.out.println();
                        }
                        count--;
                        self.notifyAll();  // 唤醒其他线程释放self 锁
                    }

                    // 执行完self 同步代码块，self 锁释放
                    try {
                        if (count == 0) {
                            pre.notifyAll();  // 最后一次打印，通过notifyAll()释放对象锁
                        } else {
                            pre.wait();  // 立即释放pre锁，当前线程休眠，等待唤醒
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } 
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Object a = new Object();
        Object b = new Object();
        Object c = new Object();
        
        ThreadPrint tp1 = new ThreadPrint("A", c, a);
        ThreadPrint tp2 = new ThreadPrint("B", a, b);
        ThreadPrint tp3 = new ThreadPrint("C", b, c);

        ExecutorService service = Executors.newFixedThreadPool(3);
        service.execute(tp1);
        Thread.sleep(10);  // 保证初始ABC的启动顺序
        service.execute(tp2);
        Thread.sleep(10);
        service.execute(tp3);
        Thread.sleep(10);
        service.shutdown();  // 关闭线程池
    }
}
