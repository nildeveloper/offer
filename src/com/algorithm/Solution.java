package com.algorithm;

import java.util.*;

/**
 * Created with IntellJ IDEA.
 * User: lhl
 * Date: 2018-10-24
 * Time: 16:05
 * Description:
 */
public class Solution {
    // 递归
    // 1.集合全排列问题
    // R = {r1,r2,r3,...,rn} 是要进行排列的n个元素，一共有 n!种排列
    // 当n = 1 时， perm(R) = (r), r是集合R中唯一元素
    // 当m > 1时，perm(R)由(r1)perm(R1), (r2)perm(R2),...,(rn)perm(Rn)构成
    // 产生元素k--m的全排列，作为前k-1个元素的后缀
    public void Perm(int[] list, int k ,int m) {
        // 构成一次全排列，输出打印结果
        if (k == m) {
            for (int i = 0; i <= m; i++) {
                System.out.print(list[i] + "  ");
            }
            System.out.println();
        } else {
            // 在数组list中产生k -- m的全排列
            for (int i = k; i <= m; i++) {
                swap(k, i, list);
                Perm(list, k+1, m);
                swap(k, i, list);
            }
        }
    }
    // 用于交换的函数
    public static void swap(int x, int y, int[] list) {
        int temp = list[x];
        list[x] = list[y];
        list[y] = temp;
    }
    
    
    // 2.整数划分问题
    // 将一个整数n表示成一系列正整数之和
    public int split(int n, int m) {
        if (n == 1 || m == 1) {
            return 1;
        } else if (n < m) {
            return split(n, n);
        } else if (n == m) {
            return split(n, n-1) + 1;
        } else {
            return split(n, m-1) + split(n - m, m);
        }
    }
    
    
    // 分治
    // 二分搜索
    // array 是一个有序序列
    public int binarySearch(int[] arr, int x) {
        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr[mid] == x) {
                return mid;
            } else if (arr[mid] < x) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;  // 没有找到 x
    }
    
    
    // 循环赛日程表
    // 实现方阵拷贝
    // 源方阵左上角顶点坐标(fromx, fromy),行列数为r
    // 目标方阵左上角顶点坐标(tox, toy), 行列数为r
    private int[][] a = new int[100][100];
    public void copy(int tox, int toy, int fromx, int fromy, int r) {
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < r; j++) {
                a[tox + i][toy + j] = a[fromx + i][fromy + j];
            }
        }
    }
    // 构造循环日程表 选手数量为 n = pow(2, k);
    public void table(int k) {
        int i, r;
        int n = 1 << k;  // 1 * pow(2, k);
        // 构造正方形表格第一行数据
        for (i = 0; i < n; i++) {
            a[0][i] = i + 1;
        }
        // 采用分治算法构造整个循环日程表
        for (r = 1; r < n; r = r << 1) {
            for (i = 0; i < n; i++) {
                copy(r, r + i, 0, i, r);
                copy(r, i, 0, r + i, r);
            }
        }
    }
    
    // 分治找出第k小的数
    public int select(int left, int right, int k, int[] a) {
        // 找到第k小的数
        if (left >= right) {
            return a[left];
        }
        int i = left;
        int j = right + 1;
        // 最左边元素作为分界数据
        int pivot = a[left];
        // 把左侧 >= pivot 以及右侧 <= pivot 的元素交换
        while (true) {
            // 在左侧寻找 >= pivot 的元素
            do {
                i += 1;
            } while (i < a.length && a[i] < pivot);
            // 在右侧寻找 <= pivot的元素
            do {
                j -= 1;
            } while (j < a.length && a[j] > pivot);
            
            // 没有找到交换对象
            if (i >= j) {
                break;
            }
            swap(i, j, a);
        }
        if (j - left + 1 == k) {
            return pivot;
        }
        a[left] = a[j];
        a[j] = pivot;
        if (j - left + 1 < k) {
            // 对一个段进行递归调用
            return select(j + 1, right, k - j + left - 1, a);
        } else {
            return select(left, j - 1, k, a);
        }
    }
    
    // 最大字段和
    public int maxSum(int[] a) {
        int n = a.length;
        int sum = 0;
        int d = 0;
        for (int i = 1; i < n; i++) {
            if (d > 0) {
                d += a[i];
            } else {
                d = a[i];
            }
            if (d > sum) {
                sum = d;
            }
        }
        return sum;
    }

    // 动态规划
    /*
       最长公共子序列
       c[i][j] = 0    i = 0, j = 0
       c[i][j] = c[i - 1][j - 1] + 1   i > 0, j > 0, xi == yi
       c[i][j] = max{c[i][j - 1], c[i - 1][j]}  i > 0, j > 0, xi != yi
       c[i][j] 记录长度， b[i][j] 记录状态
    */
    // 计算最长公共子序列长度
    public Map lcsLength(String x, String y) {
        int m = x.length();
        int n = y.length();
        int[][] b = new int[m + 1][n + 1];
        int[][] c = new int[m + 1][n + 1];
        // 第0行 第0列置 0 
        for (int i = 1; i <= m; i++) {
            c[i][0] = 0;
        }
        for (int i = 1; i <= n; i++) {
            c[0][i] = 0;
        }
        // 根据递推公示构造数组c
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (x.charAt(i - 1) == y.charAt(j - 1)) {
                    // 两字符串对应位置元素相同， 向后判断
                    c[i][j] = c[i - 1][j - 1]  + 1;
                    b[i][j] = 1;
                } else if (c[i - 1][j] >= c[i][j - 1]) {
                    c[i][j] = c[i - 1][j];
                    b[i][j] = 2;
                } else {
                    c[i][j] = c[i][j - 1];
                    b[i][j] = 3;
                }
            }
        }
        Map map = new HashMap<String, Integer[][]>();
        map.put("c", c);
        map.put("b", b);
        return map;
    }

    // 构造最长公共子序列
    public void lcs(int i, int j, String x, int[][] b) {
        if (i == 0 || j == 0) return;
        if (b[i][j] == 1) {
            lcs(i - 1, j - 1, x, b);
            System.out.print(x.charAt(i - 1));
        } else if (b[i][j] == 2) {
            lcs(i - 1, j, x, b);
        } else {
            lcs(i, j - 1, x, b);
        }
    }

    // 最大字段和
    // F(i):以array[i] 为末尾的子数组的和的最大值，子数组元素相对位置不变
    // F(i) = max(F(i-1)+array[i], array[i])
    // res:所有子数组和的最大值
    // res=max(res,F(i))
    public int FindGreatestSumOfSubArray(int[] array) {
        int res = array[0]; // 记录当前所有子数组和的最大值
        int max = array[0]; // 包含array[i]的连续数组最大值
        for (int i = 1; i < array.length; i++) {
            max = Math.max(max + array[i], array[i]);
            res = Math.max(res, max);
        }
        return res;
    }

    public List finLength(int[] array) {
        int res = 0;
        int max = 0;
        int begin = 0;
        int begini = 0, beginj = 0;
        for (int i = 1;i < array.length; i++) {
            if (max > 0) max += array[i];
            else {
                max = array[i];
                begin = i;
            }
            if (max > res) {
                res = max;
                begini = begin;
                beginj = i;
            }
        }
        List list = new ArrayList();
        list.add(begini);
        list.add(beginj);
        list.add(res);
        return list;
    }


    // 最长单调递增子序列 
    // b[i] 表示以a[i] 为结尾的最长递增子序列的长度
    public int MaxLength(int[] array) {
        int n = array.length;
        int[] b = new int[n]; // 辅助数组b, 记录长度
        b[1] = 1;
        int max = 0; // 数组b最大值
        int i, j;
        for (i = 2; i <= n; i++) {
            int k = 0;
            for (j = 1; j < i; j++) {
                if (array[j] <= array[i] && k < b[j]) {  // k = max{b[j]}
                    k = b[j];
                }
            }
            b[i] = k + 1;  // 此位置长度为最长长度 + 1
            if (max < b[i]) {
                max = b[i];
            }
        }
        return max;
    }

    // 0--1背包问题
    // n 物品数量 c 背包容量
    // w 物品质量 v 物品价值
    // m[n][c] m[i][j]表示在面对第i件物品，且背包容量为j时所能获得的最大价值
    // (1) j < w[i] 背包容量不足以放下第 i 件物品   m[i][j] = m[i-1][j]
    // (2) j > w[i] 背包容量可以放下第 i 件物品  要考虑拿这件物品是否能获取更大的价值
    // 如果拿 m[i][j] = m[i-1][j-w[i]] + v[i]  考虑了i-1件物品，背包容量为j-w[i]时的最大价值
    // 如果不拿，m[i][j] = m[i-1][j]
    public int knapsack(int n, int c, int[] w, int[] v) {
        int[][] m = new int[15][15];
        int[] x = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= c; j++) {
                // 如果能装下，判断要不要装
                if (j >= w[i]) {
                    m[i][j] = Math.max(m[i-1][j], m[i-1][j-w[i]] + v[i]);
                } else {
                    m[i][j] = m[i-1][j];
                }
            }
        }
        // 求装入背包的物品
        int j = c;
        for (int i = n; i > 0; i--) {
            if (m[i][j] > m[i - 1][j]) {
                x[i] = 1;
                j -= w[i];
            }
        }
        System.out.println(Arrays.toString(x));
        return m[n][c];
    }

    // 数字三角形问题
    // 找一条路径，使其经过的数字总和最大
    // 自下而上
    // tri[i][j] = tri[i][j] + max(tri[i+1][j], tri[i+1][j+1])
    public int triangle(int[][] triangle) {
        for (int i = triangle.length - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                if (triangle[i + 1][j] > triangle[i + 1][j + 1]) {
                    triangle[i][j] += triangle[i + 1][j];
                } else {
                    triangle[i][j] += triangle[i + 1][j + 1];
                }
            }
        }
        return triangle[0][0];
    }
    
    // 贪心
    // 活动安排问题
    // 区间 [Si, Fi) 要与 [Sj, Fj) 不相交
    // 也就是Sj > Fi
    class Action {
        public int s; // 起始时间
        public int f; // 结束时间
        public int index; // 活动序号
    }
    
    public boolean[] GreedySelector(Action[] actions) {
        // 按活动结束时间升序
        Arrays.sort(actions, new Comparator<Action>() {
            @Override
            public int compare(Action o1, Action o2) {
                return o1.f - o2.f;
            }
        });
        boolean[] res = new boolean[actions.length];
        res[0] = true;  // 第一个活动必选
        int preEnd = 1;  // 记录最后加入到res中的活动
        for (int i = 1; i < actions.length; i++) {
            if (actions[i].s >= actions[preEnd].f) {
                res[i] = true;
                preEnd = i;
            }
        }
        return res;
    }
    
    // 背包问题
    class Bag {
        public int w; // 物品重量
        public int v; // 物品价值
        public double c = (w * 1.0) / (v * 1.0);  // 性价比
        public double x; // 装入背包的量。 0 <= x <= 1
        public int index;  // 物品编号
    } 
    // c 背包容量  bags要按性价比降序排列
    public double knapsack(Bag[] bags, double c) {
        // 按性价比降序排列
        Arrays.sort(bags, new Comparator<Bag>() {
            @Override
            public int compare(Bag o1, Bag o2) {
                return (int)(o2.c - o1.c);
            }
        });
        double cleft = c;  // 背包剩余容量
        int i = 0;
        double value = 0; // 获取的总价值
        while (i < bags.length && bags[i].w < cleft) {
            cleft -= bags[i].w;
            value += bags[i].v;
            
            bags[bags[i].index].x = 1.0;
            i++;
        }
        // 装满背包的剩余空间
        if (i < bags.length) {
            bags[bags[i].index].x = 1.0 * cleft / bags[i].w;
//            value += 1.0 * bags[i].v * cleft / bags[i].w;
            value += bags[bags[i].index].x * bags[i].v;
        }
        return value;
    }
    
    
    // 删数问题
    // 对于 n 位正整数 a, 去掉其中的任意k位数字
    // 求剩下最小的新数
    // 最近下降点优先 x[i] > x[i+1]  删除x[i]
    public String deleteNum(String a, int k) {
        if (k > a.length()) {
            return "";
        }
        // 创建StringBuilder 对象， 便于元素删除操作
        StringBuilder s = new StringBuilder(a);
        // 删除k个数
        for (int i = 0 ; i < k; i++) {
            int j = 0;
            for (j = 0; j < (s.length() - 1) && (s.charAt(j) <= s.charAt(j + 1)); j++);
            s.delete(j, j + 1);
        }
        // 删除前导0
        while (s.length() > 1 && s.charAt(0) == '0') {
            s.delete(0,1);
        } 
        return s.toString();
    }
    
    
    // 多处最优服务次序问题
    // n个顾客同时等待一项服务，顾客i需要的服务时间为ti
    // 共有s处可以提供此服务
    // 安排n个顾客服务次序使得平均等待时间最小
    // client 顾客需要的服务时间 s 服务窗口
    public double greedy(int[] client, int s) {
        // 顾客数量
        int n = client.length;
        // 服务窗口顾客等待时间
        int[] service = new int[s];
        // 服务窗口顾客等待时间总和
        int[] sum = new int[s];
        // 按顾客服务时间升序排列
        Arrays.sort(client);
        // 贪心算法实现
        int i = 0;  // 顾客指针
        int j = 0;  // 窗口指针
        while (i < n) {
            service[j] += client[i];
            sum[j] += service[j];
            i++;
            j++;
            if (j == s) {
                j = 0;
            }
        }
        // 计算所有窗口服务时间总和
        double t = 0;
        for (i = 0; i < s; i++) {
            t += sum[i];
        }
        t /= n;
        return t;
    }
    
    
//    // 子集树伪代码
//    void backtrack(int t) {
//        if (t > n) {
//            update(x);
//        } else {
//            for (int i = 0; i <= 1; i++) {
//                x[t] = i;
//                if (constraint(t) && bound(t)) {
//                    backtrack(t + 1);
//                }
//            }
//        }
//    }
//    
//    // 排序树伪代码
//    void backtrack1(int t) {
//        if (t >ｎ) {
//            udpate(x);
//        } else {
//            for (int i = t; i <= n; i++) {
//                swap(x[t], x[i]);
//                if (constraint(t) && bound(t)) {
//                    backtrack1(t + 1);
//                }
//                swap(x[t], x[i]);
//            }
//        }
//    }
//    
//    // 贪心伪代码
//    greedy(A) {
//        s = {};
//        while(not solution(s)) {
//            x = select(A);
//            if feasible(s,x)
//                    s = s + {x};
//            A = A - {x};
//        }
//        return s;
//    }
//    
    
    
    
}
