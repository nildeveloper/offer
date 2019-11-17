package com.exams.okgroup;


import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            String str = in.nextLine();
            char[] chars = str.toCharArray();
            int count = 0;
            for (int i = 0; i < chars.length; i++) {
                boolean flag = false;
                for (int j = i + 1; j < chars.length; j++) {
                    if (chars[i] != chars[j]) {
                        break;
                    }
                    flag = true;
                    chars[j] = ',';
                }
                if (flag) {
                    chars[i] = ',';
                }
            }
            String ret = String.valueOf(chars);
            String[] split = ret.split(",");
            for (int i = 0; i < split.length; i++) {
                if (!split[i].equals("")) {
                    count++;
                }
            }
            System.out.println(count);
        }
    }
}
