package com.exams.bili;

import java.util.ArrayList;
import java.util.List;


/**
 * Created with IntelliJ IDEA.
 * User: haolu.liu
 * Date: 2019-08-20 18:59
 * Description:
 */
import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int count = 0;
        while (in.hasNext()) {
            if (count != 0) {
                in.nextLine();
            }
            count++;
            String str = in.nextLine();
            int n = in.nextInt();
            String[] split = str.split(" ");
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < split.length - 1; i++) {
                sb.append(split[i]);
            }
            List<StringBuilder> list = new ArrayList<>();
            for (int i = 0; i < sb.length(); i += n) {
                if (i + n <= sb.length()) {
                    StringBuilder builder = new StringBuilder(sb.substring(i, i + n));
                    list.add(builder.reverse());
                } else {
                    StringBuilder builder = new StringBuilder(sb.substring(i, sb.length()));
                    list.add(builder);
                }
            }
            StringBuilder ret = new StringBuilder();
            list.forEach(item -> {
                for (int i = 0; i < item.length(); i++) {
                    ret.append(item.charAt(i)).append("->");
                }
            });
            System.out.println(ret.substring(0, ret.length() - 2));
        }
    }

//
//    public int knapsack(int n, int c, int[] w, int[] v) {
//        int[][] m = new int[15][15];
//        int[] x = new int[n + 1];
//        for (int i = 1; i <= n; i++) {
//            for (int j = 1; j <= c; j++) {
//                // 如果能装下，判断要不要装
//                if (j >= w[i]) {
//                    m[i][j] = Math.max(m[i-1][j], m[i-1][j-w[i]] + v[i]);
//                } else {
//                    m[i][j] = m[i-1][j];
//                }
//            }
//        }
//        // 求装入背包的物品
//        int j = c;
//        for (int i = n; i > 0; i--) {
//            if (m[i][j] > m[i - 1][j]) {
//                x[i] = 1;
//                j -= w[i];
//            }
//        }
//        System.out.println(Arrays.toString(x));
//        return m[n][c];
//    }
//
//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        while (in.hasNext()) {
//            int n = in.nextInt();
//            int m = in.nextInt();
//            int[] w = new int[n];
//            int[] v = new int[n];
//            for (int i = 0; i < n; i++) {
//                w[i] = in.nextInt();
//            }
//            for (int i = 0; i < n; i++) {
//                v[i] = in.nextInt();
//            }
//        }
//    }

//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        while (in.hasNext()) {
//            String line = in.nextLine();
//            String[] split = line.split(",");
//            Arrays.sort(split, (s1, s2) -> s1.charAt(0)-s2.charAt(0));
//            StringBuilder builder = new StringBuilder();
//            for (String s : split) {
//                builder.append(s);
//            }
//            System.out.println(builder);
//        }
//    }

//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        while(in.hasNext()) {
//            String line = in.nextLine();
//            String[] split = line.split(" ");
//            StringBuilder builder = new StringBuilder();
//            for (int i = split.length - 1; i >= 0; i--) {
//                builder.append(split[i]).append(" ");
//            }
//            System.out.println(builder.substring(0, builder.length() - 1));
//        }
//    }
}
