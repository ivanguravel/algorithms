package org.ivzh.graph.shortest.path.floyd.warshall;//package graph;

import java.io.*;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

// https://acmp.ru/asp/do/index.asp?main=task&id_course=2&id_section=32&id_topic=52&id_problem=668
public class FloydInsteadOfDijkstra {

    int n;

    int[][] graph;

    private void solve() {

        this.n = nextInt();
        int s = nextInt()-1;
        int t = nextInt()-1;
        this.graph = new int[n][n];

        for(int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                graph[i][j] = nextInt();
                if (graph[i][j] < 0) {
                    graph[i][j] = Integer.MAX_VALUE;
                }
            }
        }

        floydWarshall(s, t);
    }

    public void floydWarshall(int source , int target) {
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if ((i != j && graph[i][k] != Integer.MAX_VALUE && graph[k][j] != Integer.MAX_VALUE))
                       graph[i][j] = Math.min(graph[i][k] + graph[k][j], graph[i][j]);
                }
            }
        }


        if (graph[source][target] < Integer.MAX_VALUE) {
            writer.println(graph[source][target]);
        } else {
            writer.println(-1);
        }
    }

    public static void main(String[] args) {
        new FloydInsteadOfDijkstra().run();
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
