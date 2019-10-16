package com.exams.tc;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: lhl
 * Date: 2019-04-05
 * Time: 18:42
 * Description:
 */
public class Main1 {

//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        while (in.hasNext()) {
//            int n = in.nextInt();
//            String s = in.next();
//            StringBuilder str = new StringBuilder(s);
//            for (int i = 0; i < str.length() - 1; i++) {
//                if (str.length() <= 1) {
//                    break;
//                } else {
//                    if ((str.charAt(i) == '0' && str.charAt(i + 1) == '1') || (str.charAt(i) == '1' && str.charAt(i + 1) == '0')) {
//                        str.deleteCharAt(i);
//                        str.deleteCharAt(i);
//                        i = -1;
//                    }
//                }
//            }
//            System.out.println(str.length());
//        }
//    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {
            int m = in.nextInt();  // 最大面值
            int n = in.nextInt();  // 硬币种数
            int[] coin = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                coin[i] = in.nextInt();
            }
            Arrays.sort(coin);
            if (coin[1] != 1) {
                System.out.println(-1);
            }

            int sum = 0, ans = 0;
            while (true) {
                if (sum >= m) {
                    System.out.println(ans);
                    return;
                }
                for (int i = n; i >= 1; i--)   //从大到小
                    if (coin[i] <= sum + 1) {
                        sum += coin[i];
                        ans++;
                        break;
                    }
            }
        }
    }
}
