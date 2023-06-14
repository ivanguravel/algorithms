package org.ivzh.dynamic.programming.two.dimensions;

import java.math.BigDecimal;


// https://informatics.mccme.ru/mod/statements/view3.php?id=654&chapterid=207#1
public class OneAndZeroWithoutOneDubclicationCount {

    public static void main(String[] args) {
        System.out.println(oneAndZeroCount(2));
        System.out.println(oneAndZeroCount(3));
        System.out.println(oneAndZeroCount(4));
        System.out.println(oneAndZeroCount(5));
    }

    private static BigDecimal oneAndZeroCount(int n) {
        if (n == 1) {
            return new BigDecimal(2);
        } else {
            BigDecimal[][] dp = new BigDecimal[n + 1][2];
            dp[1][0] = new BigDecimal(1);
            dp[1][1] = new BigDecimal(1);
            for (int i = 2; i <= n; i++) {
                dp[i][0] = dp[i - 1][0].add(dp[i - 1][1]);
                dp[i][1] = dp[i - 1][0];
            }

           return dp[n][0].add(dp[n][1]);
        }
    }
}
