package com.exams.bytebeat;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {
            int n = in.nextInt();  // 原始数目
            int m = in.nextInt();  // 需要数目
            double[] len = new double[n];
            double ret = 0.0;
            for (int i = 0; i < len.length; i++) {
                len[i] = in.nextInt();
            }
            Arrays.sort(len);
            // m <= n 的情况
            if (m <= n) {
                if (len[len.length - 1] / m >= len[len.length - 2]) {
                    ret = len[len.length - 1] / (m * 1.0);
                } else if (len[len.length - 1] / m < len[len.length - 2] && len[len.length - 1] / m > len[len.length - 3]) {
                    if (len[len.length - 1] / (m - 1) >= len[len.length - 2]) ret = len[len.length - 2];
                    else ret = len[len.length - 1] / (m - 1);
                } else {
                    ret = len[len.length - m];
                }
            } else {  // m > n 情况
                ret = len[len.length - 1] / ((m - n + 1) * 1.0);
            }
            System.out.println(String.format("%.2f", ret));
        }
    }


//        public static void main(String[] args) {
//            Scanner in = new Scanner(System.in);
//            int cnt = in.nextInt();
//            for (int m = 0; m < cnt; m++) {
//                int n = in.nextInt();  // 比赛人数
//                int[] score = new int[n];
//                int ret = 0;  // 准备奖品数量
//                for (int i = 0; i < n; i++) {
//                    score[i] = in.nextInt();
//                }
//
//
//            }
//        }

//        public static void main(String[] args) {
//            Scanner in = new Scanner(System.in);
//            while (in.hasNextInt()) {
//                int n = in.nextInt();
//                StringBuilder[] strs = new StringBuilder[n];
//                for (int i = 0; i < n; i++) {
//                    String word = in.next();
//                    strs[i] = new StringBuilder(word);
//                }
//
//                // 处理字符串
//                for (int i = 0; i < n; i++) {
//                    StringBuilder str = strs[i];
//                    for (int j = 1; j < str.length() - 1; j++) {
//                        if ((j - 1) >=0 && (j + 1) < str.length() && str.charAt(j - 1) == str.charAt(j) && str.charAt(j) == str.charAt(j + 1)) {
//                            str.deleteCharAt(j + 1);
//                            j--;
//                        }
//                        if ((j - 1) >=0 && (j + 2) < str.length() && str.charAt(j - 1) == str.charAt(j) && str.charAt(j + 1) == str.charAt(j + 2)) {
//                            str.deleteCharAt(j + 2);
//                            j--;
//                        }
//                    }
//                    System.out.println(str);
//                }
//            }
//        }

//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        while (in.hasNextInt()) {
//            int n = in.nextInt();  // 商品价格
//            int m = 1024 - n;  // 找回钱数
//            int[] coin = new int[]{64, 16, 4, 1};
//            int ret = 0;
//            int index = 0;
//            while (m >= 0) {
//                if (m >= coin[index]) {
//                    m -= coin[index];
//                    ret++;
//                } else {
//                    if (index < 3) {
//                        index++;
//                    }
//                }
//                if (m == 0) {
//                    break;
//                }
//            }
//            System.out.println(ret);
//        }
//    }
}
