package com.lhl;

import org.junit.Test;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: lhl
 * Date: 2019-03-21
 * Time: 20:51
 * Description:
 */
public class Solution6 {

    /**
     * 两个链表的第一个公共节点
     * 设 A 的长度为 a + c，B 的长度为 b + c，其中 c 为尾部公共部分长度，可知 a + c + b = b + c + a。
     * 当访问链表 A 的指针访问到链表尾部时，令它从链表 B 的头部重新开始访问链表 B；同样地，当访问链表 B 
     * 的指针访问到链表尾部时，令它从链表 A 的头部重新开始访问链表 A。这样就能控制访问 A 和 B 两个链表的指针能同时访问到交点。
     * 又是骚操作！！！
     * @param pHead1
     * @param pHead2
     * @return
     */
    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        ListNode h1 = pHead1;
        ListNode h2 = pHead2;
        while (h1 != h2) {
            h1 = (h1 == null ? pHead2:h1.next);
            h2 = (h2 == null ? pHead1:h2.next);
        }
        return h1;
    }

    /**
     * 求二叉树的深度
     * @param root
     * @return
     */
    public int TreeDepth(TreeNode root) {
        return root == null ? 0 : 1 + Math.max(TreeDepth(root.left), TreeDepth(root.right));
    }

    /**
     * 输入一棵树，判断是不是平衡二叉树
     * @param root
     * @return
     */
    private boolean isBalance = true;
    public boolean IsBalanced_Solution(TreeNode root) {
        getHeight(root);
        return isBalance;
    }
    
    private int getHeight(TreeNode root) {
        if (root == null || !isBalance) {
            return 0;
        }
        int left = getHeight(root.left);
        int right = getHeight(root.right);
        if (Math.abs(left - right) > 1) {
            isBalance = false;
        }
        return 1 + Math.max(left, right);
    }

    /**
     * 一个整型数组里除了两个数字之外，其他的数字都出现了偶数次。请写程序找出这两个只出现一次的数字。
     */
    public void FindNumsAppearOnce(int[] nums, int num1[], int num2[]) {
        int diff = 0;
        for (int num : nums)
            diff ^= num;  // 找出两个不同的数的异或结果
        diff &= -diff;  // 全 1 为 1  找出 为 1 的那位
        for (int num : nums) {
            if ((num & diff) == 0)  // 是 1 的分成一组，异或后得到第一个不相同的数
                num1[0] ^= num;
            else  
                num2[0] ^= num;  // 是 0 的分成一组，异或后得到地位个不相同的数
        }
    }


    /**
     * 有序序列中找数字和为sum 的两个数
     * @param array
     * @param sum
     * @return
     */
    public ArrayList<Integer> FindNumbersWithSum(int[] array, int sum) {
        int i = 0, j = array.length - 1;
        while (i < j) {
            int cur = array[i] + array[j];
            if (cur == sum)
                return new ArrayList<>(Arrays.asList(array[i], array[j]));
            if (cur < sum)
                i++;
            else
                j--;
        }
        return new ArrayList<>();
    }


    /**
     * 给定一个数组和滑动窗口的大小，找出所有滑动窗口里数值的最大值。
     * 例如，如果输入数组 {2, 3, 4, 2, 6, 2, 5, 1} 及滑动窗口的大小 3，那
     * 么一共存在 6 个滑动窗口，他们的最大值分别为 {4, 4, 6, 6, 6, 5}。
     * @param num
     * @param size
     * @return
     */
    public ArrayList<Integer> maxInWindows(int [] num, int size) {
        ArrayList<Integer> ret = new ArrayList<>();
        if (size > num.length || size < 1) {
            return ret;
        }
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((o1, o2) -> o2 - o1);  // 大根堆
        // 第一个滑动窗口
        for (int i = 0; i < size; i++) {
            maxHeap.offer(num[i]);  // 维护一个大小为 size 的一个大根堆
        }
        ret.add(maxHeap.peek());
        for (int i = 0, j = i + size; j < num.length; i++, j++) {  // j表示滑动窗口往后移动
            // i维持滑动窗口大小
            maxHeap.remove(num[i]);
            maxHeap.offer(num[j]);
            ret.add(maxHeap.peek());
        }
        return ret;
    }

    /**
     * 和为s的连续正数序列
     * @param sum
     * @return
     */
    public ArrayList<ArrayList<Integer>> FindContinuousSequence(int sum) {
        ArrayList<ArrayList<Integer>> ret = new ArrayList<>();
        int start = 1, end = 2;
        int curSum = 3;
        while (end < sum) {
            if (curSum > sum) {
                curSum -= start;
                start++;
            } else if (curSum < sum) {
                end++;
                curSum += end;
            } else {
                ArrayList<Integer> list = new ArrayList<>();
                for (int i = start; i <= end; i++)
                    list.add(i);
                ret.add(list);
                curSum -= start;
                start++;
                end++;
                curSum += end;
            }
        }
        return ret;
    }
    
    @Test
    public void testFindContinuousSequence() {
        System.out.println(FindContinuousSequence(236));
        List<Integer> list = new ArrayList<>( Arrays.asList(1,2,3,4,5));
        Collections.reverse(list);
        System.out.println(list);
    }
    
    @Test
    public void testmaxInWindows() {
        System.out.println(maxInWindows(new int[]{2,3,4,2,6,2,5,1}, 3));
    }
    
}
