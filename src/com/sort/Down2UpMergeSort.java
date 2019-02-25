package com.sort;

import java.util.Arrays;

/**
 * Created with IntellJ IDEA.
 * User: lhl
 * Date: 2019-02-24
 * Time: 15:02
 * Description: 自底向上归并排序
 * 先归并微型数组，然后成对归并得到的微型数组
 * n 个待排序记录看成 n 个长度为 1 的有序序列
 * 然后两两归并
 */
public class Down2UpMergeSort<T extends Comparable<T>> extends MergeSort{

    @Override
    public void sort(Comparable[] nums) {
        int N= nums.length;
        aux = (T[]) new Comparable[N];
        
        for (int sz = 1; sz < N; sz += sz) {  // 这里 sz 就是当前序列长度
            for (int lo = 0; lo < N - sz; lo += sz + sz) {  // lo 表示已经归并多少元素
                merge(nums, lo, lo + sz - 1, Math.min(lo + sz + sz - 1, N - 1));
            }
        }
    }


    public static void main(String[] args) {
        Integer[] nums = {60,20,10,50,15,30,55};
        MergeSort<Integer> mergeSort = new Down2UpMergeSort<Integer>();
        mergeSort.sort(nums);
        System.out.println(Arrays.toString(nums));
    }
}
