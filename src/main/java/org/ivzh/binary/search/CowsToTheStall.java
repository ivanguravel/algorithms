package org.ivzh.binary.search;


import java.io.*;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;


// https://informatics.msk.ru/mod/statements/view3.php?id=1966&chapterid=1#1
public class CowsToTheStall {


    int n;
    int k;
    int[] arr;

    private void solve() {
        this.n = nextInt();
        this.k = nextInt();
        this.arr = new int[n];
        for (int i =0; i< arr.length; i++) {
            arr[i] = nextInt();
        }

        println(cowsToTheStall());
    }

    int cowsToTheStall() {
        int left = 0;
        int right = arr[arr.length -1];
        while (right - left > 1) {
            int mid = (left + right) / 2;
            if (calculateDistanceBetweenCows(mid)) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return left;
    }

    boolean calculateDistanceBetweenCows(int probablyDistance) {
        int cows = 1;
        int last = arr[0];
        for (int c : arr) {
            if (c - last >= probablyDistance) {
                cows++;
                last = c;
            }
        }
        return cows >= k;
    }

    private int closestToNumberBinarySearch(int i) {
        int l = 0;
        int r = arr.length-1;

        while (r - l > 1) {
            int m = (l+r) / 2;

            if (arr[m] < i){
                l = m;
            } else {
                r = m;
            }
        }

        boolean condition = Math.abs(i - arr[l]) <= Math.abs(i - arr[r]);
        return condition ? arr[l] : arr[r];
    }


    private BufferedReader reader;
    private StringTokenizer tokenizer;
    private PrintWriter writer;

    public static void main(String[] args) {
        new CowsToTheStall().run();
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


    private int nextInt() {
        return parseInt(nextToken());
    }

    private long nextLong() {
        return parseLong(nextToken());
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

    private void println(Object o) {
        print(o);
        println();
    }

    private void print(Object o) {
        writer.print(o);
    }

    private void flush() {
        writer.flush();
    }

    private void println() {
        writer.println();
    }
}

