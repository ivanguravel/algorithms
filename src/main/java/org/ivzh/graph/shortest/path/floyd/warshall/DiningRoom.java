package org.ivzh.graph.shortest.path.floyd.warshall;//package graph;//package graph;

import java.io.*;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

// https://acmp.ru/asp/do/index.asp?main=task&id_course=2&id_section=32&id_topic=52&id_problem=1027
public class DiningRoom {

    int n,m;

    int[][] graph;

    private void solve() {

        this.n = nextInt();
        this.m = nextInt();

        if (m < 1) {
            writer.println(1);
            return;
        }

        this.graph = new int[n+1][n+1];

        for(int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i != j) {
                    graph[i][j] = Integer.MAX_VALUE;
                } else {
                    graph[i][j] = 0;
                }
            }
        }

        for (int i = 0; i < m; i++) {
            int x = nextInt();
            int y = nextInt();
            int value = nextInt();

            graph[x][y] = value;
            graph[y][x] = value;
        }

        floydWarshall();

        int min = Integer.MAX_VALUE;
        int index = 0;
        for (int i = 1; i <= n; ++i) {
            int localMax = 0;
            for (int j = 1; j <= n; ++j) {
                if (graph[i][j] > localMax) {
                    localMax = graph[i][j];
                }
            }
            if (localMax < min) {
                min = localMax;
                index = i;
            }
        }

        writer.println(index);
    }

    public void floydWarshall() {
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if ((graph[i][k] != Integer.MAX_VALUE && graph[k][j] != Integer.MAX_VALUE)) {
                        graph[i][j] = Math.min(graph[i][k] + graph[k][j], graph[i][j]);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        new DiningRoom().run();
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
