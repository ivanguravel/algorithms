package org.ivzh.two.pointers;

import java.util.*;


// https://leetcode.com/problems/sliding-window-maximum/
public class SlidingWindowMaximum {

    public static void main(String[] args) {
        int[] n = {4, -2};
        System.out.println(Arrays.toString(new SlidingWindowMaximum().maxSlidingWindow(n, 2)));
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
            List<Integer> result = new ArrayList<>();
            int first = 0;
            int second = 0;
            int count = 0;
            int max = Math.max(n[first], n[second]);
            while (second < n.length) {
                if (count++ < k) {
                    max = Math.max(max, n[second++]);
                } else {
                    result.add(max);
                    ++first;
                    second = first;
                    count = 0;
                    max = Integer.MIN_VALUE;
                }
            }
            result.add(max);
            return result.stream().mapToInt(i-> i).toArray();
        }
    }
}
