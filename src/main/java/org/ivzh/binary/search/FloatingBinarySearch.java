package org.ivzh.binary.search;

import java.io.*;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;


// https://informatics.msk.ru/mod/statements/view.php?id=3516#1
public class FloatingBinarySearch {


    int n;
    int k;

    private void solve() {
        this.n = nextInt();
        this.k = nextInt();

        println(root(n, k));
    }

    static double root(double val, int n) {
        double l, r;
        if (val >= 0 && val <= 1) {
            l = val;
            r = 1;
        } else {
            l = 1;
            r = val;
        }

        double diff = 1e-8;

        double m = 0;
        while (r -l >= diff) {
            m = (l + r) / 2;
            if (Math.pow(m, n) > val) {
                r = m;
            } else {
                l = m;
            }
        }

        return m;
    }

    private BufferedReader reader;
    private StringTokenizer tokenizer;
    private PrintWriter writer;

    public static void main(String[] args) {
        new FloatingBinarySearch().run();
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

