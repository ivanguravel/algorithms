package org.ivzh.algebra;

import java.io.*;
import java.util.StringTokenizer;

import static java.lang.Long.parseLong;


// https://cses.fi/problemset/task/1068/
public class WeirdAlgorithm {


    private BufferedReader reader;
    private StringTokenizer tokenizer;
    private PrintWriter writer;

    public static void main(String[] args) {
        new WeirdAlgorithm().run();
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
        long n = nextLong();
        StringBuilder sb = new StringBuilder();
        while (true) {
            sb.append(n).append(" ");
            if (n == 1) {
                break;
            } else if ((n & 1) == 0) {
                n = n / 2;
            } else {
                n = n * 3 +1;
            }

        }
        writer.println(sb);
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

    private long nextLong() {
        return parseLong(nextToken());
    }
}
