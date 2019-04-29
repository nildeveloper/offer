package com.tree;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created with IntelliJ IDEA.
 * User: lhl
 * Date: 2019-04-25
 * Time: 3:29 PM
 * Description:
 */
public class BinaryTree {

    /**
     * 树节点
     */
    class TreeNode {

        public int value;

        public TreeNode left;

        public TreeNode right;

        public TreeNode(int value) {
            this.value = value;
        }
    }

    /**
     * 前序遍历
     * 递归形式
     * @param root
     */
    public void preOrder(TreeNode root) {
        if (root != null) {
            System.out.println(root.value);
            preOrder(root.left);
            preOrder(root.right);
        }
    }

    /**
     * 前序遍历非递归形式
     * 处理当前节点，完之后保存到栈中，遍历完左子树后，元素出栈，遍历其右子树
     * @param root
     */
    public void preOrderWihStack(TreeNode root) {
        if (root == null) {
            return;
        }
        LinkedList<TreeNode> stack = new LinkedList<>();  // 用LinkedList模拟栈
        while (true) {
            while (root != null) {
                System.out.println(root.value);  // 打印值
                stack.push(root);  // 入栈
                root = root.left;  // 遍历左子树
            }
            if (stack.isEmpty()) {
                break;
            }
            root = stack.poll().right;
        }
        return;
    }

    /**
     * 中序遍历
     * 递归形式
     * @param root
     */
    public void inOrder(TreeNode root) {
        if (root != null) {
            inOrder(root.left);
            System.out.println(root.value);
            inOrder(root.right);
        }
    }

    /**
     * 中序遍历非递归形式
     * 移动到节点得左子树，完成左子树遍历后，将节点出栈处理
     * @param root
     */
    public void inOrderWithStack(TreeNode root) {
        if (root == null) {
            return;
        }
        LinkedList<TreeNode> stack = new LinkedList<>();
        while (true) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            if (stack.isEmpty()) {
                break;
            }
            root = stack.pop();
            System.out.println(root.value);
            root = root.right;
        }
        return;
    }

    /**
     * 后序遍历
     * 递归形式
     * @param root
     */
    public void postOrder(TreeNode root) {
        if (root != null) {
            postOrder(root.left);
            postOrder(root.right);
            System.out.println(root.value);
        }
    }

    
    /**
     * 后序遍历 非递归
     * 双栈法
     * 通过栈一来做节点顺序反转，将反转顺序放入到栈二
     * @param root
     */
    public void postOrderWithTwoStack(TreeNode root) {
        LinkedList<TreeNode> s1 = new LinkedList<>();
        LinkedList<TreeNode> s2 = new LinkedList<>();  // 保存最终元素 出栈顺序为 左 右 根
        TreeNode curr;
        s1.push(root);
        while (!s1.isEmpty()) {
            curr = s1.peek();
            s1.pop();
            s2.push(curr);
            if (curr.left != null) {
                s1.push(curr.left);
            }
            if (curr.right != null) {
                s1.push(curr.right);
            }
        }
        while (!s2.isEmpty()) {
            TreeNode n = s2.pop();
            System.out.println(n.value);
        }
    }

    /**
     * 后续遍历非递归形式
     * 左右根，每个节点需要访问两次，但只有在处理完右子树之后，
     * 才需要处理节点中的数据
     * @param root
     */
    public void postOrderWithStack(TreeNode root) {
        LinkedList<TreeNode> stack = new LinkedList<>();
        while (true) {
            if (root != null) {
                stack.push(root.left);
                root = root.left;
            } else {
                if (stack.isEmpty()) {
                    System.out.println("stack is empty!");
                    return;
                } else if (stack.peek().right == null) {
                    root = stack.pop();
                    System.out.println(root.value);
                    if (root == stack.peek().right) {
                        System.out.println(stack.peek().right);
                        stack.pop();
                    }
                }
            }
            if (!stack.isEmpty()) {
                root = stack.peek().right;
            } else {
                root = null;
            }
        }
    }

    /**
     * 层序遍历
     * @param root
     */
    public void levelOrder(TreeNode root) {
        if (root == null) return;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            System.out.println(node.value);
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
    }
    
}
