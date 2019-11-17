package com.leetcode.bfs;

import javafx.scene.transform.Rotate;
import org.junit.Test;
import sun.reflect.generics.tree.Tree;

import java.security.cert.PolicyQualifierInfo;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: lhl
 * Date: 2019-10-26
 * Time: 15:28
 * Description:
 */
public class Solution1 {

    /**
     * 101. 对称二叉树
     */
    public boolean isSymmetric(TreeNode root) {
        return isMirror(root, root);
    }
    
    private boolean isMirror(TreeNode node1, TreeNode node2) {
        if (node1 == null && node2 == null) {
            return true;
        }
        if (node1 == null || node2 == null) {
            return false;
        }
        return (node1.val == node2.val) && isMirror(node1.left, node2.right) && isMirror(node1.right, node2.left);
    }
    
    public boolean isSymmetricWithBfs(TreeNode root) {
        if (root == null) {
            return true;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node1 = queue.poll();
            TreeNode node2 = queue.poll();
            if (node1 == null && node2 == null) continue;
            if (node1 == null || node2 == null) return false;
            if (node1.val != node2.val) return false;
            queue.offer(node1.left);
            queue.offer(node2.right);
            queue.offer(node1.right);
            queue.offer(node2.left);
        }
        return true;
    }

    /**
     * 102. 二叉树的层次遍历
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ret = new ArrayList<>();
        if (root == null) return ret;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            List<Integer> list = new ArrayList<>();
            int size = queue.size();
            while (size-- != 0) {
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
     * 103. 二叉树的锯齿形层次遍历
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ret = new ArrayList<>();
        if (root == null) return ret;
        Queue<TreeNode> queue = new LinkedList<>();
        boolean reverse = false;
        queue.offer(root);
        while (! queue.isEmpty()) {
            List<Integer> list = new ArrayList<>();
            int size = queue.size();
            while (size-- != 0) {
                TreeNode node = queue.poll();
                if (node == null) continue;
                list.add(node.val);
                queue.offer(node.left);
                queue.offer(node.right);
            }
            if (list.size() > 0) {
                if (reverse) {
                    Collections.reverse(list);
                }
                reverse = !reverse;
                ret.add(list);
            }
        }
        return ret;
    }

    /**
     * 107. 二叉树的层次遍历 II
     */
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>>ret = new ArrayList<>();
        if (root == null) {
            return ret;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            List<Integer> list = new ArrayList<>();
            int size = queue.size();
            while (size-- != 0) {
                TreeNode node = queue.poll();
                if (node == null) continue;
                list.add(node.val);
                queue.offer(node.left);
                queue.offer(node.right);
            }
            if (list.size() > 0) {
                ret.add(list);
            }
        }
        Collections.reverse(ret);
        return ret;
    }

    /**
     * 111. 二叉树的最小深度
     * 
     */
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null) return minDepth(root.right) + 1;
        if (root.right == null) return minDepth(root.left) + 1;
        return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
    }

    @Test
    public void test() {
        List<List<Integer>> ret = new ArrayList<>();
        List<Integer> list1 = Arrays.asList(1,2,3);
        List<Integer> list2 = Arrays.asList(3,4,5);
        ret.add(list1);
        ret.add(list2);
        Collections.reverse(ret);
        System.out.println(ret);
    }
}
