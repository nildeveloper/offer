package com.lhl;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Created with IntelliJ IDEA.
 * User: lhl
 * Date: 2019-03-16
 * Time: 16:52
 * Description:
 */
public class Solution5 {

    /**
     * 最小的 k 个数
     * @param nums
     * @param k
     * @return
     */
    public ArrayList<Integer> GetLeastNumbers_Solution(int[] nums, int k) {
        if (k > nums.length || k <= 0)
            return new ArrayList<>();
        // 大根堆 维护最小的k个数
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((o1, o2) -> o2 - o1);
        for (int num : nums) {
            maxHeap.add(num);
            // 如果堆大小 > k, 则需要将对顶元素弹出后继续放入
            if (maxHeap.size() > k) {
                maxHeap.poll();
            }
        }
        return new ArrayList<>(maxHeap);
    }

    /**
     * 最大连续字段和
     * @param array
     * @return
     */
    public int FindGreatestSumOfSubArray(int[] array) {
        if (array.length == 0) {
            return 0;
        }
        int res = array[0];
        int max = array[0];
        for (int i = 0; i < array.length; i++) {
            max = Math.max(max + array[i], array[i]);
            res = Math.max(res, max);
        }
        return res;
    }

    /**
     * 整数中 1 出现的次数
     * 为此他特别数了一下1~13中包含1的数字有1、10、11、12、13因此共出现6次
     * @param n
     * @return
     */
    public int NumberOf1Between1AndN_Solution(int n) {
        int cnt = 0;
        for (int m = 1; m <= n; m *= 10) {
            int a = n / m, b = n % m;
            cnt += (a + 8) / 10 * m + (a % 10 == 1 ? b + 1 : 0);
        }
        return cnt;
    }

    /**
     * 把数组排列成最小的数
     * 输入数组{3，32，321}，则打印出这三个数字能排成的最小数字为321323。
     * 如果 S1+S2 < S2+S1，那么应该把 S1 排在前面，否则应该把 S2 排在前面。
     * @param numbers
     * @return
     */
    public String PrintMinNumber(int [] numbers) {
        if (numbers.length == 0 || numbers == null) {
            return "";
        }
        String[] num = new String[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            num[i] = String.valueOf(numbers[i]);
        }
        Arrays.sort(num, (s1, s2) -> (s1 + s2).compareTo(s2 + s1));
        String ret = "";
        for (String s : num) {
            ret += s;
        }
        return ret;
    }

    /**
     * 丑数
     * 把只包含质因子2、3和5的数称作丑数（Ugly Number）。例如6、8都是丑数，
     * 但14不是，因为它包含质因子7。 习惯上我们把1当做是第一个丑数。求按从小到大的顺序的第N个丑数。
     * p=2^x * 3^y * 5^z
     * @param index
     * @return
     */
    public int GetUglyNumber_Solution(int index) {
        if (index <= 6) {
            return index;
        }
        int i2 = 0, i3 = 0, i5 = 0;
        int[] dp = new int[index];
        dp[0] = 1;
        for (int i = 1; i < index; i++) {
            int next2 = dp[i2] * 2, next3 = dp[i3] * 3, next5 = dp[i5] * 5;
            dp[i] = Math.min(next2, Math.min(next3, next5));
            if (dp[i] == next2) i2++;
            if (dp[i] == next3) i3++;
            if (dp[i] == next5) i5++;
        }
        return dp[index - 1];
    }

    /**
     * 第一次只出现一次的字符
     * 在一个字符串(0<=字符串长度<=10000，全部由字母组成)
     * 中找到第一个只出现一次的字符,并返回它的位置, 如果没
     * 有则返回 -1（需要区分大小写）.
     * @param str
     * @return
     */
    public int FirstNotRepeatingChar(String str) {
        int[] cnts = new int[256];
        for (int i = 0; i < str.length(); i++) {
            cnts[str.charAt(i)]++;
        }
        for (int i = 0; i < str.length(); i++) {
            if (cnts[str.charAt(i)] == 1) {
                return i;
            }
        }
        return -1;
    }
    
    
    @Test
    public void testNumberOf1Between1AndN_Solution() {
        System.out.println(NumberOf1Between1AndN_Solution(13));
    }
    
}
