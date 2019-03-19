package com.lhl;

import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * Created with IntelliJ IDEA.
 * User: lhl
 * Date: 2019-03-16
 * Time: 16:52
 * Description:
 */
public class Solution5 {

    /**
     * 最小的 k 个数
     * @param nums
     * @param k
     * @return
     */
    public ArrayList<Integer> GetLeastNumbers_Solution(int[] nums, int k) {
        if (k > nums.length || k <= 0)
            return new ArrayList<>();
        // 大根堆 维护最小的k个数
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((o1, o2) -> o2 - o1);
        for (int num : nums) {
            maxHeap.add(num);
            // 如果堆大小 > k, 则需要将对顶元素弹出后继续放入
            if (maxHeap.size() > k) {
                maxHeap.poll();
            }
        }
        return new ArrayList<>(maxHeap);
    }
    
}
