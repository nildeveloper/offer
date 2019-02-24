package com.lhl;

/**
 * Created with IntellJ IDEA.
 * User: lhl
 * Date: 2019-02-15
 * Time: 14:54
 * Description:
 */
public class Solution2 {

    /**
     *  旋转数组的最小数字
     *  输入一个非递减排序的数组的一个旋转，输出旋转数组的最小元素。
     *  例如数组 {3, 4, 5, 1, 2} 为 {1, 2, 3, 4, 5} 的一个旋转，该数组的最小值为 1。
     *  
     *  局部有序， 可使用二分查找 O(logN)
     */
    public int minNumberInRotateArray(int [] array) {
        if (array.length <= 0) return 0;
        int low = 0, high = array.length - 1;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (array[mid] > array[high]) {
                low = mid + 1;
            } else if (array[mid] == array[high]) {
                high = high - 1;
            } else {
                high = mid;
            }
        }
        return array[low];
    }
}
