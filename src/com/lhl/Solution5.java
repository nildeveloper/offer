package com.lhl;

import org.junit.Test;

import java.util.*;

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
     * 数据流中的中位数
     * 我们使用Insert()方法读取数据流，使用GetMedian()方法获取当前读取数据的中位数。
     * 左边大根堆元素 + 右边小根堆元素是一个有序序列
     * @param num
     */
    private PriorityQueue<Integer> left = new PriorityQueue<>(Collections.reverseOrder());  // 大根堆
    private PriorityQueue<Integer> right = new PriorityQueue<>();  // 小根堆
    private int n = 0; // 当前数据流读入的元素个数
    public void Insert(Integer num) {
        // 插入要保证两堆处于平衡状态
        if (n % 2 == 0) {
            left.offer(num);  // 先放入左边大根堆
            right.offer(left.poll());  // 将左边堆顶最大元素出堆并放入右边小根堆
        } else {
            right.offer(num);
            left.offer(right.poll());
        }
        n++;
    }

    public Double GetMedian() {
        if (n % 2 == 0) {
            return (left.peek() + right.peek()) / 2.0;
        }
        return (double)right.peek();  // 奇数个时：右边小根堆堆顶元素为中位数
    }

    /**
     * 字符流中第一个不重复的字符
     * @param ch
     */
    private Queue<Character> queue = new LinkedList<>();
    int[] flag = new int[256];
    public void Insert(char ch) {
        flag[ch] += 1;
        queue.offer(ch);
        while (!queue.isEmpty() && flag[queue.peek()] > 1) {
            queue.poll();
        }
    }
    
    //return the first appearence once char in current stringstream
    public char FirstAppearingOnce() {
        return queue.isEmpty() ? '#' : queue.peek();
    }

    /**
     * 把数组排列成最小的数
     * 输入一个正整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。
     * 例如输入数组 {3，32，321}，则打印出这三个数字能排成的最小数字为 321323。
     * @param numbers
     * @return
     */
    public String PrintMinNumber(int[] numbers) {
        if (numbers == null || numbers.length == 0)
            return "";
        int n = numbers.length;
        String[] nums = new String[n];
        for (int i = 0; i < n; i++)
            nums[i] = numbers[i] + "";
        Arrays.sort(nums, (s1, s2) -> (s1 + s2).compareTo(s2 + s1));
        String ret = "";
        for (String str : nums)
            ret += str;
        return ret;
    }
    
    @Test
    public void testPrintMinNumber() {
        int[] nums = new int[]{3,32,321};
        System.out.println(PrintMinNumber(nums));
    }
    
}
