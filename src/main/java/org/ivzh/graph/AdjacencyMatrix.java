package org.ivzh.graph;

import java.io.*;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

public class AdjacencyMatrix {

    int n;
    int[][] matrix;

    private void solve() {
        this.n = nextInt();
        this.matrix = new int[n][n];

        Integer vertex;
        int vertexCount = 0;
        for (int i =0; i < n; i++) {
            for (int j =0; j < n ; j++) {
                vertex = nextInt();
                if (vertex == 1) {
                    ++vertexCount;
                }
                matrix[i][j] = vertex;
            }
        }

        println(String.format("%d %d", n, vertexCount));
        for (int i =0; i < n; i++) {
            for (int j =0; j < n ; j++) {
                if (matrix[i][j] == 1) {
                    println(String.format("%d %d", i+1, j+1));
                }
            }
        }
    }

    private BufferedReader reader;
    private StringTokenizer tokenizer;
    private PrintWriter writer;

    public static void main(String[] args) {
        new AdjacencyMatrix().run();
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
