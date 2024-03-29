package org.ivzh.binary.search;

import java.io.*;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;


// https://informatics.msk.ru/mod/statements/view.php?id=192#1
public class ClosestElementToOrigin {


    int n;
    int m;
    int[] arr;

    private void solve() {
        this.n = nextInt();
        this.m = nextInt();
        this.arr = new int[n];
        for (int i =0; i< arr.length; i++) {
            arr[i] = nextInt();
        }

        Integer searched, closestToNumber;
        for (int i =0; i< m; i++) {
            searched = nextInt();
            closestToNumber =closestToNumberBinarySearch(searched);
            println(closestToNumber);
        }
    }

    private int closestToNumberBinarySearch(int num) {
        int l = 0;
        int r = arr.length - 1;

        while (r - l > 1) {
            int mid = (l + r) / 2;
            if (arr[mid] < num) {
                l = mid;
            } else {
                r = mid;
            }
        }

        boolean condition = Math.abs(arr[r] - num) < Math.abs(arr[l] - num);
        return condition ? arr[l] : arr[r];
    }

    private BufferedReader reader;
    private StringTokenizer tokenizer;
    private PrintWriter writer;

    public static void main(String[] args) {
        new ClosestElementToOrigin().run();
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
