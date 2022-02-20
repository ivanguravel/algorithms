package org.ivzh.graph;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

// https://acmp.ru/asp/do/index.asp?main=task&id_course=2&id_section=21&id_topic=48&id_problem=627
public class ColoredRain {

    int n;
    int[][] matrix;
    int[] colors;

    private void solve() {
        this.n = nextInt();
        this.matrix = new int[n+1][n+1];
        this.colors = new int[n+1];

        for (int i =0; i< n; i++) {
            for (int j = 0; j <n; j++) {
                matrix[i][j] = nextInt();
            }
        }

        for (int i = 0; i< n; i++) {
            colors[i] = nextInt();
        }

        int result = 0;

        for (int i =0; i< n; i++) {
            for (int j = i+1; j <n; j++) {
                if ( matrix[i][j] ==1 ) {
                    if (colors[i] != colors[j]) {
                        ++result;
                    }
                }
            }
        }

        println(result);
    }

    private BufferedReader reader;
    private StringTokenizer tokenizer;
    private PrintWriter writer;

    public static void main(String[] args) {
        new ColoredRain().run();
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
