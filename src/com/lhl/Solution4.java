package com.lhl;

import org.junit.Test;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: lhl
 * Date: 2019-03-14
 * Time: 15:36
 * Description:
 */
public class Solution4 {

    /**
     * 栈的压入弹出序列
     * 输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否可能为该栈的弹出顺序。
     * @param pushA
     * @param popA
     * @return
     */
    public boolean IsPopOrder(int [] pushA,int [] popA) {
        if (pushA.length == 0 || popA.length == 0) {
            return false;
        }
        Stack<Integer> stack = new Stack<Integer>();
        int popIndex = 0;
        for (int i = 0; i < pushA.length; i++) {
            stack.push(pushA[i]);
            while (popIndex < pushA.length && !stack.isEmpty() && stack.peek() == popA[popIndex]) {
                stack.pop();
                popIndex++;
            }
        }
        return stack.isEmpty();
    }


    /**
     * 从上往下打印二叉树
     * 从上往下打印出二叉树的每个节点，同层节点从左至右打印。
     * @param root
     * @return
     */
    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        if (root == null) {
            return list;
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            list.add(node.val);
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        return list;
    }


    /**
     * 把二叉树打印成多行
     * @param pRoot
     * @return
     */
    public ArrayList<ArrayList<Integer>> printLine(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> ret = new ArrayList<ArrayList<Integer>>();
        if (pRoot == null) {
            return ret;
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(pRoot);
        while (!queue.isEmpty()) {
            ArrayList<Integer> list = new ArrayList<Integer>();
            int cnt = queue.size();
            while (cnt-- != 0) {  // 控制每层节点个数
                TreeNode node = queue.poll();
                if (node == null) continue;
                list.add(node.val);
                queue.offer(node.left);
                queue.offer(node.right);
            }
            if (list.size() != 0) {
                ret.add(list);
            }
        }
        return ret;
    }

    /**
     * 按之字形打印二叉树
     * 参考净骚操作！！！哈哈哈哈哈！！！
     * @param pRoot
     * @return
     */
    public ArrayList<ArrayList<Integer> > PrintZhi(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> ret = new ArrayList<ArrayList<Integer>>();
        if (pRoot == null) {
            return ret;
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(pRoot);
        boolean reverse = false;  // 是否反转标志位
        while (!queue.isEmpty()) {
            ArrayList<Integer> list = new ArrayList<Integer>();
            int cnt = queue.size();
            while (cnt -- != 0) {
                TreeNode node = queue.poll();
                if (node == null) continue;
                list.add(node.val);
                queue.offer(node.left);
                queue.offer(node.right);
            }
            if (reverse) {
                Collections.reverse(list);
            }
            reverse = !reverse;
            if (list.size() != 0) {
                ret.add(list);
            }
        }
        return ret;
    }

    /**
     * 二叉搜索树后续遍历序列 (左 < 根 < 右   后续：左右根)
     * 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果。如果是则输出Yes,否则输出No
     * @param sequence
     * @return
     */
    public boolean VerifySquenceOfBST(int [] sequence) {
        if (sequence.length == 0) return false;
        if (sequence.length == 1) return true;
        return verify(sequence, 0, sequence.length - 1);
    }
    
    private boolean verify(int[] sequence, int left, int right) {
        if (left >= right) return true;
        int currentIndex = left;
        int rootVal = sequence[right];  // 根节点
        // 左边：都小于根节点
        while (currentIndex < right && sequence[currentIndex] <= rootVal) { // 左 右 根
            currentIndex++;  // 找到左右分界点
        }
        // 右边：都大于根节点
        for(int i = currentIndex; i < right; i++) {
            if (sequence[i] < rootVal) {
                return false;  // 若右边有 < 根节点的则不是
            }
        }
        return verify(sequence, left, currentIndex - 1) && verify(sequence, currentIndex, right - 1);
    }


    /**
     * 二叉树中和为某一值的路径
     * 输入一颗二叉树和一个整数，打印出二叉树中结点值的和为输入整数的所有路径。
     * @param root
     * @param target
     * @return
     */
    private ArrayList<ArrayList<Integer>> ret = new ArrayList<ArrayList<Integer>>();
    private ArrayList<Integer> list = new ArrayList<Integer>();
    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root, int target) {
        if (root == null) return ret;
        list.add(root.val);
        target -= root.val;
        if (target == 0 && root.left == null && root.right == null) {  // 符合条件的一条路径
            ret.add(new ArrayList<Integer>(list));
        }
        FindPath(root.left, target);
        FindPath(root.right, target);
        list.remove(list.size() - 1);  // 处理到叶子节点，若不合适，则回溯到其父节点
        return ret;
    }


    /**
     * 二叉搜索树与双向链表
     * @param pRootOfTree
     * @return
     */
    //双向链表的左边头结点和右边头节点
    TreeNode leftHead = null;
    TreeNode rightHead = null;
    public TreeNode convert(TreeNode pRootOfTree) {
        // 递归调用中叶子节点的左右节点返回null
        if (pRootOfTree == null) {
            return null;
        }
        // 第一次运行，最左边叶子节点为链表第一个节点
        convert(pRootOfTree.left);
        if (rightHead == null) {
            leftHead = pRootOfTree;
            rightHead = pRootOfTree;
        } else {
            // 把根节点插入到双向链表右边，rightHead向后移动
            rightHead.right = pRootOfTree;
            pRootOfTree.left = rightHead;
            rightHead = pRootOfTree;
        }
        // 把右边叶子接待呢也插入到双向链表
        convert(pRootOfTree.right);
        return leftHead;
    }
    

    @Test
    public void testPrintFromTopToBottom() {
        TreeNode root = new TreeNode(1);
        TreeNode left = new TreeNode(2);
        TreeNode right = new TreeNode(3);
        root.left = left;
        root.right = right;
        System.out.println(PrintFromTopToBottom(root));
        System.out.println(printLine(root));
        System.out.println(PrintZhi(root));
    }
    
    
    @Test
    public void testInPopOrder() {
        int[] a = new int[]{1,2,3,4,5};
        int[] b = new int[]{4,5,3,2,1};
        int[] c = new int[]{4,3,5,1,2};
        System.out.println(IsPopOrder(a, b));
        System.out.println(IsPopOrder(a, c));
    }
}
