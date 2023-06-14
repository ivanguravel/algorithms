package org.ivzh.dynamic.programming.by.profile;//package dp;

import java.io.*;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

// https://acmp.ru/?id_task=83&main=task
public class CutePatterns {

    private BufferedReader reader;
    private StringTokenizer tokenizer;
    private PrintWriter writer;

    public static void main(String[] args) {
        new CutePatterns().run();
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

        int[][] dp = new int[32][129];
        if (n > m) {
            int tmp = n;
            n = m;
            m = tmp;
        }

        int allCases = (1 << n);

        for (int mask = 0; mask < (1 << n); mask++) {
            dp[1][mask] = 1;
        }

        for (int i = 2; i <= m; i++) {
            for (int currentMask = 0; currentMask < allCases; currentMask++) {
                for (int previousMask = 0; previousMask < allCases; previousMask++) {
                    if (noProblem(currentMask, previousMask, n)) {
                        dp[i][currentMask] += dp[i - 1][previousMask];
                    }
                }
            }
        }


        int answer = 0;

        for (int mask = 0; mask < allCases; mask++) {
            answer += dp[m][mask];
        }

        writer.println(answer);
    }

    private boolean noProblem(int currentMask, int previousMask, int len) {
        for (int i = 0; i + 2 <= len; i++) {
            if (((currentMask & (3 << i)) == (previousMask & (3 << i)))
                    && ((currentMask & (1 << i)) == ((currentMask & (2 << i)) >> 1))) {
                return false;
            }
        }
        return true;
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
