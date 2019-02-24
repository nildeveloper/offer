package com.sort;

/**
 * Created with IntellJ IDEA.
 * User: lhl
 * Date: 2019-02-23
 * Time: 11:18
 * Description: 选择排序
 */
public class Selection<T extends Comparable<T>> extends Sort<T>{
    
    @Override
    public void sort(T[] nums) {
        int length = nums.length;
        for (int i = 0 ; i < length - 1; i++) {
            int min = i;  // 假设 i 为最小元素下标
            for (int j = i + 1; j < length; j++) {
                // 向后寻找剩余元素中最小元素下标
                if (less(nums[j], nums[min])) {
                    min = j;
                }
            }
            swap(nums, i, min);  // 将最小元素拿到最前面
        }
    }
}
