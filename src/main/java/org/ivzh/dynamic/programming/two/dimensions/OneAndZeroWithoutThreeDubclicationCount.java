package org.ivzh.dynamic.programming.two.dimensions;

import java.math.BigDecimal;

public class OneAndZeroWithoutThreeDubclicationCount {

    public static void main(String[] args) {
        System.out.println(solve(4));
    }


    private static BigDecimal solve(int n) {
        BigDecimal[][] dp = new BigDecimal[n + 1][3];

        // 1
        dp[1][0] = new BigDecimal(1);
        dp[1][1] = new BigDecimal(1);
        dp[1][2] = new BigDecimal(0);

        // 2
//        dp[2][0] = new BigDecimal(2);
//        dp[2][1] = new BigDecimal(1);
//        dp[2][2] = new BigDecimal(1);

        // 3
//        dp[3][0] = new BigDecimal(4);
//        dp[3][1] = new BigDecimal(2);
//        dp[3][2] = new BigDecimal(1);

        if (n > 1) {
            for (int i = 2; i <= n; i++) {
                dp[i][0] = dp[i -1][0].add(dp[i - 1][1]).add(dp[i -1][2]);
                dp[i][1] = dp[i -1][0];
                dp[i][2] = dp[i -1][1];
            }
        }

        return dp[n][0].add(dp[n][1]).add(dp[n][2]);
    }
}
