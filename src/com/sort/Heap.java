package com.sort;

/**
 * Created with IntelliJ IDEA.
 * User: lhl
 * Date: 2019-02-24
 * Time: 16:38
 * Description: 堆：是一颗完全二叉树，某节点的值总是大于等于子节点的值（大根堆）
 * 完全二叉树性质：位置 k 的节点的父节点位置为 k/2 , 两个自子节点位置为 2k、2k+1
 */
public class Heap<T extends Comparable<T>> {
    
    private T[] heap;
    private int N = 0;

    public Heap(int n) {
        this.heap = (T[]) new Comparable[n + 1];
    }

    public boolean isEmpty() {
        return N == 0;
    }
    
    public int size() {
        return N;
    }
    
    private boolean less(int i, int j) {
        return heap[i].compareTo(heap[j]) < 0;
    }
    
    private void swap(int i, int j) {
        T t = heap[i];
        heap[i] = heap[j];
        heap[j] = t;
    }

    /**
     * 上浮
     * 当一个节点比父节点大，需要不断向上比较和交换
     * @param k
     */
    private void swim(int k) {
        while (k > 1 && less(k / 2, k)) {
            swap(k / 2, k);
            k /= 2;
        }
    }

    /**
     * 下沉
     * 当一个节点比子节点来的小，需要不断向下进行比较和交换
     * 与两个子节点中较大的进行交换
     * @param k
     */
    private void sink(int k) {
        while (2 * k <= N) {
            int j = 2 * k;  // 默认左子节点
            if (j < N && less(j, j + 1)) { 
                j++;  // 若 右子节点只较大，则 j 记录右子节点
            }
            // 如果该节点不小于子节点了 则停止下潜
            if (!less(k, j)) break;
            // 否则交换下潜
            swap(k, j);
            k = j;
        }
    }

    /**
     * 插入元素
     * 将新元素放到数组末尾
     * 后上浮到合适位置
     * @param v
     */
    public void insert(Comparable v) {
        heap[++N] = (T) v;
        swim(N);
    }

    /**
     * 删除最大元素
     * 从数组顶端删除最大元素
     * 将数组最后一个元素放到顶端
     * 让这个元素下沉到合适位置
     * @return
     */
    public T delMax() {
        T max = heap[1];
        swap(1, N--);  // 最后一个元素与顶端元素交换
        heap[N + 1] = null;
        sink(1);  // 下沉
        return max;
    }
}
