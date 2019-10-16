package com.exams.qunar;


import java.util.Arrays;
import java.util.Scanner;

public class Main1 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {
            int n = in.nextInt();
            int ret = 0;
            int[] arr = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                arr[i] = in.nextInt();
            }
            Arrays.sort(arr);
            int k = n;
            while (k > 3)    //求总时间 
            {
                if (arr[1] + arr[k - 1] < 2 * arr[2]) {
                    ret += arr[k] + arr[1] * 2 + arr[k - 1];
                } else {
                    ret += arr[2] * 2 + arr[1] + arr[k];
                }
                k -= 2;
            }
            //对岸剩下两个成员 
            if (k == 2) {
                ret += arr[2];
            } else {
                //对岸剩下3个成员 
                ret += arr[1] + arr[2] + arr[3];
            }
            System.out.println(ret);
        }
    }
}
