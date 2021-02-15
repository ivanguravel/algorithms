package org.ivzh.graph;

import java.io.*;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

// https://timus.online/problem.aspx?space=1&num=1315
public class MDPARandMIIAR {

    int w;
    int h;
    int x;
    int y;
    int d;
    char[][] matrix;

    private BufferedReader reader;
    private StringTokenizer tokenizer;
    private PrintWriter writer;

    public static void main(String[] args) {
        new MDPARandMIIAR().run();
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

    private void solve() {
        this.w = nextInt();
        this.h = nextInt();
        this.x = nextInt();
        this.y = nextInt();
        this.d = nextInt();

        this.matrix = new char[h+2][w+2];

        for (int j =0; j < w+1; j++) {
            matrix[0][j] = 'w';
        }

        for (int i = 1; i< h+1; i++) {
            String value = nextToken();
            for (int j = 1; j < w+1; j++) {
                matrix[i][j] = value.toCharArray()[j];
            }
        }

        for (int i = 0; i<= h; i++) {
            for (int j = 0; j <= w; j++) {
                System.out.print(matrix[i][j]);
            }
            System.out.println();
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
