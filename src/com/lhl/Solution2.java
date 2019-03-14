package com.lhl;

import java.util.Arrays;

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

    /**
     * 输入一个整数，输出该数二进制表示中1的个数。其中负数用补码表示。
     * 按位与操作，都为 1 才等于 1
     */
    public int NumberOf1(int n) {
        int cnt = 0;
        while (n != 0) {
            cnt++;
            n = n & (n - 1);
        }
        return cnt;
    }
    
    public int NumberOfOne(int n) {
        return Integer.bitCount(n);
    }

    /**
     * 链表反转
     */
    public ListNode ReverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode pHead = head;
        head = head.next;
        pHead.next = null;
        ListNode p = null;
        while (head != null) {
            p = head.next;
            head.next = pHead;
            pHead = head;
            head = p;
        }
        return pHead;  // 最后是 pHead 指向最后最后一个节点
    }

    /**
     * 调整数组顺序，使奇数位于偶数前面
     */
    public void reOrderArray(int [] array) {
        int oddCount = 0;
        // 统计出数组中奇数个数
        for (int i = 0 ; i < array.length; i++) {
            if (array[i] % 2 != 0) {
                oddCount++;
            }
        }
        int[] copy = array.clone();  // 克隆原数组
        int i = 0, j = oddCount;
        for (int num : copy) {
            if (num % 2 != 0) {
                array[i++] = num;  // 奇数扔数组前面
            } else {
                array[j++] = num;  // 偶数扔数组后面
            }
        }
    }

    /**
     * 输入一个链表，输出链表中倒数第 k 个节点
     * @param head
     * @param k
     * @return
     */
    public ListNode FindKthToTail(ListNode head,int k) {
        ListNode p1 = head;
        ListNode p2 = head;
        while (k-- != 0) {
            if (p1 == null) {
                return null;
            }
            p1 = p1.next;
        }
        while (p1 != null) {
            p1 = p1.next;
            p2 = p2.next;
        }
        return p2;
    }

    /**
     * 输入两个单调递增的链表，输出两个链表合并后的链表（单调不减）
     * 递归
     * @param list1
     * @param list2
     * @return
     */
    public ListNode Merge(ListNode list1,ListNode list2) {
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }
        if (list1.val < list2.val) {
            list1.next = Merge(list1.next,list2);
            return list1;
        } else {
            list2.next = Merge(list1, list2.next);
            return list2;
        }
    }

    // 循环迭代版本
    public ListNode Merge2(ListNode list1, ListNode list2) {
        ListNode head = new ListNode(-1);
        ListNode p = head;
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                p.next = list1;
                list1 = list1.next;
            } else {
                p.next = list2;
                list2 = list2.next;
            }
        }
        if (list1 != null) {
            p.next = list1;
        }
        if (list2 != null) {
            p.next = list2;
        }
        return head.next;
    }

    /**
     * 树的子结构
     * 输入两棵二叉树A，B，判断B是不是A的子结构。
     * @param root1
     * @param root2
     * @return
     */
    public boolean HasSubtree(TreeNode root1,TreeNode root2) {
        if (root1 == null || root2 == null) {
            return false;
        }
        return isSubTree(root1, root2) || isSubTree(root1.left, root2) || isSubTree(root1.right, root2);
    }
    
    public boolean isSubTree(TreeNode root1, TreeNode root2) {
        if (root2 == null) return true;
        if (root1 == null) return false;
        if (root2.val != root1.val) return false;
        return isSubTree(root1.left, root2.left) && isSubTree(root1.right, root2.right);
    }

    /**
     * 二叉树的镜像
     * 操作给定的二叉树，将其变换为源二叉树的镜像。
     * 递归思想
     * @param root
     */
    public void Mirror(TreeNode root) {
        if (root == null) return;
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        Mirror(root.left);
        Mirror(root.right);
        
    }

    
    

    public static void main(String[] args) {
        int[] arr = new int[]{2,3,4,5,6};
        Solution2 solution2 = new Solution2();
//        solution2.reOrderArray(arr);
//        System.out.println(Arrays.toString(arr));
//        System.out.println(func(15,20));
        
    }
    
}
