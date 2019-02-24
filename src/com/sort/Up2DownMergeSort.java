package com.sort;

/**
 * Created with IntellJ IDEA.
 * User: lhl
 * Date: 2019-02-24
 * Time: 14:54
 * Description: 自顶向下归并排序
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
        if (h <= 1) {
            return;
        }
        int mid = l + (h - l) / 2;
        sort(nums, l, mid);
        sort(nums, mid + 1, h);
        merge(nums, l, mid, h);
    }
}
