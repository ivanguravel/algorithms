package org.ivzh.dynamic.programming;

import java.io.*;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

public class CountOfCombinations {
    private BufferedReader reader;
    private StringTokenizer tokenizer;
    private PrintWriter writer;





    public static void main(String[] args) {
        new CountOfCombinations().run();
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


    private static long[] cache = new long[31];

    private void solve() {
        int n = nextInt();
        int k = nextInt();

        long nkFactorial = factorial(n - k);
        long kFactorial = factorial(k);
        long nFactorial = factorial(n);

        long buffer = kFactorial * nkFactorial;

        writer.println(nFactorial / buffer);
    }

    public static long factorial(int x) {
        for (int i = 0; i <= x; i++) {
            cache[i + 1] = cache[i] * (i + 1);
        }
        return cache[x];
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
