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

// https://acmp.ru/index.asp?main=task&id_task=41
public class CountingSort {

    int n;
    List<Integer> arr;

    private void solve() {
        readData();
        List<Integer> sorted = mergeSort(arr);
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


    public List<Integer> mergeSort(List<Integer> forSort) {
        List<Integer> result;
        if (forSort.size() <= 1) {
            return forSort;
        } else {


            int middle = forSort.size() / 2;

            List<Integer> leftBound = new ArrayList<>(middle);
            List<Integer> rightBound = new ArrayList<>(middle);

            for (int i = 0; i < middle; i ++) {
                leftBound.add(i, forSort.get(i));
            }

            for (int i = middle; i < forSort.size(); i ++) {
                rightBound.add(i - middle, forSort.get(i));
            }

            List<Integer> reducedLeft = mergeSort(leftBound);
            List<Integer> reducedRight = mergeSort(rightBound);
            result = merge(reducedLeft, reducedRight);
        }
        return result;
    }


    public List<Integer> merge(List<Integer> a, List<Integer> b) {
        int max = a.size() + b.size();
        List<Integer> result = new ArrayList<>(max + 1);
        int i = 0;
        int j = 0;
        while (i < a.size() || j < b.size()) {
            if (i >= a.size()) {
                result.add(b.get(j));
                ++j;
            } else if (j >= b.size()) {
                result.add(a.get(i));
                ++i;
            } else {
                if (a.get(i) >= b.get(j)) {
                    result.add(b.get(j));
                    ++j;
                } else {
                    result.add(a.get(i));
                    ++i;
                }
            }
        }

        return result;
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
