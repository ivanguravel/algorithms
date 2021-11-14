package org.ivzh.sort;

import java.io.*;
import java.util.StringTokenizer;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;
import static java.util.Arrays.stream;
import static java.util.stream.IntStream.range;

// https://informatics.msk.ru/mod/statements/view.php?chapterid=766#1
public class MergeSortInformatics {
    int[] arr;

    private void solve() {
        int n = nextInt();
        arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = nextInt();
        }

        arr = mergeSort(arr);

        StringBuilder response = new StringBuilder();
        for (int i : arr) {
            response.append(i + " ");
        }
        println(response.toString());

    }

    private int[] mergeSort(int[] arr) {
        if (arr.length <= 1) {
            return arr;
        } else {
            int middle = arr.length / 2;

            int[] first = new int[middle];
            int[] second = new int[arr.length - middle];

            for (int i = 0; i < middle; i++) {
                first[i] = arr[i];
            }

            int j =0;
            for (int i = middle; i < arr.length; i++) {
                second[j++] = arr[i];
            }

            int[] firstAnother = mergeSort(first);
            int[] secondAnother = mergeSort(second);
            return merge(firstAnother, secondAnother);
        }
    }

    private int[] merge(int[] firstAnother, int[] secondAnother) {
        int[] result = new int[firstAnother.length + secondAnother.length];

        int i = 0;
        int j = 0;
        int k = 0;

        while (i < firstAnother.length || j < secondAnother.length) {
            if (i >=  firstAnother.length) {
                result[k++] = secondAnother[j++];
            } else if (j >= secondAnother.length) {
                result[k++] = firstAnother[i++];
            } else if (firstAnother[i] >= secondAnother[j]) {
                result[k++] = secondAnother[j++];
            } else {
                result[k++] = firstAnother[i++];
            }
        }
        return result;
    }

    private static void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }



    public static void main(String[] args) {
        new MergeSortInformatics().run();
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

