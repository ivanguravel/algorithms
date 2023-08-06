package org.ivzh.graph.shortest.path.floyd.warshall;//package graph;

import java.io.*;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

// https://acmp.ru/asp/do/index.asp?main=task&id_course=2&id_section=32&id_topic=52&id_problem=1027
public class TwoProfesors {

    int n,m;

    int[][] graph;

    private void solve() {

        this.n = nextInt();
        this.m = nextInt();

        if (m < 1) {
            writer.println(0);
            return;
        }

        this.graph = new int[n][n];

        for(int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    graph[i][j] = Integer.MAX_VALUE;
                } else {
                    graph[i][j] = 0;
                }
            }
        }

        for (int i = 0; i < m; i++) {
            int x = nextInt()-1;
            int y = nextInt()-1;
            int value = nextInt();

            graph[x][y] = value;
        }

        floydWarshall();

        int max = 0;
        for(int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (graph[i][j] < Integer.MAX_VALUE) {
                    max = Math.max(max, graph[i][j]);
                }
            }
        }
        writer.println(max);
    }

    public void floydWarshall() {
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if ((i != j && graph[i][k] != Integer.MAX_VALUE && graph[k][j] != Integer.MAX_VALUE)) {
                        graph[i][j] = Math.min(graph[i][k] + graph[k][j], graph[i][j]);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        new TwoProfesors().run();
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
