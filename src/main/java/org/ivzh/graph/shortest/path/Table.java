package org.ivzh.graph.shortest.path;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

// https://acmp.ru/asp/do/index.asp?main=task&id_course=2&id_section=21&id_topic=51&id_problem=650
public class Table {

    int n,m;
    int[][] matrix;

    private static final int[][] moves = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    private void solve() {
        this.n = nextInt();
        this.m = nextInt();
        this.matrix = new int[n][m];


        for (int i=0; i<n;i++) {
            for (int j=0; j<m;j++) {
                matrix[i][j] = nextInt();
            }
        }

        for (int i=0; i<n;i++) {
            for (int j=0; j<m;j++) {
                System.out.print(bfs(i, j) + " ");
            }
            System.out.println();
        }
    }

    private int bfs(int i, int j) {
        Queue<Tuple> q = new LinkedList<>();
        q.add(new Tuple(i, j, 0));
        int result = 0;
        while (!q.isEmpty()) {
            Tuple tuple = q.poll();
            if (matrix[tuple.x][tuple.y] == 1) {
                result = tuple.distance;
                break;
            }
            for (int[] move : moves) {
                int x = tuple.x + move[0];
                int y = tuple.y + move[1];
                if (isValid(x, y)) {
                    q.add(new Tuple(x, y, tuple.distance +1));
                }
            }
        }
        return result;
    }

    private boolean isValid(int x, int y) {
        boolean isCellValid = x >= 0 && x < n;
        boolean isRowValid = y >= 0 && y < m;
        return isCellValid && isRowValid;
    }

    static class Tuple {
        int x,y,distance;

        public Tuple(int x, int y, int distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }
    }

    private BufferedReader reader;
    private StringTokenizer tokenizer;
    private PrintWriter writer;

    public static void main(String[] args) {
        new Table().run();
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
