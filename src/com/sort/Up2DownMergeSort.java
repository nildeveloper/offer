package com.sort;

import java.util.Arrays;

/**
 * Created with IntellJ IDEA.
 * User: lhl
 * Date: 2019-02-24
 * Time: 14:54
 * Description: 自顶向下归并排序（递归实现）
 * 将一个大数组分成两个小数组去求解。
 * 每次都将问题对半分成两个子问题，复杂度O(nlogn)
 */
public class Up2DownMergeSort<T extends Comparable<T>> extends MergeSort<T> {
    @Override
    public void sort(T[] nums) {
        aux = (T[]) new Comparable[nums.length];
        sort(nums, 0, nums.length - 1);
    }
    
    private void sort(T[] nums, int l, int h) {
        if (h <= 1 || l == h) {
            return;
        }
        int mid = l + (h - l) / 2;  // 将序列一分两半
        sort(nums, l, mid);  // 左边继续拆分归并
        sort(nums, mid + 1, h);  // 右边继续拆分归并
        merge(nums, l, mid, h);  // 将两个已排序的子序列归并
    }

    public static void main(String[] args) {
        Integer[] nums = {3,5,1,2,4,1};
        MergeSort<Integer> mergeSort = new Up2DownMergeSort<Integer>();
        mergeSort.sort(nums);
        System.out.println(Arrays.toString(nums));
    }
}
