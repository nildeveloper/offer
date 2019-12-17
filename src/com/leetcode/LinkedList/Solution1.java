package com.leetcode.LinkedList;

import org.junit.Test;

import java.util.PriorityQueue;

/**
 * Created with IntelliJ IDEA.
 * User: lhl
 * Date: 2019-10-17
 * Time: 12:26
 * Description:
 */
public class Solution1 {

    /**
     * 19. 删除链表的倒数第N个节点
     * 给出链表1->2->3->4->5->null和 n = 2.
     * 删除倒数第二个节点之后，这个链表将变成1->2->3->5->null
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode first = new ListNode(-1);
        first.next = head;
        ListNode fast = first;  // 快指针
        ListNode slow = first;  // 慢指针
        while (fast != null && n > -1) {
            fast = fast.next;  // 快指针先行k + 1步
            n--;
        }
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return first.next;
    }

    /**
     * 21. 合并两个有序链表
     * 输入：1->2->4, 1->3->4
     * 输出：1->1->2->3->4->4
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode p = null;
        if (l1 == null) return p = l2;
        if (l2 == null) return p = l1;
        if (l1.val > l2.val) {
            p = l2;
            p.next = mergeTwoLists(l1, l2.next);
        } else {
            p = l1;
            p.next = mergeTwoLists(l1.next, l2);
        }
        return p;
    }
    
    // 迭代
    public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        ListNode first = new ListNode(-1);
        ListNode p = first;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                p.next = l1;
                l1 = l1.next;
            } else {
                p.next = l2;
                l2 = l2.next;
            }
            p = p.next;
        }
        if (l1 != null) {
            p.next = l1;
        }
        if(l2 != null) {
            p.next = l2;
        }
        return first.next;
    }

    /**
     * 23. 合并K个排序链表
     * 输入:
     * [
     *   1->4->5,
     *   1->3->4,
     *   2->6
     * ]
     * 输出: 1->1->2->3->4->4->5->6
     * 1. 优先队列
     * 2. 分而治之
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        PriorityQueue<ListNode> heap = new PriorityQueue<>(lists.length, (o1, o2) -> o1.val - o2.val);
        ListNode first = new ListNode(-1);
        ListNode p = first;
        for (ListNode node : lists) {
            if (node != null) {
                heap.offer(node);
            }
        }
        while (!heap.isEmpty()) {
            // heap中最小的节点
            p.next = heap.poll();
            p = p.next;
            if (p.next != null) {
                heap.offer(p.next);
            }
        }
        return first.next;
    }
    
    // 递归方式
    public ListNode mergeLists2(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        return merge(lists, 0, lists.length - 1);
    }
    
    public ListNode merge(ListNode[] lists, int left, int right) {
        if (left == right) return lists[left];
        int mid = left + (right - left) / 2;
        // 分治
        ListNode l1 = merge(lists, left, mid);
        ListNode l2 = merge(lists, mid + 1, right);
        // 归并
        return mergeTwoLists(l1, l2);
    }

    /**
     * 24. 两两交换链表中的节点
     * 给定 1->2->3->4, 你应该返回 2->1->4->3.
     */
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode first = new ListNode(-1);
        first.next = head;
        ListNode tmp = first;
        while (tmp.next != null && tmp.next.next != null) {
            ListNode p = tmp.next;
            ListNode q = tmp.next.next;
            tmp.next = q;
            p.next = q.next;
            q.next = p;
            tmp = p;
        }
        return first.next;
    }

    /**
     * 25. K 个一组翻转链表
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        return null;
    }

    /**
     * 61. 旋转链表
     * 给定一个链表，旋转链表，将链表每个节点向右移动 k 个位置，其中 k 是非负数。
     */
    public ListNode rotateRight(ListNode head, int k) {
        return null;
    }


}
