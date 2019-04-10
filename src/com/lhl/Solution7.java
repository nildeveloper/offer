package com.lhl;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * Created with IntelliJ IDEA.
 * User: lhl
 * Date: 2019-03-30
 * Time: 16:31
 * Description:
 */
public class Solution7 {

    /**
     * 扑克牌顺子
     * 五张牌，其中大小鬼为癞子，牌面大小为 0。判断这五张牌是否能组成顺子。
     * @param numbers
     * @return
     */
    public boolean isContinuous(int [] numbers) {
        if (numbers.length < 5) {
            return false;
        }
        Arrays.sort(numbers);  // 先排序
        int cntZero = 0;  // 记录0的个数
        for (int number : numbers) {
            if (number == 0) {
                cntZero++;
            }
        }
        // 判断能否组成顺子
        for (int i = cntZero; i < numbers.length - 1; i++) {
            // 若存在对子，返回false
            if (numbers[i] == numbers[i + 1]) {
                return false;
            }
            // 判断 0 是否够补位
            cntZero -= numbers[i + 1] - numbers[i] - 1;  // 相邻两数差-1
        }
        return cntZero >= 0;
    }

    /**
     * 圆圈中最后剩下的数
     * 约瑟夫环，圆圈长度为 n 的解可以看成长度为 n-1 的解再加上报数的长度 m。因为是圆圈，所以最后需要对 n 取余。
     * @param n
     * @param m
     * @return
     */
    public int LastRemaining_Solution(int n, int m) {
        if (n <= 0 || m <= 0) {
            return -1;
        }
        LinkedList<Integer> list = new LinkedList<>();
        // 所有数构建循环链表
        for (int i = 0; i < n; i++) {
            list.add(i);
        }
        int index = 0;  // 开始位置
        while (list.size() > 1) {
            index = (index + m - 1) % list.size();  // 删除位置
            list.remove(index);
        }
        return list.size() == 1 ? list.get(0) : -1;
    }

    /**
     * 股票最大利润
     * 可以有一次买入和一次卖出，那么买入必须在前。求最大收益。
     * 使用贪心策略，假设第 i 轮进行卖出操作，买入操作价格应该在 i 之前并且价格最低。
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int soFarMin = prices[0];
        int maxProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            soFarMin = Math.min(soFarMin, prices[i]);  // 最低购价格
            maxProfit = Math.max(maxProfit, prices[i] - soFarMin);  // 最大利润
        }
        return maxProfit;
    }

    /**
     * 求 n! 
     * 要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）
     * && 短路与特性 前面为false，后面不在判断
     * 当 n == 0时，只判断（n > 0）不成立false，后面不执行
     * 当 n > 0时，地柜调用进行计算
     * @param n
     * @return
     */
    public int Sum_Solution(int n) {
        int sum = n;
        boolean b = (n > 0) && ((sum += Sum_Solution(n - 1)) > 0);
        return sum;
    }


    /**
     * 不用加减乘除做加法
     * a ^ b 表示没有考虑进位的情况下两数的和，(a & b) << 1 就是进位。
     * 递归会终止的原因是 (a & b) << 1 最右边会多一个 0，那么继续递归，
     * 进位最右边的 0 会慢慢增多，最后进位会变为 0，递归终止。
     * @param num1
     * @param num2
     * @return
     */
    public int Add(int num1,int num2) {
        return num2 == 0 ? num1 : Add(num1 ^ num2, (num1 & num2) << 1);
    }


    /**
     * 构建乘机数组
     * 给定一个数组A[0,1,...,n-1],请构建一个数组B[0,1,...,n-1],其中B中的
     * 元素B[i]=A[0]*A[1]*...*A[i-1]*A[i+1]*...*A[n-1]。不能使用除法。
     * @param A
     * @return
     */
    public int[] multiply(int[] A) {
        int n = A.length;
        int[] B = new int[n];
        for (int i = 0, product = 1; i < n; product *= A[i], i++) {
            B[i] = product;
        }
        for (int i = n - 1, product = 1; i >= 0; product *= A[i], i--) {
            B[i] *= product;
        }
        return B;
    }
    
    
    
}
