package org.ivzh.dynamic.programming;

// https://leetcode.com/problems/maximum-subarray/
public class MaxArrSum {

    public static void main(String[] args) {
        int[] nums = {-1, -2};
        System.out.println(solve(nums));
    }

    private static int solve(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        for (int i =1; i < nums.length; i++) {
            dp[i] = nums[i] + dp[i - 1];

        }

        int jmin = 0;
        Integer localSum = dp[0];
        for (int i = 0; i < dp.length; i++) {
            localSum = Math.max(localSum, dp[i] - jmin);
            jmin = Math.min(jmin, dp[i]);
        }

        return localSum;
    }
}
