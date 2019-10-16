package com.exams.fasthand;

import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: lhl
 * Date: 2019-03-30
 * Time: 20:36
 * Description:
 */
public class Main3 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {// 注意，如果输入是多个测试用例，请通过while循环处理多个测试用例
            int n = in.nextInt();
            int cnt = 0;
            while (n != 0) {
                cnt++;
                n = n & (n - 1);
            }
            System.out.println(cnt);
        }
    }
    
    public int countOne(int n) {
        return Integer.bitCount(n);
    }
}
