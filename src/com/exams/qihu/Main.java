package com.exams.qihu; /**
 * Created with IntellJ IDEA.
 * User: lhl
 * Date: 2019-02-20
 * Time: 18:58
 * Description:
 */

import java.util.Scanner;

/**
 * 桌子上有N张写有数字0或1的卡片，初始时玩家并不知道每张卡片上的数字是多少。将卡片从1到N编号，玩家每次可以选择一个区间[L，R]（1≤L≤R≤N）并询问裁判编号为L到R的卡片上的数字之和是奇数还是偶数（所需费用为AL,R）。那么玩家至少要花费多少费用才能确定每张卡片上的数字？

 输入
 第一行包含一个整数N，1≤N≤1000。

 接下来N行，第i行包含N-i+1个整数Ai,i到Ai,N，1≤Ai,j≤1000。

 输出
 输出确定每张卡片上的数字所需的最小费用。


 样例输入
 3
 1 2 3
 2 1
 3
 */


/**
 * 给出一个m*n的只由01组成的矩阵，我们称包含：

 1

 111

 1

 这样的形状为星星（矩阵的四角为0、1均可），现在要从这个矩阵中选出一个矩形区域，要求这个矩形区域中至少有k个星星，问有多少个这样的矩形区域。（矩形区域即选取连续的若干行和连续的若干列所构成的交集区域）

 输入
 输入第一行包含三个整数n,m,k(1<=n,m<=500,1<=k<=m*n), n和m分别表示矩阵的行数和列数。

 接下来有n行，每行包含一个长度为m的仅含01的字符串，描述这个矩阵。

 输出
 输出仅包含一个正整数，表示符合要求的矩形区域的数量。


 样例输入
 3 5 2
 11100
 11110
 01100
 样例输出
 2

 Hint
 有选择1到4列和1到5列两种矩形区域的选择方式
 */

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int ret = 0;
        while (in.hasNextInt()) {
            int row = in.nextInt();
            int col = in.nextInt();
            int num = in.nextInt();
            int[][] arr = new int[row][col];
            for (int i =0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    arr[i][j] = in.nextInt();
                }
            }

            for (int i =0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                }
            }
            
        }
        
    }
    
//    public static boolean isOne(int i, int j, int[][] arr) {
//        
//    }
}


//public class com.exams.qihu.Main {
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
//    }
//}


