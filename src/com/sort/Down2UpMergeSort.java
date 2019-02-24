package com.sort;

/**
 * Created with IntellJ IDEA.
 * User: lhl
 * Date: 2019-02-24
 * Time: 15:02
 * Description: 自底向上归并排序
 * 先归并微型数组，然后成对归并得到的微型数组
 */
public class Down2UpMergeSort<T extends Comparable<T>> extends MergeSort{

    @Override
    public void sort(Comparable[] nums) {
        int N= nums.length;
        aux = (T[]) new Comparable[N];
        
        for (int sz = 1; sz <N; sz += sz) {
            for (int lo = 0; lo < N -sz; lo += sz) {
                merge(nums, lo, lo + sz - 1, Math.min(lo + sz + sz - 1, N - 1));
            }
        }
    }
}
