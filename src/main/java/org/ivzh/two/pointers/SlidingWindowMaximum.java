package org.ivzh.two.pointers;

import java.util.*;


// https://leetcode.com/problems/sliding-window-maximum/
public class SlidingWindowMaximum {

    public static void main(String[] args) {
        int[] n = {1,3,-1,-3,5,3,6,7};
        System.out.println(Arrays.toString(new SlidingWindowMaximum().maxSlidingWindowPQ(n, 3)));
    }

    // O(n) - works for small amounts of data
    public int[] maxSlidingWindow(int[] n, int k) {
        if (n.length == 0) {
            return new int[0];
        } else if (n.length == 1 && k == 1) {
            int[] result = new int[1];
            result[0] = n[0];
            return result;
        } else {
            int[] res = new int[n.length - k + 1];
            int index = 0;
            int first = 0;
            int second = 0;
            int count = 0;
            int max = Math.max(n[first], n[second]);
            while (second < n.length) {
                if (count++ < k) {
                    max = Math.max(max, n[second++]);
                } else {
                    res[index++] = max;
                    ++first;
                    second = first;
                    count = 0;
                    max = Integer.MIN_VALUE;
                }
            }
            res[index++] = max;
            return res;
        }
    }

    public int[] maxSlidingWindowPQ(int[] n, int k) {
        if (n.length == 0) {
            return new int[0];
        } else if (n.length == 1 && k == 1) {
            int[] result = new int[1];
            result[0] = n[0];
            return result;
        } else {
            int[] res = new int[n.length - k + 1];
            int index = 0;
            PriorityQueue<Integer> priorityQueue = new PriorityQueue<>((o1, o2) -> Integer.compare(o2, o1));
            int first = 0;
            int second = 0;
            int count = 0;
            while (second < n.length) {
                if (count++ < k) {
                    priorityQueue.add(n[second++]);
                } else {
                    ++first;
                    second = first;
                    count = 0;
                    res[index++] = Integer.valueOf(priorityQueue.poll()).intValue();
                    priorityQueue.clear();
                }
            }
            res[index++] = Integer.valueOf(priorityQueue.poll()).intValue();
            return res;
        }
    }
}
