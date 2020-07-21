package org.ivzh.binary.search;


import java.io.*;
import java.util.StringTokenizer;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;
import static java.util.Arrays.stream;
import static java.util.stream.IntStream.range;


// https://informatics.mccme.ru/moodle/mod/statements/view3.php?id=1966&chapterid=1923
public class Diplomas {

    long width, heigth, diplomasCount;

    private void solve() {
        this.width = nextLong();
        this.heigth = nextLong();
        this.diplomasCount = nextLong();

        // when 1 diplomas (case 1 1 1 )
        long leftBound = Math.max(width, heigth);
        // find which part of diploma is smaller
        long normalMinimum = Math.min(width, heigth);
        // right bound from (case 1 1 1 ) to multiple diplomas case
        long rightBound = Math.max(width, normalMinimum + diplomasCount);


        while (rightBound > leftBound) {
            long middle = (rightBound + leftBound) / 2;
            // find if count of diplomas are smaller or equal then count of diplomas
            if (diplomasCount <= (middle / width) * (middle / heigth)) {
                // if it's true - we assign middle to right bound
                rightBound = middle;
            } else {
                leftBound = middle + 1;
            }
        }
        println(leftBound);
    }



    public static void main(String[] args) {
        new Diplomas().run();
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
