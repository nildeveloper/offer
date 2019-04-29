package com.lhl;


import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: lhl
 * Date: 2019-02-12
 * Time: 13:15
 * Description:
 */
public class Solution1 {

    /**
     * 二维数组中查找
     * 在一个二维数组中（每个一维数组的长度相同），每一行都按照从左到右递增的顺序排序，
     * 每一列都按照从上到下递增的顺序排序。请完成一个函数，输入这样的一个二维数组和一
     * 个整数，判断数组中是否含有该整数。
     * 
     * Consider the following matrix:
     [
         [1,   4,  7, 11, 15],
         [2,   5,  8, 12, 19],
         [3,   6,  9, 16, 22],
         [10, 13, 14, 17, 24],
         [18, 21, 23, 26, 30]
     ]
     Given target = 5, return true.
     Given target = 20, return false.
     
     要求时间复杂度 O(M + N)，空间复杂度 O(1)。
     根据题意可知，此矩阵一个数左边的数都比他小，下面的数都比它大。所以从右上角开始查找，
     根据 target 与当前数大小关系来进行比较查找
     */
    public boolean Find(int target, int [][] array) {
        if (array == null || array.length == 0 || array[0].length == 0) {
            return false;
        }
        int row = array.length;
        int col = array[0].length;
        int r = 0;  // 第一行
        int c = col - 1;  // 最后一个
        // 没有到边界
        while (r <= row - 1 && c >= 0) {
            if (array[r][c] == target) {
                return true;
            } else if (array[r][c] > target) {
                // 往左找
                c--;
            } else  {
                // 往下找
                r++;
            }
        }
        return false;
    }


    /**
     * 替换空格
     * 请实现一个函数，将一个字符串中的每个空格替换成“%20”。例如，
     * 当字符串为We Are Happy.则经过替换之后的字符串为We%20Are%20Happy。
     * 
     * 在字符串尾部填充任意字符，因为一个空格要替换为 %20 三个字符，所以遍历到一个空格就要在
     * 字符串尾部添加两个字符。
     * 令 p1 指向字符串原来末尾位置，p2 指向字符串现在末尾位置。p1 、 p2 从后向前遍历，当 p1 
     * 遍历到一个空格时，要令 p2 指向的位置依次填充 02%，，否则就填充上 P1 指向字符的值。
     */
    public String replaceSpace(StringBuffer str) {
        int p1 = str.length() - 1;  // p1 指向原始字符串尾部
        for (int i = 0; i <= p1; i++) {
            // 循环比那里字符串查找空格
            char c = str.charAt(i);
            if (c == ' ') {
                str.append("  ");  // 添加两个字符，
            }
        }
        int p2 = str.length() - 1;  // p2 指向新字符串尾部
        // 两指针从后往前比遍历
        while (p1 >= 0 && p2 > p1) {
            // 获取 p1 前所指字符
            char c = str.charAt(p1--);
            if (c == ' ') {
                str.setCharAt(p2--, '0');  // 先 -- 后获取
                str.setCharAt(p2--, '2');
                str.setCharAt(p2--, '%');
            } else {
                str.setCharAt(p2--, c);
            }
        }
        return str.toString();
    }


