package org.ivzh.binary.search;


import java.io.*;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;


// https://informatics.msk.ru/mod/statements/view.php?id=1966&chapterid=490#1
public class VeryEasyTask {


    int n;
    int x;
    int y;

    private void solve() {
        this.n = nextInt();
        this.x = nextInt();
        this.y = nextInt();


        int l =0;
        int r = n;

        while (r -l > 1) {
            int m = (r + l) / 2;
            if (isTimeMinimal(m)) {
                l = m;
            } else {
                r = m;
            }
        }

        println(r + Math.min(x,y));
    }

    private boolean isTimeMinimal(int proposition) {
        return (proposition / y + proposition / x) < (n-1);
    }




    private BufferedReader reader;
    private StringTokenizer tokenizer;
    private PrintWriter writer;

    public static void main(String[] args) {
        new VeryEasyTask().run();
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

