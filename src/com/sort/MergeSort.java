package com.sort;

/**
 * Created with IntellJ IDEA.
 * User: lhl
 * Date: 2019-02-23
 * Time: 16:16
 * Description: 归并排序
 * 归并排序的思想是将数组分成两部分，分别进行排序，然后归并起来。
 */
public abstract class MergeSort<T extends Comparable<T>> extends Sort<T> {
    
    protected T[] aux;

    /**
     * 归并方法将数组中两个已经排序的部分归并成一个。
     * @param nums
     * @param l
     * @param m
     * @param h
     */
    protected void merge(T[] nums, int l, int m, int h) {
        
        int i = l, j = m + 1;
        
        for (int k = l; k <= h; k++) {
            aux[k] = nums[k];  // 将数据复制到辅助数组
        }
        
        for (int k = l; k <= h; k++) {
            if (i > m) {  // 左边序列已经比较完，右边序列有剩余，将剩余直接拿到完整序列最后
                nums[k] = aux[j++];
            } else if (j > h) {  // 右边序列已经比较完，左边序列有剩余，将剩余元素直接拿到完整序列后面
                nums[k] = aux[i++];
            } else if (aux[i].compareTo(nums[j]) <= 0) {  // 比较两个序列元素，小的拿到完整序列
                nums[k] = aux[i++];  // 先进行这一步，保证稳定性
            } else {
                nums[k] = aux[j++];
            }
        }
    }
    
}