    /**
     * 从尾到头打印链表
     * 输入一个链表，按链表值从尾到头的顺序返回一个ArrayList。
     * 1. 递归
     * 2. 头插法
     * 3. 栈（LinkedList）
     */
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        LinkedList<Integer> list = new LinkedList<Integer>();
        while (listNode != null) {
            list.push(listNode.val);
            listNode = listNode.next;
        }
        ArrayList<Integer> ret = new ArrayList<Integer>();
        while (!list.isEmpty()) {
            ret.add(list.pop());
        }
        return ret;
    }
    
    public ArrayList<Integer> printListFromTailToHeadRecursion(ListNode listNode) {
        ArrayList<Integer> ret = new ArrayList<Integer>();
        if (listNode != null) {
            ret.addAll(printListFromTailToHeadRecursion(listNode.next));
            ret.add(listNode.val);
        }
        return ret;
    }
    
    public ArrayList<Integer> printListFromTailToHeadFirst(ListNode listNode) {
        // 头插法构造逆序链表
        ListNode first = new ListNode(-1);
        while (listNode != null) {
            ListNode nextNode = listNode.next;
            listNode.next = first.next;
            first.next = listNode;
            listNode = nextNode;
        }
        // 构建ArrayList
        ArrayList<Integer> list = new ArrayList<Integer>();
        first = first.next;
        while (first != null) {
            list.add(first.val);
            first = first.next;
        }
        return list;
    }


    /**
     * 重建二叉树
     * 输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。假设输入的前序遍历和
     * 中序遍历的结果中都不含重复的数字。例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序
     * 遍历序列{4,7,2,1,5,3,8,6}，则重建二叉树并返回。
     * 
     * 前序遍历第一个值为根节点，这个值将中序遍历结果分成两部分，左边为树的左子树中序遍历结果
     * 右边为树的右子树中序遍历结果，后递归向下构建节点 (代码思想没问题，但是运行结果不正确，
     * 参数用那个ArrayList 应该好解决点)
     */
    public TreeNode reConstructBinaryTree(int [] pre, int [] in) {
        int inLen = in.length;
        if (inLen == 0) {
            return null;
        }
        int rootIndex = 0;
        // 记录中序遍历中根节点位置
        for (int i = 0; i < inLen; i++) {
            if (in[i] == pre[0]) {
                rootIndex = i;
                break;
            }
        }
        int[] preLeft = new int[rootIndex];
        int[] preRight = new int[inLen - rootIndex];
        int[] inLeft = new int[rootIndex];
        int[] inRight = new int[inLen - rootIndex];
        // 中序遍历，从根节点分开，将数据保存
        for (int i = 0; i < rootIndex; i++) {
            inLeft[i] = in[i];
            preLeft[i] = in[i + 1];
        }
        for (int i = rootIndex + 1, j = 0; i < inLen; i++, j++) {
            inRight[j] = in[i];
            preRight[j] = in[i];
        }
        // 创建根节点
        TreeNode root = new TreeNode(pre[0]);
        root.left = reConstructBinaryTree(preLeft, inLeft);
        root.right = reConstructBinaryTree(preRight, inRight);
        return root;
    }

    /**
     * 用两个栈实现一个队列
     * 完成队列的Push和Pop操作。 队列中的元素为int类型。
     * 思路： 将每次入栈元素放入栈底。即以stack2 为已有元素做中转操作
     * @param args
     */
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();
    
    public void push(int node) {
        // 先检查栈一是否为空
        while (!stack1.isEmpty()) {
            stack2.push(stack1.pop());  // 元素中专
        }
        stack1.push(node);  // 新元素入栈底
        // 元素中专返回
        while (!stack2.isEmpty()) {
            stack1.push(stack2.pop());
        }
    }
    public int pop() {
        int pop = stack1.pop();
        return pop;
    }

    /**
     * 斐波纳挈数列
     * 1. 递归 : 子问题会重复求解
     * 2. 递推
     * @param n
     */
    public int Fibonacci(int n) {
        if (n == 0) {
            return 0;
        } else if(n == 1 || n == 2) {
            return 2;
        } else {
            return Fibonacci(n - 1) + Fibonacci(n - 2);
        }
    }
    
    // 记录子问题的解， 空间复杂度为 O(n)
    public int Fibonacci2(int n) {
        if (n <= 1) {
            return n;
        }
        int[] fib = new int[n + 1];
        fib[0] = 0; fib[1] = 1;
        for (int i = 2; i <= n; i++) {
            fib[i] = fib[i - 1] + fib[i - 2];
        }
        return fib[n];
    }
    
    // 空间复杂度降为 O(1)
    public int Fibonacci3(int n) {
        if (n <= 1) {
            return n;
        }
        int fib0 = 0, fib1 = 1;
        int fibn = 0;
        for (int i = 2; i <= n; i++) {
            fibn = fib0 + fib1;
            fib0 = fib1;
            fib1 = fibn;
        }
        return fibn;
    }


    /**
     * 青蛙跳台阶（斐波纳挈数列）
     * 递归：1 级台阶有一种跳法，2 级台阶有 2 种跳法，3级台阶，可以从第一级跳上来，
     * 也可以从第二级跳上来，即 f(3) = f(1) + f(2), 则推导出 f(n) = f(n - 1) + f(n - 2)
     * @param target
     */
    public int JumpFloor(int target) {
       if (target == 1) {
           return 1;
       } else if (target == 2) {
           return 2;
       } else {
           return JumpFloor(target - 1) + JumpFloor(target - 2);
       }
    }

    /**
     * 青蛙跳台阶2
     一只青蛙一次可以跳上 1 级台阶，也可以跳上 2 级... 它也可以跳上 n 级。求该青蛙跳上一个 n 级的台阶总共有多少种跳法。
     * @param n
     */
    public int JumpFloorII(int n) {
        int[] dp = new int[n];
        Arrays.fill(dp, 1);  // 状态数组
        for (int i = 1; i < n; i++) {  // 求解第 i 层跳法
            for (int j = 0; j < i; j++) {
                dp[i] += dp[j];
            }
        }
        return dp[n - 1];
    }


    /** 矩形覆盖
     * 题目描述
     * 我们可以用2*1的小矩形横着或者竖着去覆盖更大的矩形。
     * 请问用n个2*1的小矩形无重叠地覆盖一个2*n的大矩形，总共有多少种方法？
     * @param target
     */
    public int RectCover(int target) {
        if (target <= 2) {
            return target;
        }
        return RectCover(target - 1) + RectCover(target - 2);
    }
    
    public int RectCover2(int target) {
        if (target <= 2) {
            return target;
        }
        int pre2 = 1, pre1 = 1;
        int result = 0;
        for (int i = 3; i <= target; i++) {
            result = pre2 + pre1;
            pre2 = pre1;
            pre1 = result;
        }
        return result;
    }

    public static void main(String[] args) {
//        int[] a = new int[]{3,9,20,15,7};
//        int[] b = new int[]{9,3,15,20,7};
//        Solution1 solution1 = new Solution1();
//        TreeNode treeNode = solution1.reConstructBinaryTree(a, b);
//        System.out.println(treeNode.left.val);
        int a = 4;
        System.out.println(a >> 1); // 2
        System.out.println(a << 2); // 16
        System.out.println(a << 3);  // 32
    }
}
