package org.ivzh.sort;


import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;
import static java.util.Arrays.stream;
import static java.util.stream.IntStream.range;

// https://acmp.ru/index.asp?main=source&id=13114441
public class CountingSort {

    int n;
    List<Integer> arr;

    private void solve() {
        readData();
        List<Integer> sorted = qSort(arr);
        for (Integer i : sorted) {
            print(i + " ");
        }
    }

    private List<Integer> qSort(List<Integer> arr) {

        if (arr.size() <= 1) {
            return arr;
        }

        int pivot =  arr.get(0);

        List<Integer> lower = arr.stream().filter(a -> a < pivot).collect(Collectors.toList());
        List<Integer> eq = arr.stream().filter(a -> a == pivot).collect(Collectors.toList());
        List<Integer> bigger = arr.stream().filter(a -> a > pivot).collect(Collectors.toList());

        List<Integer> lowerAndEq = Stream.concat(qSort(lower).stream(), eq.stream()).collect(Collectors.toList());
        return Stream.concat(lowerAndEq.stream(), qSort(bigger).stream()).collect(Collectors.toList());
    }


    private void readData() {
        this.n = nextInt();
        this.arr = new ArrayList<>(n+1);
        for (int i = 0; i < n; i ++) {
            arr.add(nextInt());
        }
    }

    public static void main(String[] args) {
        new CountingSort().run();
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
