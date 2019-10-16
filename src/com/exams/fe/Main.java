package com.exams.fe;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {
            int n = in.nextInt(); // n条鱼
            int m = in.nextInt(); // m轮
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                list.add(in.nextInt());
            }
            for (int i = 0; i < m; i++) {
                Collections.sort(list);
                int tmp = list.get(0);
                list.remove(0);
                for (int j = 0; j < list.size(); j++) {
                    if (list.get(j) != tmp) {
                        list.add(list.get(j) + tmp);
                        list.remove(j);
                        break;
                    }
                }
            }
            System.out.println(list.get(0));
        }
    }

//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        while (in.hasNextInt()) {
//            int m = in.nextInt();
//            System.out.println(walk(m));
//        }
//    }
//
//    public static int walk(int target) {
//        if (target == 1) {
//            return 1;
//        } else if (target == 2) {
//            return 2;
//        } else {
//            return walk(target - 1) + walk(target - 2);
//        }
//    }

//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        int count = 0;
//        while (in.hasNextInt()) {
//            int m = in.nextInt();
//            int n = in.nextInt();
//            int x = in.nextInt();
//            int[] nums = new int[n - m + 1];
//            int i = 0;
//            while (m <= n) {
//                nums[i++] = m++;
//            }
//            String str = Arrays.toString(nums);
//            for (int j = 0; j < str.length(); j++) {
//                char c = str.charAt(j);
//                if (Character.isDigit(c) && Integer.parseInt(String.valueOf(c)) == x) {
//                    count++;
//                }
//            }
//            System.out.println(count);
//        }
//    }
}