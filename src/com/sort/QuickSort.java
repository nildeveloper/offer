package com.sort;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: lhl
 * Date: 2019-02-24
 * Time: 15:12
 * Description: 快速排序
 * 通过一个切分元素将数组分为两个子数组，左子数组小于等于切分元素
 * 有子数组大于等于切分元素，将这啷个子数组排序也就将整个数组排序了
 */
public class QuickSort<T extends Comparable<T>> extends Sort<T> {

    /**
     * 随机打乱数组元素
     * @param nums
     */
    private void shuffle(T[] nums) {
        List<T> list = Arrays.asList(nums);
        Collections.shuffle(list);
        list.toArray(nums);
    }

    /**
     * 一次划分
     * 1. 从左向右扫描找到第一个 >= 轴值得元素 
     * 2. 从右向左扫描找到第一个 <= 轴值得元素
     * 3. 交换两个元素
     * 4. 两指针相遇时，切分元素a[l] 与 a[j]交换
     * @param nums
     * @param l
     * @param h
     * @return
     */
    private int partition(T[] nums, int l, int h) {
        int i = l;
        int j = h + 1;
        while (true) {
            while (less(nums[++i], nums[l]) && i != h);  // 左侧扫描寻找 >= 轴值得元素 
            while (less(nums[l], nums[--j]) && j != l);  // 右侧扫描寻找 <= 轴值得元素
            if (i >= j) {  // 当指针 i > j 时跳出循环
                break;
            }
            swap(nums, i, j);  // 否则交换两元素 
        }
        swap(nums, l, j);  // 最后两指针相遇， 执行切分元素nums[l] 与 nums[j] 进行交换
        return j;  //  j 为轴值记录的最终位置
    }

    /**
     * 递归进行排序
     * @param nums
     * @param l
     * @param h
     */
    private void sort(T[] nums, int l, int h) {
        if (h <= 1) return ;
        int pivot = partition(nums, l, h);  // 获取一次划分后轴值所在位置
        sort(nums, l, pivot - 1);  // 进行递归
        sort(nums, pivot + 1, h);
    }

    @Override
    public void sort(T[] nums) {
        shuffle(nums);
        sort(nums, 0, nums.length - 1);
    }
}
