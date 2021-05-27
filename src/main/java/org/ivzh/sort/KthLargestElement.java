package org.ivzh.sort;

import java.util.PriorityQueue;

// https://leetcode.com/problems/kth-largest-element-in-an-array/
public class KthLargestElement {

    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o2, o1));

        for (int i : nums) {
            pq.add(i);
        }

        int counter = 0;
        Integer val = 0;
        while(counter++ < k) {
            val = pq.poll();
        }

        return val;
    }
}
