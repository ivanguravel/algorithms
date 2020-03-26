package org.ivzh.dynamic.programming;

import java.math.BigDecimal;

// TODO: 2 tests from 7 are passed
public class Explosiveness2 {

    public static void main(String[] args) {
        System.out.println(solve(2));
    }


    private static BigDecimal solve(int n) {

        if (n == 0) {
            return new BigDecimal(0);
        }

        // dp basics preparation
        // 1
        BigDecimal[][] dp = new BigDecimal[n + 1][3];

        dp[1][0] = new BigDecimal(1);
        dp[1][1] = new BigDecimal(1);
        dp[1][2] = new BigDecimal(1);

        for (int i =2; i <= n; i++) {
            dp[i][0] = dp[i - 1][0].add(dp[i - 1][1]).add(dp[i - 1][2]);
            dp[i][1] = dp[i - 1][0].add(dp[i - 1][1]).add(dp[i - 1][2]);
            dp[i][2] = dp[i - 1][1].add(dp[i - 1][2]);
        }

        return dp[n][0].add(dp[n][1]).add(dp[n][2]);
    }
}
