package org.ivzh.lists;

import java.io.*;
import java.util.*;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;
import static java.util.Arrays.stream;
import static java.util.stream.IntStream.range;

public class MinStackTwo {

    LinkedList<Integer> left, right;
    int sequenceSize;
    int windowSize;
    int min;


    private void solve() {
        this.sequenceSize = nextInt();
        this.windowSize = nextInt();
        this.min = Integer.MAX_VALUE;
        this.left = new LinkedList<>();
        this.right = new LinkedList<>();

        int i = 0;
        Integer forAdding = 0;
        for (; i <= sequenceSize; i++) {
            if (i < sequenceSize) {
                forAdding = nextInt();
            }
            if (i < windowSize) {
                add(forAdding);
            } else {
                System.out.println(deliverMin(i == sequenceSize));
                add(forAdding);
            }
        }
     }

     private void add(int value) {
        left.push(value);
     }


     private int deliverMin(boolean lastRun) {
        Integer min = Integer.MAX_VALUE;
        if (right.isEmpty() || lastRun) {
            while (!left.isEmpty()) {
                min = Math.min(min, left.pop());
                right.push(min);
            }
        }
        return right.pop();
     }


    // OTHER CODEBASE IS ABOUT HELPING. NOT 4 REVIEW
    public static void main(String[] args) {
        new MinStackTwo().run();
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
