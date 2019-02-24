package com.sort;

/**
 * Created with IntellJ IDEA.
 * User: lhl
 * Date: 2019-02-23
 * Time: 15:52
 * Description: 希尔排序
 */
public class Shell<T extends Comparable<T>> extends Sort<T> {

    /**
     * 插入排序的改进，通过交换不相邻的元素，每次可以将逆序数量减少大于1
     * @param nums
     */
    @Override
    public void sort(T[] nums) {
        int length = nums.length;
        // 以相聚某个增量的记录组成一个子序列， 此处以 d 为增量
        for (int d = 2 / length; d >= 1; d = d / 2) {
            // 以 d 为增量在子序列内进行插入排序
            for (int i = d; i < length; i++) {
                for (int j = i; j >= d && less(nums[j], nums[j - d]); j -= d) {
                    swap(nums, j, j - d);
                }
            }
        }
    }
}
