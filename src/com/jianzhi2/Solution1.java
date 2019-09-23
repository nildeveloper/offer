package com.jianzhi2;

import com.algorithm.Solution;
import sun.plugin.javascript.navig.Link;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: haolu.liu
 * Date: 2019-09-21 14:21
 * Description: 剑指 Offer 二刷
 */
public class Solution1 {

    /**
     * 二维数组中查找
     * @param target
     * @param array
     * @return
     */
    public boolean Find(int target, int[][] array) {
        if (array == null || array.length == 0 ||  array[0] == null) {
            return false;
        }
        int row = array.length;
        int col = array[0].length;
        // 第一行 最后一列
        int r = 0;
        int c = col - 1;
        // 没有到边界
        while (r <= row - 1 && c >= 0) {
            if (target == array[r][c]) {
                return true;
            } else if (target < array[r][c]) {
                c--;
            } else {
                r++;
            }
        }
        return false;
    }

    /**
     * 替换空格
     * @param str
     * @return
     */
    public String replaceSpace(StringBuffer str) {
        // 原字符串末尾位置
        int p1 = str.length() -1;
        // 必须是 <= p1  而不是 < str.length()  因为length一直在变
        for (int i = 0; i <= p1; i++) {
            if (str.charAt(i) == ' ') {
                // 遇到一个空格，末尾添加俩空格
                str.append("  ");
            }
        }
        // 新字符串末尾
        int p2 = str.length() - 1;
        while (p1 >= 0 && p2 > p1) {
            // 获取p1 指向字符
            char c = str.charAt(p1--);
            if (c == ' ') {
                str.setCharAt(p2--, '0');
                str.setCharAt(p2--, '2');
                str.setCharAt(p2--, '%');
            } else {
                str.setCharAt(p2--, c);
            }
        }
        return str.toString();
    }

    /**
     * 从尾到头打印链表(栈)
     * @param listNode
     * @return
     */
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        ArrayList<Integer> ret = new ArrayList<>();
        if (listNode == null) {
            return ret;
        }
        LinkedList<Integer> stack = new LinkedList<>();
        while (listNode != null) {
            stack.push(listNode.val);
            listNode = listNode.next;
        }
        while (!stack.isEmpty()) {
            ret.add(stack.pop());
        }
        return ret;
    }

    /**
     * 想到栈 就想到递归（递归调用栈）
     * @param root
     * @return
     */
    public ArrayList<Integer> printListFromTailToHead2(ListNode root) {
        ArrayList<Integer> ret = new ArrayList<>();
        if (root == null) {
            return ret;
        }
        if (root != null) {
            ret.addAll(printListFromTailToHead2(root.next));
            ret.add(root.val);
        }
        return ret;
    }

    /**
     * 重建二叉树（前序、中序构建二叉树）
     * @param pre
     * @param in
     * @return
     */
    public TreeNode reConstructBinaryTree(int [] pre,int [] in) {
        int inLen = in.length;
        if (inLen == 0) {
            return null;
        }
        // 记录中序遍历中根节点位置（左根右）
        int rootIndex = 0;
        for (int i = 0; i < in.length; i++) {
            if (in[i] == pre[0]) {
                rootIndex = i;
                break;
            }
        }
        List<Integer> preLeft = new ArrayList<>();
        List<Integer> preRight = new ArrayList<>();
        List<Integer> inLeft = new ArrayList<>();
        List<Integer> inRight = new ArrayList<>();
        // 中序遍历，根节点两边数据分开保存
        for (int i = 0; i < rootIndex; i++) {
            inLeft.add(in[i]);
            preLeft.add(pre[i + 1]);  // 0位置为根节点
        }
        for (int i = rootIndex + 1; i < inLen; i++) {
            inRight.add(in[i]);
            preRight.add(pre[i]);
        }
        // 创建根节点
        TreeNode root = new TreeNode(pre[0]);
        int[] preLeftArr = preLeft.stream().mapToInt(Integer::intValue).toArray();
        int[] inLeftArr = inLeft.stream().mapToInt(Integer::intValue).toArray();
        int[] preRightArr = preRight.stream().mapToInt(Integer::intValue).toArray();
        int[] inRightArr = inRight.stream().mapToInt(Integer::intValue).toArray();
        // 递归创建
        root.left = reConstructBinaryTree(preLeftArr, inLeftArr);
        root.right = reConstructBinaryTree(preRightArr, inRightArr);
        return root;
    }

    /**
     * 调整数组元素位置（奇数位于偶数前）
     * @param array
     */
    public void reOrderArray(int [] array) {
        int oddCount = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] % 2 != 0) {
                oddCount++;
            }
        }
        int[] copy = array.clone();
        int i = 0, j = oddCount;
        for (int number : copy) {
            if (number % 2 != 0) {
                array[i++] = number;
            } else {
                array[j++] = number;
            }
        }
    }


    /**
     * 链表倒数第k个节点（双指针）
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
     * 翻转链表
     * @param head
     * @return
     */
    public ListNode ReverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode next = head.next;
        // TODO
        return null;
    }

    
    public static void main(String[] args) {
        Solution1 solution1 = new Solution1();
//        StringBuffer str = new StringBuffer("We Are Happy");
//        System.out.println(solution1.replaceSpace(str));
        solution1.reConstructBinaryTree(new int[]{1,2,4,7,3,5,6,8}, new int[]{4,7,2,1,5,3,8,6});
    }
}