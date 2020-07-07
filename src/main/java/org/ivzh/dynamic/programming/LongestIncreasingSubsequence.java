package org.ivzh.dynamic.programming;


import java.io.*;
import java.util.*;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

import static java.util.Arrays.stream;
import static java.util.stream.IntStream.range;

public class LongestIncreasingSubsequence {

        int n;
        int result;
        List<Integer> sequence;
        int[] cache;

        private void solve() {
            readData();

            if (n ==0) {
                print(0);
                return;
            }

            cache[0] = 1;
            for (int i = 1; i < n; i++) {
                int localMax = 0;

                for (int j = 0; j < i; j++) {
                    if (sequence.get(i) > sequence.get(j)) {
                        localMax = Math.max(localMax, cache[j]);
                    }
                }
                cache[i] = localMax + 1;
                result = Math.max(result, cache[i]);
            }

            print(result);
        }

        private void readData() {
            n = nextInt();
            sequence = new ArrayList<>(n + 2);
            cache = new int[n+1];
            Arrays.fill(cache, 0);
            int counter = 0;
            while (counter < n) {
                sequence.add(counter, nextInt());
                ++counter;
            }
            result = 1;
        }

        public static void main(String[] args) {
            new LongestIncreasingSubsequence().run();
        }

        private BufferedReader reader;
        private StringTokenizer tokenizer;
        private PrintWriter writer;

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

        private int nextInt(int radix) {
            return parseInt(nextToken(), radix);
        }

        private int nextInt() {
            return parseInt(nextToken());
        }

        private long nextLong(int radix) {
            return parseLong(nextToken(), radix);
        }

        private long nextLong() {
            return parseLong(nextToken());
        }

        private double nextDouble() {
            return parseDouble(nextToken());
        }

        private int[] nextArr(int size) {
            return stream(new int[size]).map(c -> nextInt()).toArray();
        }

        private long[] nextArrL(int size) {
            return stream(new long[size]).map(c -> nextLong()).toArray();
        }

        private double[] nextArrD(int size) {
            return stream(new double[size]).map(c -> nextDouble()).toArray();
        }

        private char[][] nextCharMatrix(int n) {
            return range(0, n).mapToObj(i -> nextToken().toCharArray()).toArray(char[][]::new);
        }

        private int[][] nextIntMatrix(final int n, final int m) {
            return range(0, n).mapToObj(i -> nextArr(m)).toArray(int[][]::new);
        }

        private double[][] nextDoubleMatrix(final int n, final int m) {
            return range(0, n).mapToObj(i -> nextArrD(m)).toArray(double[][]::new);
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

        private void printf(String format, Object... args) {
            writer.printf(format, args);
        }

        private void print(Object o) {
            writer.print(o);
        }

        private void println() {
            writer.println();
        }

        private void println(Object o) {
            print(o);
            println();
        }

        private void flush() {
            writer.flush();
        }

}
