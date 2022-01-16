package org.ivzh.two.pointers;


import java.io.*;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

// https://informatics.msk.ru/mod/statements/view.php?id=16345&forceview=1#1
public class StyleCloses {

    int n;
    int[] shirts;
    int m;
    int[] pants;

    private void solve() {

        this.n = nextInt();
        this.shirts = new int[n];
        for (int i = 0; i < n; i++) {
            shirts[i] = nextInt();
        }

        this.m = nextInt();
        this.pants = new int[m];
        for (int i = 0; i < m; i++) {
            pants[i] = nextInt();
        }

        int l=0,r =0;
        int min = Integer.MAX_VALUE;

        int i=0,j=0;
        Integer tmp;
        while (i < shirts.length && j < pants.length) {
            tmp = Math.abs(shirts[i] - pants[j]);

            if (tmp < min) {
                min = tmp;
                l = i;
                r = j;
            }

            if (shirts[i] < pants[j]) {
                i++;
            } else {
                j++;
            }
        }
        println(shirts[l] + " " + pants[r]);
    }

    public static void main(String[] args) {
        new  StyleCloses().run();
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



    private int nextInt() {
        return parseInt(nextToken());
    }

    private void println() {
        writer.println();
    }

    private void println(Object o) {
        print(o);
        println();
    }

    private void print(Object o) {
        writer.print(o);
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
}
