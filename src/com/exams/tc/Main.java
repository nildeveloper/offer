package com.exams.tc;

import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {
            int shoot = in.nextInt();  // 射击数
            int color = in.nextInt();  // 气球颜色数
            int[] ball = new int[shoot];
            for (int i = 0; i < shoot; i++) {
                ball[i] = in.nextInt();
            }
            boolean flag = false;
            for (int i = color; i <= shoot; i++) {
                int[] state = new int[i + 1];  // 状态数组
                for (int j = 1; j <= i; j++) {
                    state[ball[j]] = 1;
                }
                int n = 0;
                for (int k = 1; k <= i; k++) {
                    if (state[k] != 0) n++;
                }
                if (n == color) {
                    System.out.println(i);
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                System.out.println(-1);
            }
        }
    }
    
    
//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        while (in.hasNextInt()) {
//            int n = in.nextInt();  // n 局
//            int s = in.nextInt();  // 赢 s 局
//            int[] arr = new int[n];
//            int ret = 0;
//            for (int i = 0; i < n; i++) {
//                arr[i] = in.nextInt();  // 牛妹的牌
//            }
//            
//            // 若这一局要赢，只有三种对比方案
//            // 平或者输 s - n 局
//            int num = 1;
//            for (int i = 1; i <= s; i++) {
//                num *= i;
//            }
//            ret = (n * s) / num * 2;
//            ret %= 1e9+7;
//            System.out.println(ret);
//        }
//    }
    
//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        int n = in.nextInt();
//        for (int i = 0; i < n; i++) {
//            int ret = 0;
//            int l = in.nextInt();
//            int r = in.nextInt();
//            if (l % 2 == 0 && r % 2 == 0) {
//                ret = (l + r) / 2;
//            } else if (l % 2 == 0 && r % 2 != 0) {
//                ret = (l + r - 1) / 2 - r;
//            } else {
//                int sum = (l + r) / 2;
//                if (sum % 2 == 0) ret = sum;
//                else ret = -sum;
//            }
//            System.out.println(ret);
//        }
//    }
    
    
    
    
//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        while (in.hasNextInt()) {
//            int ret = 0;
//            int money = in.nextInt();  // 最大硬币
//            int good = in.nextInt();  // 物品价格
//            if (good <= money) {
//                ret = 1;
//            } else {
//                while (good - money > 0) {
//                    good -=money;
//                    ret++;
//                    if (good <= money) {
//                        ret++;
//                        break;
//                    }
//                }
//            }
//            System.out.println(ret);
//        }
//    }
}
