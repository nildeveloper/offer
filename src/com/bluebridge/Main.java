package com.bluebridge;

import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: lhl
 * Date: 2019-03-18
 * Time: 10:19 PM
 * Description:
 */
public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        double f1 = 1, f2 = 1;
        double ret = 0;
        int n = in.nextInt();
        if (n == 1) {
            ret = 1;
        } else if (n == 2) {
            ret = 2;
        } else {
            for (int i = 2; i < n; i++) {
                ret = f1 + f2;
                f1 = f2;
                f2 = ret;
            }
            ret %= 10007;
            System.out.println(ret);
        }
    }
}
