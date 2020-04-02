package org.ivzh.dynamic.programming;

import java.io.*;
import java.util.*;

// https://acm.timus.ru/problem.aspx?space=1&num=1225
public class Flags2 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        int n = in.nextInt();

        // blue = 0, white = 1, red =2
        int[] dp = new int[n+1];
        // 1
        dp[0] = 0;
        dp[1] = 2;
        dp[2] = 2;
        dp[3] = 4;


        for (int i = 4; i <= n; i++) {
            dp[i] = dp[i -1] + dp[i - 2];
        }

        out.println(dp[n]);
        out.flush();
    }
}
