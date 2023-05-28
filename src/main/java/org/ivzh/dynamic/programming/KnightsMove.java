package org.ivzh.dynamic.programming;

import java.io.*;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

public class KnightsMove {

    private BufferedReader reader;
    private StringTokenizer tokenizer;
    private PrintWriter writer;

    public static void main(String[] args) {
        new KnightsMove().run();
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
        int m = nextInt();

        int dp[][] = new int[n + 2][m + 2];

        dp[1][1] = 1;
        int startFirst = 1;
        int startSecond = 1;
        while ((startFirst < n + 1) || (startSecond < m + 1)) {

            if (startSecond == m + 1) {
                startFirst++;
            } else {
                startSecond++;
            }

            int i = startFirst;
            int j = startSecond;
            while ((i <= n + 1) && j >= 2) {
                dp[i][j] = dp[i + 1][j - 2] + dp[i - 1][j - 2] + dp[i - 2][j - 1] + dp[i - 2][j + 1];
                i++;
                j--;
            }
        }
        writer.println(dp[n + 1][m + 1]);
    }

    private int recursiveDp(int[][] dp, int i , int j) {
        boolean isCellValid = isCellValid(dp.length, dp[0].length, i, j);
        if (isCellValid) {
            if (dp[i][j] == Integer.MIN_VALUE) {
                dp[i][j] = recursiveDp(dp, i - 2, j - 1)
                        + recursiveDp(dp, i - 2, j + 1)
                        + recursiveDp(dp, i - 1, j - 2)
                        + recursiveDp(dp, i + 1, j - 2);
            }

        } else {
            return 0;
        }
        return dp[i][j];
    }

    private boolean isCellValid(int n, int m, int i, int j) {
        boolean validI = i >= 0 && i < n;
        boolean validJ = j >= 0 && j < m;
        return validI && validJ;
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
