package org.ivzh.tree;

import java.util.TreeMap;

// https://leetcode.com/problems/132-pattern/
public class OneThreeTwoPattern {
    public boolean find132pattern(int[] nums) {
        if (nums.length < 3) {
            return false;
        } else {
            int[] minimums4J = calculateMinsForJ(nums);

            TreeMap<Integer, Void> tree = new TreeMap<>();
            for (int i = nums.length - 1; i > 0; i--) {
                Integer k = tree.floorKey(nums[i] - 1);
                if (k != null && (minimums4J[i - 1] < k)) {
                    return true;
                } else {
                    tree.put(nums[i], null);
                }

            }
            return false;
        }
    }

    private int[] calculateMinsForJ(int[] nums) {
        int[] minimums = new int[nums.length];
        Integer min = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            min=Math.min(min,nums[i]);
            minimums[i] = Math.min(min,nums[i]);
        }
        return minimums;
    }

}
