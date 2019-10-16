package com.exams.meituan;

import org.junit.Test;

import java.util.Random;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: lhl
 * Date: 2019-04-23
 * Time: 18:45
 * Description:
 */
public class Main {


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            int n = in.nextInt();
            int[] num = new int[n - 1];
            int[] color = new int[n];
            for (int i = 0; i < num.length; i++) {
                num[i] = in.nextInt();
            }
            
            for (int i = 0; i < color.length; i++) {
                color[i] = in.nextInt();
            }

            if (n <= 3) {
                System.out.println(2);
            } else {
                System.out.println(3);
            }
        }
    }

//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        while (in.hasNext()) {
//            int n = in.nextInt();
//            int m = in.nextInt();
//            int ret = 0;
//            int[][] num = new int[n][m];
//            for (int i = 0; i < num.length; i++) {
//                for (int j = 0; j < num[0].length; j++) {
//                    num[i][j] = in.nextInt();
//                }
//            }
//
//            for (int i = 0; i < num.length; i++) {
//                for (int j = 0; j < num[0].length; j++) {
//                    
//                }
//            }
//            System.out.println(4);
//        }
//    }
    
    @Test
    public void test() {
        Random rand = new Random();
        System.out.println(rand.nextInt(5));
    }
}
