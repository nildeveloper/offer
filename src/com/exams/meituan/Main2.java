package com.exams.meituan;

import java.util.Scanner;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created with IntelliJ IDEA.
 * User: lhl
 * Date: 2019-10-16
 * Time: 16:02
 * Description:
 */
public class Main2 {

    private static Semaphore semaphoreA = new Semaphore(1);
    private static Semaphore semaphoreB = new Semaphore(0);
    private static Semaphore semaphoreC = new Semaphore(0);
    // 0 打印B 1打印C
    private static AtomicInteger state = new AtomicInteger(0);
    private static AtomicInteger count = new AtomicInteger(1);

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {
            int n = in.nextInt();
            ReviewEncourage reviewEncourage = new ReviewEncourage(n);
            new Thread1(reviewEncourage).start();
            new Thread2(reviewEncourage).start();
            new Thread3(reviewEncourage).start();
        }
    }

    static class Thread1 extends Thread {

        private static ReviewEncourage reviewEncourage;

        public Thread1(ReviewEncourage reviewEncourage) {
            this.reviewEncourage = reviewEncourage;
        }

        @Override
        public void run() {
            try {
                semaphoreA.acquire();  // A 获取信号执行，A信号量 -1
                if (count.get() <= reviewEncourage.getN() && count.get() % 2 != 0) {
                    reviewEncourage.bonus(reviewEncourage.new PrizePool());
                    count.incrementAndGet();
                }
                if (state.get() == 0) {
                    semaphoreB.release();  // B 释放信号量，B信号量 + 1
                } else {
                    semaphoreC.release();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class Thread2 extends Thread {

        private static ReviewEncourage reviewEncourage;

        public Thread2(ReviewEncourage reviewEncourage) {
            this.reviewEncourage = reviewEncourage;
        }

        @Override
        public void run() {
            try {
                semaphoreB.acquire();
                if (count.get() <= reviewEncourage.getN() && count.get() % 2 == 0 && state.get() == 0) {
                    reviewEncourage.coupon(reviewEncourage.new PrizePool());
                    count.incrementAndGet();
                    state.set(1);
                }
                semaphoreA.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class Thread3 extends Thread {

        private static ReviewEncourage reviewEncourage;

        public Thread3(ReviewEncourage reviewEncourage) {
            this.reviewEncourage = reviewEncourage;
        }

        @Override
        public void run() {
            try {
                semaphoreC.acquire();
                if (count.get() <= reviewEncourage.getN() && count.get() % 2 == 0 && state.get() == 1) {
                    reviewEncourage.contribution(reviewEncourage.new PrizePool());
                    count.incrementAndGet();
                    state.set(0);
                }
                semaphoreA.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


class ReviewEncourage {

    private static int n;

    // 构造函数,n为中奖用户数
    public ReviewEncourage(int n) {
        this.n = n;
    }


    // PrizePool类仅有一个send方法，实现如下：
    class PrizePool {
        public void send(String input) {
            System.out.print(input);
        }
    }


    // 仅能打印A，表示发放积分
    public void bonus(PrizePool prizePool) {
        prizePool.send("A");
    }

    // 仅能打印B，表示发放优惠券
    public void coupon(PrizePool prizePool) {
        prizePool.send("B");
    }

    // 仅能打印C，表示发放贡献值
    public void contribution(PrizePool prizePool) {
        prizePool.send("C");
    }

    public static int getN() {
        return n;
    }

}
