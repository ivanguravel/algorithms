package org.ivzh.recursion;

import java.io.*;
import java.util.StringTokenizer;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

// https://informatics.msk.ru/mod/statements/view.php?id=49435#1
public class SimpleRecursionTasks {

    private BufferedReader reader;
    private StringTokenizer tokenizer;
    private PrintWriter writer;

    public static void main(String[] args) {
        new SimpleRecursionTasks().run();
    }

    private void run() {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter writer = new PrintWriter(new OutputStreamWriter(System.out))) {
            this.reader = reader;
            this.writer = writer;
            this.tokenizer = null;
            double n = nextDouble();
            long k = nextLong();

            writer.println(negativeFastPowerOf(n, k));
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }


    private int combinationsCount(int n, int k) {
        if (n==k || k==0) {
            return 1;
        } else {
            return combinationsCount(n - 1, k - 1) + combinationsCount(n - 1, k);
        }
    }

    public static final long fib(long n) {
        if (n<=1) {
            return 1;
        } else {
            return  fib(n-1) + fib(n-2);
        }
    }

    public static final int factorial(int n) {
        if (n==1) {
            return 1;
        } else {
            return  n*factorial(n-1);
        }
    }

    public static double fastPowerOf(double n, long m) {
        if (m <= 0) {
            return 1.0;
        }

        double result = fastPowerOf(n * n, m/2);
        return m % 2 == 0 ? result : n * result;
    }

    public static double negativeFastPowerOf(double n, long m) {
        if (m == 0) {
            return 1.0;
        }

        if (m < 0) {
            return 1.0 / negativeFastPowerOf(n, -m);
        }

        return n * negativeFastPowerOf(n, m -1);
    }

    public static double powerOf(double n, long m) {
        if (m<=0) {
            return 1;
        } else {
            return n * powerOf(n, m-1);
        }
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
    private long nextLong() {
        return parseLong(nextToken());
    }
    private double nextDouble() {
        return parseDouble(nextToken());
    }
}
