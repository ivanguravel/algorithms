package org.ivzh.dynamic.programming;

// https://acm.timus.ru/problem.aspx?space=1&num=1225
public class Flags {

    public static void main(String[] args) {
        System.out.println(solve(4));
        System.out.println(solve(5));
        System.out.println(solve(6));
        System.out.println(solve(7));
        System.out.println(solve(8));
    }

    private static int solve(int n) {
        int[][] dp = new int[n+1][4];

        // look on the combinations of colors
        // 0 = 12-rw, 1 = 21-wr, 3 - bw, 4 - br

        dp[2][0] = 1;
        dp[2][1] = 1;
        dp[2][2] = 0;
        dp[2][3] = 0;

        dp[3][0] = 1;
        dp[3][1] = 1;
        dp[3][2] = 1;
        dp[3][3] = 1;

        for (int i=4; i<=n; i++) {
            dp[i][0] = dp[i - 1][0] + dp[i - 2][0];
            dp[i][1] = dp[i - 1][1] + dp[i - 2][1];
            dp[i][2] = dp[i - 1][2] + dp[i - 2][2];
            dp[i][3] = dp[i - 1][3] + dp[i - 2][3];
        }

        return dp[n][0] + dp[n][1] + dp[n][2] + dp[n][3];
    }
}
