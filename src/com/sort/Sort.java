package com.sort;

/**
 * Created with IntellJ IDEA.
 * User: lhl
 * Date: 2019-02-23
 * Time: 11:02
 * Description:
 * 待排序的元素需要实现 Java 的 Comparable 接口，该接口有 compareTo() 方法，可以用它来判断两个元素的大小关系。
 研究排序算法的成本模型时，统计的是比较和交换的次数。
 使用辅助函数 less() 和 swap() 来进行比较和交换的操作，使得代码的可读性和可移植性更好。
 */
public abstract class Sort<T extends Comparable<T>>{  // 类型 T 都为 Comparable<T> 的子类
    
    public abstract void sort(T[] nums);
    
    // 比较：判断 v 是否 < w
    protected boolean less(T v, T w) {
        return v.compareTo(w) < 0;
    }
    
    // 交换：交换数组中 i、 j 的位置
    protected void swap(T[] nums, int i, int j) {
        T t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
    
}
