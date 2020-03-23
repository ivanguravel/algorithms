package org.ivzh.dynamic.programming;

import java.math.BigDecimal;

public class Explosiveness {

    public static void main(String[] args) {
        System.out.println(solve(5));
    }


    private static BigDecimal solve(int n) {
        BigDecimal[][] dp = new BigDecimal[n + 1][2];

        if (n == 0) {
            return new BigDecimal(0);
        }

        // dp basics preparation
        // 1
        dp[1][0] = new BigDecimal(1);
        dp[1][1] = new BigDecimal(1);

        for (int i =2; i <= n; i++) {
            dp[i][0] = dp[i - 1][0].add(dp[i - 1][1]);
            dp[i][1] = dp[i - 1][0];
        }

        return dp[n][0].add(dp[n][1]);
    }
}
