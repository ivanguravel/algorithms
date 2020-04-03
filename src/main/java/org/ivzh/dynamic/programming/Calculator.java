package org.ivzh.dynamic.programming;

// https://informatics.mccme.ru/mod/statements/view3.php?id=654&chapterid=2963
public class Calculator {

    public static void main(String[] args) {
        System.out.println(solve2(32718));
    }

    private static int solve(int n) {
        return -1;
    }


    private static int solve2(int n) {
        int[] dp = new int[n+1];
        dp[1]= 0;
        dp[2]= 1;
        dp[3]= 2;

        for (int i = 4; i <= n; i++) {
            int buffer = dp[i - 1] + 1;
            if (i % 3==0) {
                buffer = min(buffer, dp[i / 3] + 1);
            } else if (i % 2==0) {
                buffer = min(buffer, dp[i / 2] + 1);
            }

            dp[i] = buffer;
        }

        return dp[n];
    }

    private static int min(int a, int b) {
        return Math.min(a, b);
    }
}
