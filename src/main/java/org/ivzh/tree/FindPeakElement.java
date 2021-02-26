package org.ivzh.tree;

import java.util.TreeMap;

// https://algocademy.com/app/#problem/find-peak-element
// https://leetcode.com/problems/find-peak-element/
public class FindPeakElement {
    public static void main(String[] args) {
        int[] n = {1, 2, 1, 3, 5, 6, 4};
        System.out.println(new FindPeakElement().findPeakElement(n));
    }

    public int findPeakElement(int[] nums) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        return map.lastEntry().getValue();
    }
}
