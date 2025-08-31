
package org.ivzh.cache;

import java.util.LinkedHashMap;
import java.util.Map;


// https://leetcode.com/problems/lru-cache/
public class LRUCache {

 private int capacity;
    private Map<Integer, Node> cache;
    private Node oldest;
    private Node latest;


    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();
        this.oldest = new Node(0, 0);
        this.latest = new Node(0, 0);
        this.oldest.next = this.latest;
        this.latest.prev = this.oldest;
    }


    
    public int get(int key) {
        if (cache.containsKey(key)) {
            Node node = cache.get(key);
            remove(node);
            insert(node);
            return node.val;
        }
        return -1;
    }
    
    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            remove(cache.get(key));
        }
        Node newNode = new Node(key, value);
        cache.put(key, newNode);
        insert(newNode);

        if (cache.size() > capacity) {
            Node lru = oldest.next;
            remove(lru);
            cache.remove(lru.key);
        }
    }

    private void remove(Node node) {
        Node prev = node.prev;
        Node next = node.next;
        prev.next = next;
        next.prev = prev;
    }

    private void insert(Node node) {
        Node prev = latest.prev;
        Node next = latest;
        prev.next = next.prev = node;
        node.next = next;
        node.prev = prev;
    }

    static class Node {
        int key;
        int val;
        Node prev;
        Node next;

        public Node(int key, int val) {
            this.key = key;
            this.val = val;
            this.prev = null;
            this.next = null;
        }
   }

}
