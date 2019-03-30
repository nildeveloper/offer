package com.fasthand;

import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            String ret = "True";
            String str = in.next();
            if (str != null && str.equals("None")) {
                ret = "True";
            } else {
                String[] split = str.split(",");
                if (split.length % 2 == 0) {
                    ret = "False";
                } else {
                    int[] nums = new int[split.length];
                    for (int i = 0; i < split.length; i++) {
                        nums[i] = Integer.parseInt(split[i]);
                    }
                    for (int i = 0; i < 2 * i + 2; i++) {
                        if (nums[2 * i + 1] > nums[i] || nums[2 * i + 2] < nums[i]) {
                            ret = "False";
                        }
                    }
                    
                }
            }
            System.out.println(ret);
        }
    }
}
