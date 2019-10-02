package com; /**
 * Created with IntellJ IDEA.
 * User: lhl
 * Date: 2019-02-20
 * Time: 18:58
 * Description:
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;


public class Main {
    

    public static void main(String[] args) {
        
//        int sum = 0;
//        for (int i = 1; i <= 100; i++) {
//            if (i %2 != 0) {
//                sum += i;
//            }
//        }
//        System.out.println(sum);
//        int a = 10;
//        long b = 20;
//        test(a,b);
//        String s = "I am a good cat";
//        System.out.println(s.indexOf("a", 7));
        System.out.println(func(20));
    }
    
    public static  int func(int target) {
        if (target == 1) {
            return  1;
        }
        if (target == 2) {
            return 3;
        }
        return 2 * func(target - 1) + func(target - 2);
    }
    
    public static void test(int a, double b) {
        System.out.println("haha" + (a + b));
    }
    public static void test(int a, float b) {
        System.out.println("hahaha" + (a +b));
    }

//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        int ret = 0;
//        while (in.hasNextInt()) {
//            int child = in.nextInt();
//            int desk = in.nextInt();
//            int[] arr = new int[child];
//            for (int i = 0; i < arr.length; i++) {
//                arr[i] = in.nextInt();
//            }
//            Arrays.sort(arr);
//            int n = child - desk;
//            ret = arr[0] + arr[2 * n - 1];
//            System.out.println(ret);
//        }
//        List<String> list = generateParenthesis(2);
//        list.forEach(System.out::println);
//    }

    public static List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        int leftCount = 0;//左括号数量
        int rightCount = 0;//右边括号数量
        String tmpSubResult = "";//临时结果集
        dfs(leftCount, rightCount, tmpSubResult, result, n);
        List<String> list = new ArrayList<>();
        for (String item : result) {
            if (item.contains(")(")) {
                String[] split = item.split("\\)\\(");
                for (int i =0; i < split.length; i++) {
                    list.add(item);
                }
            }
            String replace = item.replaceFirst("\\(\\)", "(4399)");
            list.add(replace);
        }

        return list;
    }

    public static void dfs(int left, int right, String tmpSubResult, List<String> result, int n) {
        //如果左右括号数量都是n，那就是结果集了
        if (left == n && right == n) {
            result.add(tmpSubResult);
            return;
        }
        //递归过程中，如果left还小于n，继续增加左括号
        if (left < n) {
            dfs(left + 1, right, tmpSubResult + "(", result, n);
        }
        //如果发现左括号数量大于右括号了，马上增加一个右括号
        if (left > right) {
            dfs(left, right + 1, tmpSubResult + ")", result, n);
        }
    }

}


