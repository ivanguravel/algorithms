package org.ivzh.graph;

import java.io.*;
import java.util.*;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

// https://acmp.ru/asp/do/index.asp?main=task&id_course=2&id_section=21&id_topic=49&id_problem=635
public class OrientedGraph {

    int n;
    int[][] matrix;
    int[][] copy;

    private void solve() {
        this.n = nextInt();

        if (n ==0) {
            println("NO");
            return;
        }

        this.matrix = new int[n][n];
        this.copy = new int[n][n];

        for ( int i =0; i < n; i++) {
            for (int j =0; j< n; j++) {
                matrix[i][j] = nextInt();
                copy[j][i] = matrix[i][j];
            }
        }

        for ( int i =0; i < n; i++) {
            for (int j =0; j< n; j++) {
                if (i == j && matrix[i][j] == 1) {
                    println("NO");
                    return;
                }
            }
        }


        for ( int i =0; i < n; i++) {
            for (int j =0; j< n; j++) {
                if (matrix[i][j] != copy[i][j]) {
                    println("YES");
                    return;
                }
            }
        }

        println("NO");
    }

    private BufferedReader reader;
    private StringTokenizer tokenizer;
    private PrintWriter writer;

    public static void main(String[] args) {
        new OrientedGraph().run();
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
