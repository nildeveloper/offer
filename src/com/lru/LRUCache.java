package com.lru;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: lhl
 * Date: 2019-03-02
 * Time: 10:07
 * Description:
 */
public class LRUCache<K,V> extends LinkedHashMap<K,V> {
    
    private static final int MAX_ENTRIES = 3;  // 设定最大缓存空间
    
    LRUCache() {
        // false 基于插入顺序  true  基于访问顺序  使用了LRU  最近最少被使用的调度算法
        super(MAX_ENTRIES, 0.75f, true);  
    }

    /**
     * 当节点数 > MAX——ENTRIES 时，将最近最久未使用的节点移除
     * @param eldest
     * @return
     */
    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > MAX_ENTRIES;
    }


    public static void main(String[] args) {
        LRUCache cache = new LRUCache();
        cache.put("key1", 1);
        cache.put("key2", 2);
        cache.put("key3", 3);
        System.out.println(cache.get("key1"));
        System.out.println(cache.get("key3"));
        cache.put("key4", 4);  // 应该将key2 剔除
        System.out.println(cache);
    }
}
