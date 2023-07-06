package org.ivzh.graph.shortest.path;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

// https://acmp.ru/asp/do/index.asp?main=task&id_course=2&id_section=21&id_topic=51&id_problem=664
public class OneHorse {

    int[] dx = {-1,-1,1,1,2,2};
    int[] dy = {-2,2,-2,2,-1,1};

    int[][] moves = {{-2, -1}, {-2,1}, {-1, -2}, {-1, 2}, {1, -2}, {1,2}, {2, -1}, {2, 1}};

    int n;
    int[][] distances;

    private BufferedReader reader;
    private StringTokenizer tokenizer;
    private PrintWriter writer;

    private void solve() {
        this.n = nextInt();
        this.distances = new int[n+2][n+2];

        for (int i=0; i<=n;i++) {
            Arrays.fill(distances[i], Integer.MAX_VALUE);
        }

        int x1 = nextInt();
        int y1 = nextInt();
        int x2 = nextInt();
        int y2 = nextInt();

        distances[x1][y1] = 0;

        bfs(x1, y1);

        writer.println(distances[x2][y2]);
    }

    private void bfs(int i, int j) {
        Queue<Tuple> q = new ArrayDeque<>();
        q.add(new Tuple(i, j));
        while (!q.isEmpty()) {
            Tuple tuple = q.poll();
            for (int[] move : moves) {
                int x = tuple.x + move[0];
                int y = tuple.y + move[1];
                if (isValid(x, y) && distances[x][y] > distances[tuple.x][tuple.y] + 1) {
                    distances[x][y] = distances[tuple.x][tuple.y] + 1;
                    q.add(new Tuple(x, y));
                }
            }
        }
    }

    private boolean isValid(int x, int y) {
        boolean isCellValid = x >= 0 && x <= n;
        boolean isRowValid = y >= 0 && y <= n;
        return isRowValid && isCellValid;
    }

    static class Tuple {
        int x,y;

        public Tuple(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) {
        new OneHorse().run();
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
