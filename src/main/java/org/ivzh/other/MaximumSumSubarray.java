package org.ivzh.other;

// https://leetcode.com/problems/maximum-subarray
class MaximumSumSubarray {
    public int maxSumSubarray(int[] nums) {
        int max = Integer.MIN_VALUE;
        int maxResult = 0;
        for (Integer i : nums) {
            maxResult = maxResult + i;
            max = Math.max(max, maxResult);
            maxResult = Math.max(0, maxResult);
        }
        return max;
    }
}
