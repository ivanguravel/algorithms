package org.ivzh.dynamic.programming;

import java.io.*;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

// https://algoprog.ru/material/dp_simple
public class EasyDymamicProgrammingTasks {

    private BufferedReader reader;
    private StringTokenizer tokenizer;
    private PrintWriter writer;

    public static void main(String[] args) {
        new EasyDymamicProgrammingTasks().run();
    }

    private void run() {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter writer = new PrintWriter(new OutputStreamWriter(System.out))) {
            this.reader = reader;
            this.writer = writer;
            this.tokenizer = null;
            solve();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    private void solve() {
        int n = nextInt();
       // writer.println(fibonachi(n));
        writer.println(countOfWays(n));
    }

    public int fibonachi(int n) {
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;

        for (int i =2; i < n+1; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }

        return dp[n];
    }

    public int simpleSequence(int n) {
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;

        for (int i =2; i < n+1; i++) {
            int temp = i %2 ==0 ? dp[i/2 -1] : (-1) * dp[i/2 -1];
            dp[i] = dp[i/2] + temp;
        }

        return dp[n];
    }

    public int danger(int n) {

        if (n ==0) {
            return 0;
        }

        int[][] dp = new int[n+1][2];

        dp[1][0] = 1;
        dp[1][1] = 1;

        for (int i = 2; i <= n; i++) {
            dp[i][0] = dp[i - 1][0] + dp[i - 1][1];
            dp[i][1] = dp[i - 1][0];
        }

        return dp[n][0] + dp[n][1];
    }

    private BigDecimal without3Ones(int n) {
        BigDecimal[][] dp = new BigDecimal[n + 1][3];

        // 1
        dp[1][0] = new BigDecimal(1);
        dp[1][1] = new BigDecimal(1);
        dp[1][2] = new BigDecimal(0);


        for (int i = 2; i <= n; i++) {
            dp[i][0] = dp[i -1][0].add(dp[i - 1][1]).add(dp[i -1][2]);
            dp[i][1] = dp[i -1][0];
            dp[i][2] = dp[i -1][1];
        }


        return dp[n][0].add(dp[n][1]).add(dp[n][2]);
    }

    public int mostCheapestWay(int n) {
        int[] price = new int[n];
        for (int i = 0; i <n; i++) {
            price[i] = nextInt();
        }

        int[] dp = new int[n];
        dp[0] = price[0];
        dp[1] = price[1];
        for (int i =2; i< n; i++) {
            dp[i] = price[i] + Math.min(price[i-1], price[i-2]);
        }
        return dp[n-1];
    }

    public int countOfWays(int n) {
        int[] dp = new int[n+1];
        dp[n] = 1;
        for (int i =n; i >= 0; i--) {
            if (i - 1 >= 0) {
                dp[i-1] += dp[i];
            }
            if (i - 2 >= 0) {
                dp[i-2] += dp[i];
            }
            if (i - 3 >= 0) {
                dp[i-3] += dp[i];
            }
        }
        return dp[0];
    }

    public int tableWalking(int n, int m) {
        int[][] dp = new int[n+1][m+1];
        Arrays.fill(dp[0], 1);
        for (int i =0; i <m; i++) {
            dp[0][i] = 1;
        }

        for (int i =1; i< n; i++) {
            for (int j = 1; j < m; j++) {
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }
        return dp[n-1][m-1];
    }

    public int lastDigitOfTheFibonachiSeq(int n) {
        return fibonachi(n) % 10;
    }

    private String nextToken() {
        while (tokenizer == null || !tokenizer.hasMoreTokens()) {
            try {
                tokenizer = new StringTokenizer(reader.readLine());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return tokenizer.nextToken();
    }

    private int nextInt() {
        return parseInt(nextToken());
    }
}
