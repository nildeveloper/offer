package com.sort;

import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: lhl
 * Date: 2019-02-24
 * Time: 17:29
 * Description:堆排序（大根堆）  建堆 -> 处理堆顶记录 -> 调整剩余元素
 * 建堆：无序数组，从左到右遍历数组进行上浮操作；从右到左遍历数组进行下沉操作（高效）
 * 处理堆顶记录：堆顶记录与最后一个记录进行交换
 * 调整剩余元素：进行下沉操作维持现有状态
 */
public class HeapSort<T extends Comparable<T>> extends Sort<T> {

    /**
     * 数组第 0 个位置不使用
     * @param nums
     */
    @Override
    public void sort(T[] nums) {
        int N = nums.length - 1;
        // 忽略叶子节点（叶子节点已经是堆），叶子节点不要下沉
        for (int k = N / 2; k >= 1; k--) {
            sink(nums, k, N);
        }
        // 重复执行移走堆顶和重建堆的操作
        while (N > 1) {
            swap(nums, 1, N--);
            sink(nums, 1, N);
        }
    }


    /**
     * 下沉
     * 当一个节点比子节点来的小，需要不断向下进行比较和交换
     * 与两个子节点中较大的进行交换
     * @param k
     */
    private void sink(T[] nums, int k, int N) {
        while (2 * k <= N) {  // 筛选还没有进行到叶子节点
            int j = 2 * k;  // k指向被筛选节点，j默认指向左子节点
            if (j < N && less(nums, j, j + 1)) {
                j++;  // 若右子节点值较大，则 j 记录右子节点
            }
            // 如果该节点不小于子节点了 则停止下沉
            if (!less(nums, k, j)) break;
            // 当前节点与节点 j 交换
            swap(nums, k, j);
            k = j;
        }
    }

    private boolean less(T[]nums, int i, int j) {
        return nums[i].compareTo(nums[j]) < 0;
    }

    public static void main(String[] args) {
        Integer[] nums = {0,3,5,1,2,4,1};
        HeapSort<Integer> sort = new HeapSort<Integer>();
        sort.sort(nums);
        System.out.println(Arrays.toString(nums));
    }
    
}
