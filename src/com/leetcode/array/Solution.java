package com.leetcode.array;

import java.util.*;

/**
 * Created with IntelliJ IDEA
 *
 * @author liuhaolu01
 * @date 2020-09-07
 * @time 11:42
 * @describe:
 */
public class Solution {

    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        Queue<Map.Entry<Integer, Integer>> heap = new PriorityQueue<>(k, (e1, e2) -> e2.getValue() - e1.getValue());
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            heap.offer(entry);
            if (heap.peek().getValue() < entry.getValue()) {
                heap.poll();
            }
        }
        int[] ret = new int[k];
        for (int i = 0; i < k; i++) {
            ret[i] = heap.poll().getKey();
        }
        return ret;
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // 首先想到的是两个数组归并为一个数组（有序），后拿到中位数
        // 时间复杂度 m+n  空间复杂度 m+n
        int[] arr = new int[nums1.length + nums2.length];
        // 两个指针
        int m = 0, n = 0;
        for (int i = 0; i < arr.length; i++) {
            if (m == nums1.length) {
                arr[i] = nums2[n++];
            } else if(n == nums2.length) {
                arr[i] = nums1[m++];
            } else {
                int num1 = nums1[m];
                int num2 = nums2[n];
                if (num1 < num2) {
                    arr[i] = num1;
                    m++;
                } else {
                    arr[i] = num2;
                    n++;
                }
            }
        }
        if (arr.length % 2 != 0) {
            return arr[arr.length / 2];
        }
        return (arr[arr.length / 2] + arr[arr.length / 2 - 1]) / 2.0;
    }



    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] num = new int[]{1,1,1,2,2,3,3,3,4};
        System.out.println(Arrays.toString(solution.topKFrequent(num, 3)));
        System.out.println("===================================");
        int[] nums1 = new int[]{1,3};
        int[] nums2 = new int[]{2};
        System.out.println(solution.findMedianSortedArrays(nums1, nums2));
    }
}
