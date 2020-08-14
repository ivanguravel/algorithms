
package org.ivzh.cache;

import java.util.LinkedHashMap;
import java.util.Map;


// https://leetcode.com/problems/lru-cache/
public class LRUCache {

    int capacity;
    LinkedHashMap<Integer, Integer> hash;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.hash = new LinkedHashMap<Integer, Integer>(capacity, 0.75f, true) {
            protected boolean removeEldestEntry(Map.Entry<Integer,Integer> eldest) {
                return size() > capacity;
            }   
        };
    }
    
    public int get(int key) {
        Integer val = hash.get(key);
        return val == null ? -1 : val;
    }
    
    public void put(int key, int value) {
        hash.put(key, value);
    }



}
