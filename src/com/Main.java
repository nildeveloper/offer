package com; /**
 * Created with IntellJ IDEA.
 * User: lhl
 * Date: 2019-02-20
 * Time: 18:58
 * Description:
 */

import java.util.Arrays;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int ret = 0;
        while (in.hasNextInt()) {
            int child = in.nextInt();
            int desk = in.nextInt();
            int[] arr = new int[child];
            for (int i = 0; i < arr.length; i++) {
                arr[i] = in.nextInt();
            }
            Arrays.sort(arr);
            int n = child - desk;
            ret = arr[0] + arr[2 * n - 1];
            System.out.println(ret);
        }
    }
}


