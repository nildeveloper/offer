package com.sort;

/**
 * Created with IntellJ IDEA.
 * User: lhl
 * Date: 2019-02-23
 * Time: 11:33
 * Description: 冒泡排序
 */
public class Bubble<T extends Comparable<T>> extends Sort<T> {
    @Override
    public void sort(T[] nums) {
        int length = nums.length;
        // 比较 n - 1 趟
        for (int i = 0 ; i < length - 1; i++) {
            // i 记录已排序元素数量
            for (int j = 0; j < length - 1 - i; j++) {
                if (less(nums[j + 1], nums[j])) {
                    swap(nums, j, j + 1);
                }
            }
        }
    }
}
