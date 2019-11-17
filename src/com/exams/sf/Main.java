package com.exams.sf;


import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: lhl
 * Date: 2019-10-21
 * Time: 20:56
 * Description: 主方法
 */
public class Main {

    public static void main(String[] args) {
//        List<String> count = count(4);
//        System.out.println(count);
//        ListNode n1 = new ListNode(1);
//        ListNode n2 = new ListNode(-10);
//        ListNode n3 = new ListNode(3);
//        ListNode n4 = new ListNode(-2);
//        n1.next = n2;
//        n2.next = n3;
//        n3.next = n4;
//        n4.next = null;
//        ListNode head = sortList(n1);
//        while (head != null) {
//            System.out.println(head.val);
//            head = head.next;
//        }
        find(1000000).forEach(System.out::println);
    }
    
    public static List<Integer> find(int n) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i <=n; i++) {
            if (i ==0 || i % 10 == 1 || i % 10 == 5 || i % 10 == 6) {
                Long n2 = Long.valueOf(i * i);
                if (String.valueOf(n2).endsWith(String.valueOf(i))) {
                    list.add(i);
                }
            }
        }
        return list;
    }
    
    public static ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode tmp = null;
        for (ListNode p = head; p != null; p = p.next) {
            tmp = p;
            for (ListNode q = p.next; q != null; q = q.next) {
                if (Math.abs(q.val) < Math.abs(tmp.val)) {
                    tmp = q;
                }
            }
            if (tmp != p) {
                int t = tmp.val;
                tmp.val = p.val;
                p.val = t;
            }
        }
        return head;
    }
    
    
    public static List<String> count(int n) {
        List<String> list = new ArrayList<>();
        int ret = 1;
        int[] count = new int[10];
        for (int i = 1; i <= n; i++) {
            ret *= i;
            while (ret >= 10) {
                ret /= 10;
            }
            count[ret]++;
        }
        for (int i = 1; i <= 9; i++) {
            if (count[i] != 0) {
                list.add(i + "=>" + count[i]);
            }
        }
        return list;
    }
}
