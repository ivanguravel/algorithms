package org.ivzh.dynamic.programming;

// https://leetcode.com/problems/climbing-stairs
class ClimbingStairs {
    public int climbStairs(int n) {
        
        if (n == 0) {
            return 0;
        } else if (n==1) {
            return 1;
            
        } else if (n==2) {
            return 2;
        }
        
        
        int[] dp = new int[n+2];
        
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 3;
        dp[4] = 5;
        
        for (int i = 5; i <= n; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }
        
        return dp[n];
    }
}
