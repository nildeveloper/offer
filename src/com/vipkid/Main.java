package com.vipkid;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {

//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        while (in.hasNextInt()) {
//            int n = in.nextInt();
//            int count = 0;
//            while (n != 0) {
//                n &= n - 1;
//                count++;
//            }
//            System.out.println(count);
//        }
//    }

//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        long count = 0L;
//        String lines = in.nextLine();
//        String[] split = lines.split(",");
//        for (int i = 0; i < split.length; i++) {
//            split[i] = split[i].trim();
//        }
//        StringBuilder builder = new StringBuilder();
//        for (int i = 0; i < split.length; i++) {
//            for (int j = 0; j < split.length; j++) {
//                if (Long.parseLong(split[i]) + Long.parseLong(split[i]) == 0 
//                        && builder.indexOf(j + "") == -1 && builder.indexOf(i + "") == -1) {
//                    builder.append(i + "&" + j);
//                    count++;
//                }
//            }
//        }
//        System.out.println(count);
//    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        String[] arr = line.split(",");
        for (int i = 0; i < arr.length; i++) {
            arr[i] = arr[i].trim();
        }
        long count = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                if (Long.parseLong(arr[i]) + Long.parseLong(arr[j]) == 0 && sb.indexOf(j + "") == -1 && sb.indexOf(i + "") == -1) {
                    sb.append(i + "&" + j);
                    count++;
                }
            }
        }
        System.out.println(count);
    }
}
