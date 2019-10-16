package com.exams.qunar;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {
            int n = in.nextInt();
            long[][] arr = new long[n + 1][n + 1];
            for (int i = 0; i < arr.length; i++) {
                arr[i][0] = 1;
                arr[i][i] = 1;
            }
            for (int i = 1; i < arr.length; i++) {
                for (int j = 1; j < i; j++) {
                    arr[i][j] = arr[i - 1][j] + arr[i - 1][j - 1];
                }
            }
            for (int i = 0; i < arr[0].length; i++) {
                System.out.print(arr[n][i] + " ");
            }
        }
    }
    
}
