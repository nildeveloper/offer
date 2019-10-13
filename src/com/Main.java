package com; /**
 * Created with IntellJ IDEA.
 * User: lhl
 * Date: 2019-02-20
 * Time: 18:58
 * Description:
 */

import com.lhl.TreeNode;

import java.util.*;
import java.util.stream.Collectors;


public class Main {

    public static void main(String[] args) {
        int n = 4;
        System.out.println(drinkMost(n));
        System.out.println(drinkMost1(n, n));
        System.out.println(n + drinkMost2(n));
    }

    /**
     * 定义 N ，表示可以用 N 个啤酒瓶换一瓶啤酒；
     * 现在已经买了 n 瓶啤酒，问最多可以喝多少瓶啤酒；
     * drinkMost(n) ?
     * @param n
     * @return
     */
    private static int N = 3;
    public static int drinkMost(int n) {
        int now = n;  // 瓶子数
        int count = n;  // 酒数
        while(now >= N){
            now = now - N + 1;
            count = count + 1;
        }
        return count;
    }
    
    public static int drinkMost1(int n, int cnt) {
        if (n < N) {
            return cnt;
        }
        int t = n % N; //n个瓶子换完m瓶酒后剩余t个瓶子
        int m = n / N; //n个瓶子能换m瓶酒
        return drinkMost1(t + m, cnt + m);
    }

    private static int cnt = 0;
    public static int drinkMost2(int n) {
        if (n < N) {
            return cnt;
        }
        int t = n % N; //n个瓶子换完m瓶酒后剩余t个瓶子
        int m = n / N; //n个瓶子能换m瓶酒
        cnt += m;
        return m + drinkMost2(t + m);
    }
    

    public static void rotate(int n) {
        if (n <= 0) {
            return ;
        } else if (n == 1) {
            System.out.println(1);
        } else {
            int[][] state = new int[n][n];
            int[][] arr = new int[n][n];
            int number = 1;
            int i = 0, j = 0;
            int row = n / 2, col = n / 2;
            if (n % 2 == 0) {
                col = n / 2 - 1;
            }
            while (state[row][col] != 1) {
                if (j != state.length -1 && state[i][j + 1] == 0 && ((i == 0) || (i > 0) && state[i - 1][j] != 0)) {
                    state[i][j] = 1;
                    arr[i][j] = number;
                    j++;
                    number++;
                } else if (i != state[0].length - 1 && state[i + 1][j] != 1) {
                    state[i][j] = 1;
                    arr[i][j] = number;
                    i++;
                    number++;
                } else if (j != 0 && state[i][j - 1] != 1) {
                    state[i][j] = 1;
                    arr[i][j] = number;
                    j--;
                    number++;
                } else if (i != 0 && state[i - 1][j] != 1) {
                    state[i][j] = 1;
                    arr[i][j] = number;
                    i--;
                    number++;
                } else {
                    state[i][j] = 1;
                    arr[i][j] = number;
                    break;
                }
            }
            for (int m = 0; m < arr.length; m++) {
                for (int s = 0; s < arr[0].length; s++) {
                    System.out.print(arr[m][s] + " ");
                }
                System.out.println();
            }
        }
    }

//    public static void main(String[] args) {
//        int[][] arr = new int[6][];
//        for (int i = 0; i < arr.length; i++) {
//            arr[i] = new int[i + 1];
//            // 打印空格
//            for (int j = 0; j < arr.length - i - 1; j++) {
//                System.out.print(" ");
//            }
//            for (int j = 0; j < arr[i].length; j++) {
//                if (j == 0 || arr[i].length - 1 == j) {
//                    arr[i][j] = 1;
//                } else {
//                    arr[i][j] = arr[i - 1][j] + arr[i - 1][j - 1];
//                }
//                System.out.printf("%3d", arr[i][j]);
//            }
//            System.out.println();
//        }
//    }
    
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


