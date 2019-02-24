package com.sort;

/**
 * Created with IntellJ IDEA.
 * User: lhl
 * Date: 2019-02-23
 * Time: 15:38
 * Description: 插入排序
 */
public class Insertion<T extends Comparable<T>> extends Sort<T> {


    /**
     * 将当前元素插入到左侧已排好序的数组中，使得插入后左侧数组依然有序
     * @param nums
     */
    @Override
    public void sort(T[] nums) {
        int length = nums.length;
        // N 个数 需要排 N - 1 个数
        for (int i = 1; i < length; i++) {
            for (int j = i; j > 0 && less(nums[j], nums[j - 1]); j--) {
                swap(nums, j - 1, j);
            }
        }
    }
}
