package com.jianzhi2;

import com.algorithm.Solution;
import sun.plugin.javascript.navig.Link;

import java.util.*;

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
        return pHead;
    }

    /**
     * 合并两个排序的链表
     * @param list1
     * @param list2
     * @return
     */
    public ListNode Merge(ListNode list1,ListNode list2) {
        ListNode head = null;
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }
        if (list1.val < list2.val) {
            list1.next = Merge(list1.next, list2);
            return list1;
        } else {
            list2.next = Merge(list1, list2.next);
            return list2;
        }
    }
    
    public ListNode Merge1(ListNode list1, ListNode list2) {
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
            p = p.next;
        }
        if (list1.next != null) {
            p.next = list1;
        }
        if (list2.next != null) {
            p.next = list2;
        }
        return head.next;
    }

    /**
     * 二叉树A、B
     * 判断B是不是A的子结构
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
        if (root2 == null) {
            return true;
        }
        if (root1 == null) {
            return false;
        }
        if (root1.val != root2.val){
            return false;
        }
        return isSubTree(root1.left, root2.left) && isSubTree(root1.right, root2.right);
    }

    /**
     * 镜像二叉树
     * @param root
     */
    public void Mirror(TreeNode root) {
        if (root == null) {
            return;
        }
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        Mirror(root.left);
        Mirror(root.right);
    }

    /**
     * 判断是否是入栈顺序的出栈顺序
     * @param pushA
     * @param popA
     * @return
     */
    public boolean IsPopOrder(int [] pushA,int [] popA) {
        if (pushA.length == 0 || popA.length == 0) {
            return false;
        }
        LinkedList<Integer> stack = new LinkedList<>();
        int popIndex = 0;
        for (int i = 0; i < pushA.length; i++) {
            stack.push(pushA[i]);
            while (popIndex < popA.length && !stack.isEmpty() && stack.peek() == popA[popIndex]) {
                stack.pop();
                popIndex++;
            }
        }
        return stack.isEmpty();
    }

    /**
     * 判断一个数组 是否是二叉搜索树后序遍历结果
     * 二叉搜索树：中序遍历结果是一个有序序列
     * 特点：左子树上所有结点的值均小于它的根结点的值； 右子树上所有结点的值均大于它的根结点的值
     * @param sequence
     * @return
     */
    public boolean VerifySquenceOfBST(int [] sequence) {
        if (sequence.length == 0) {
            return false;
        }
        if (sequence.length == 1) {
            return true;
        }
        return isBst(sequence, 0, sequence.length - 1);
    }
    
    // 后序遍历：左右根  根节点在最后
    // 思想就是  左边都小于根节点， 右边都大于根节点
    public boolean isBst(int[] sequence, int left, int right) {
        // 跳出条件
        if (left >= right) {
            return true;
        }
        int rootVal = sequence[right];
        int currentIndex = left;
        // 找到左右分界点  左边都小于根节点
        while (sequence[currentIndex] < rootVal) {
            currentIndex++;
        }
        // 找到分界点后判断  右边都大于根节点
        for (int i = 0; i < right; i++) {
            if (sequence[i] < rootVal) {
                return false;
            }
        }
        // 递归进行判断
        return isBst(sequence, left, currentIndex - 1) && isBst(sequence, currentIndex, right);
    }

    /**
     * 二叉树根节点和一个整数，找和等于这个整数的全部路径
     * 深搜
     * @param root
     * @param target
     * @return
     */
    private ArrayList<ArrayList<Integer>> ret = new ArrayList<>();
    private ArrayList<Integer> list = new ArrayList<>();
    
    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root, int target) {
        if (root == null) {
            return ret;
        }
        list.add(root.val);
        target -= root.val;
        // 到达叶子节点，且符合题目要求
        if (target == 0 && root.left == null && root.right == null) {
            ret.add(new ArrayList<>(list));
        }
        FindPath(root.left, target);
        FindPath(root.right, target);
        // 处理到叶子节点后，不符合需求，回溯到父节点
        list.remove(list.size() - 1);
        return ret;
    }

    /**
     * 
     * 求滑动窗口中最大值
     * @param num
     * @param size
     * @return
     */
    public ArrayList<Integer> maxInWindows(int [] num, int size) {
        ArrayList<Integer> ret = new ArrayList<>();
        if (num.length < size || size < 1) {
            return ret;
        }
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int i = 0; i < size; i++) {
            queue.offer(num[i]);
        }
        ret.add(queue.peek());
        for (int i = 0, j = i + size; j < num.length; i++, j++) {
            queue.remove(num[i]);
            queue.offer(num[j]);
            ret.add(queue.peek());
        }
        return ret;
    }
    
    
    public static void main(String[] args) {
        Solution1 solution1 = new Solution1();
//        StringBuffer str = new StringBuffer("We Are Happy");
//        System.out.println(solution1.replaceSpace(str));
        solution1.reConstructBinaryTree(new int[]{1,2,4,7,3,5,6,8}, new int[]{4,7,2,1,5,3,8,6});
    }
}