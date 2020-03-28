package org.ivzh.dynamic.programming;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.math.BigDecimal;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;
import static java.util.Arrays.stream;
import static java.util.stream.IntStream.range;

// https://informatics.mccme.ru/mod/statements/view3.php?id=654&chapterid=915#1
public class Stairway {

    private static int solve(int n, int[] prices) {

        int[] dp = new int[n + 1];
        dp[0] = prices[0];
        // dynamic base
        dp[1] = prices[1];
        for (int i = 2; i < n; i++) {
            dp[i] = getCalculatedMinPlusCurrentIndex(dp, prices, i);
        }
        return dp[n - 1];

    }

    private static int getCalculatedMinPlusCurrentIndex(int[] dp, int[] prices, int index) {
        int min = Math.min(dp[index - 1], dp[index - 2]);
        return min + prices[index];
    }

}
