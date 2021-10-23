package org.ivzh.queues;

import java.util.PriorityQueue;

// https://leetcode.com/problems/kth-largest-element-in-a-stream/
public class KthLargestElementInStream {

    private PriorityQueue<Integer> heap;
    private int k;

    // O(n)
    public KthLargestElementInStream(int k, int[] nums) {
        this.heap = new PriorityQueue<>(Integer::compare);
        this.k = k;
        for (int i : nums) {
            heap.add(i);
        }
    }

    // O(k)
    public int add(int val) {
        heap.add(val);
        while(heap.size() > k){
            heap.poll();
        }
        return heap.peek();
    }
}
