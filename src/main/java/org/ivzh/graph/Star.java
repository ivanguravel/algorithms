package org.ivzh.graph;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

// https://acmp.ru/asp/do/index.asp?main=task&id_course=2&id_section=21&id_topic=51&id_problem=652
public class Star {

    int n,m;
    char[][] matrix;
    boolean[][] visited;

    private static final int[][] moves = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    private void solve() {
        this.n = nextInt();
        this.m = nextInt();

        this.matrix = new char[n][m];

        this.visited = new boolean[n][m];

        int count = 0;

        for (int i = 0; i< matrix.length; i++) {
            matrix[i] = nextCharArray();
        }

        for (int i = 0; i< matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == '*' && !visited[i][j]) {
                    dfs(i, j);
                    count++;

                }
            }
        }

        writer.println(count);
    }

    private void bfs(int i, int j) {
        matrix[i][j] = '.';
        visited[i][j] = true;

        Queue<Tuple> q = new ArrayDeque<>();
        q.add(new Tuple(i, j));
        while (!q.isEmpty()) {

            Tuple tuple = q.poll();
            if (isValid(tuple.x, tuple.y)) {
                visited[tuple.x][tuple.y] = true;
                matrix[tuple.x][tuple.y] = '.';
                for (int[] move : moves) {
                    q.add(new Tuple(i + move[0], j + move[1]));
                }
            }
        }
    }

    private void dfs(int i, int j) {
        if (isValid(i, j)) {
            visited[i][j] = true;
            matrix[i][j] = '.';
            for (int[] move : moves) {
                dfs(i + move[0], j + move[1]);
            }
        }
    }

    private boolean isValid(int x, int y) {
        boolean isCellValid = x >= 0 && x < n;
        boolean isRowValid = y >= 0 && y < m;

        if (!isCellValid || ! isRowValid) {
            return false;
        }

        boolean notVisited = !visited[x][y];
        boolean hasStar = matrix[x][y] == '*';
        return notVisited && hasStar;
    }

    static class Tuple {
        int x,y;

        public Tuple(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private BufferedReader reader;
    private StringTokenizer tokenizer;
    private PrintWriter writer;

    public static void main(String[] args) {
        new Star().run();
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

    private char[] nextCharArray() {
        return nextToken().toCharArray();
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
