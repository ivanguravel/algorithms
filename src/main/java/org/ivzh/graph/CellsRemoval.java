package org.ivzh.graph;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

// https://acmp.ru/asp/do/index.asp?main=task&id_course=2&id_section=21&id_topic=50&id_problem=1013
public class CellsRemoval {

    int n;
    int s;
    int[][] matrix;
    ArrayList<Integer>[] graph;
    boolean[][] used;
    int count;

    private void solve() {
        this.n = nextInt();
        this.s = nextInt();
        this.graph = new ArrayList[n+1];
        used = new boolean[n][s];
        this.matrix = new int[n][s];
        this.count = 0;

        for (int i =0; i< n; i++) {
            String str = nextToken();
            for (int j = 0; j < s;j++) {
                matrix[i][j] = str.charAt(j) == '#' ? 1 : 0;
            }
        }

        for (int i =0; i< n; i++) {
            for (int j = 0; j < s;j++) {
                if (matrix[i][j] == 1 && !used[i][j]) {

                    ++count;
                    dfs(i, j);
                }
            }
        }

        println(count);
    }

    private void dfs(Integer i, Integer j) {
        if (i < 0 || j < 0 || i >= n || j >= s) {
            return;
        }

        if (used[i][j] || matrix[i][j] == 0) {

            return;
        }

        used[i][j] = true;

        dfs(i, j-1);
        dfs(i-1, j);
        dfs(i, j+1);
        dfs(i+1, j);
    }

    private BufferedReader reader;
    private StringTokenizer tokenizer;
    private PrintWriter writer;

    public static void main(String[] args) {
        new CellsRemoval().run();
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
