package com.lru;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: lhl
 * Date: 2019-04-16
 * Time: 8:38 PM
 * Description: 使用 HashMap 与 双向链表实现LRU
 * 当数据每一次查询就将数据放到链表的head，当有新数
 * 据添加时也放到head上。这样链表的tail就是最久没用
 * 使用的缓存数据，每次容量不足的时候就可以删除tail，
 * 并将前一个元素设置为tail
 * HashMap 保证get时间复杂度为O(1)
 * 双向链表保证节点添加删除时间复杂度为O(1)
 */
public class LRUCacheWithList {
    
    private int capacity;
    private LRUNode head;
    private LRUNode tail;
    private HashMap<Integer,LRUNode> map;

    public LRUCacheWithList(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
    }

    /**
     * 获取缓存元素
     * @return
     */
    public int get(int key) {
        if (map.containsKey(key)) {
            LRUNode node = map.get(key);
            remove(node);  // get后删除原位置元素
            setHead(node); // 将最近访问元素置于链表首部
            return node.value;
        }
        return -1;
    }

    /**
     * 数据放入缓存
     */
    public void set(int key, int value) {
        // 缓存中有此数据
        if (map.containsKey(key)) {
            LRUNode node = map.get(key);
            node.value = value;  // 更新值
            remove(node);  // 删除
            setHead(node);  // 放到链表首部
        } else {
            LRUNode node = new LRUNode(key, value);
            // 如果 > 上限
            if (map.size() > capacity) {
                map.remove(tail.key);  // 淘汰最久未使用的数据
                remove(tail);
                setHead(node);  // 插入新数据
            } else {
                setHead(node);
            }
            map.put(key, node);
        }
    }

    /**
     * 将节点放到链表头部
     * @param node
     */
    private void setHead(LRUNode node) {
        // 如果头结点不是空，将node插入到头结点前面
        if (head != null) {
            node.next = head;
            head.pre = node;
        }
        head = node;
        // 如果尾节点是空，tail指向node
        if (tail == null) {
            tail = node;
        }
    }

    /**
     * 从链表中删除 node
     * @param node
     */
    private void remove(LRUNode node) {
        // 如果node不是头结点
        if(node.pre != null) {
            node.pre.next = node.next;
        } else {
            // 否则head 后移
             head = node.next;
        }
        // node 不是尾节点
        if(node.next != null) {
            node.next.pre = node.pre;
        } else {
            // 否则tail 前移
            tail = node.pre;
        }
    }
    


    /**
     * LRUNode 节点
     */
    static class LRUNode {
        private int key;
        private int value;
        private LRUNode pre;
        private LRUNode next;
        
        public LRUNode(int key, int value) {
            this.key = key;
            this.value = value;
        }

        public void setKey(int key) {
            this.key = key;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public void setPre(LRUNode pre) {
            this.pre = pre;
        }

        public void setNext(LRUNode next) {
            this.next = next;
        }
    }
    
    
    
}
