package com.exams.tujia;


import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {
            int m = in.nextInt();
            int s = in.nextInt();
            int t = in.nextInt();
            int time = 1;
            int runDis = 0, flashDis = 0;
            while (time <= t) {
                // 有魔法值
                if (m >= 10) {
                    flashDis += 50;
                    m -= 10;
                } else {
                    // 无魔法值
                    m += 4;
                }
                // 跑步前进
                runDis += 13;
                // 总是取两个最远的
                if (flashDis > runDis) {
                    runDis = flashDis;
                }
                // 跑出安全区域
                if (runDis >= s) {
                    break;
                }
                time++;
            }
            if (time <= t) {
                System.out.println("Yes");
                System.out.println(time);
            } else {
                System.out.println("No");
                System.out.println(runDis);
            }
        }
    }
}
