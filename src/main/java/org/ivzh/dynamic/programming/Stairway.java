package org.ivzh.dynamic.programming;


// https://informatics.mccme.ru/mod/statements/view3.php?id=654&chapterid=915
public class Stairway {

    public static void main(String[] args) {

    }

    private static int solve(int n, int[] prices) {
        if (n == 0) {
            return 0;
        } else if(n == 1) {
            return prices[0];
        } else {
            int[] dp = new int[n];
            dp[0] = 0;
            dp[1] = prices[1];
            for (int i = 2; i <= n; i++) {
                dp[i] = getCalculatedMinPlusCurrentIndex(dp, prices, i);
            }
            return dp[n];
        }
    }

    private static int getCalculatedMinPlusCurrentIndex(int[] dp, int[] prices, int index) {
        int min = Math.min(dp[index -1], dp[index -2]);
        return min + prices[index];
    }
}
