package org.ivzh.graph.shortest.path.dijkstra;//package graph;

import java.io.*;
import java.util.*;

import static java.lang.Integer.parseInt;

// https://acmp.ru/asp/do/index.asp?main=task&id_course=2&id_section=32&id_topic=54&id_problem=1037
public class DijkstraII {

    int[] distances;
    int[] previous;
    boolean[] used;
    int n;

    private void solve() {

        this.n = nextInt();
        int s = nextInt();
        int f = nextInt();

        int[][] matrix = new int[n+1][n+1];
        this.previous = new int[n+1];
        Arrays.fill(this.previous, -1);

        this.distances = new int[n+1];
        Arrays.fill(this.distances, Integer.MAX_VALUE);
        distances[s] = 0;

        this.used = new boolean[n+1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                matrix[i][j] = nextInt();
            }
        }

        dijkstra(matrix, s);

        List<Integer> restoredPath = new LinkedList<>();
        int now = f;
        while (now != s) {
            if (now == -1) {
                writer.println(-1);
                return;
            }
            restoredPath.add( now);
            now = previous[now];
        }

        restoredPath.add(s);

        Collections.reverse(restoredPath);
        StringBuilder result = new StringBuilder();
        for (Integer i : restoredPath) {
            result.append(i).append(" ");
        }

        writer.println(result);
    }

    private void dijkstra(int[][] matrix, int s) {
        for (int i = 1; i <n; i++) {
            int v = -1;
            int min = Integer.MAX_VALUE;
            // find lowest vertex
            for (int j = 1; j <= n; j++) {
                if (!used[j] && distances[j] < min) {
                    v = j;
                    min = distances[j];

                }
            }

            // can't find any other vertexes
            if (v < 0) {
                break;
            }

            // re-compute distances
            for (int j = 1; j <= n; j++) {
                if (!used[j] && matrix[v][j] != -1 && (distances[v] + matrix[v][j]) < distances[j]) {
                    distances[j] = distances[v] + matrix[v][j];
                    previous[j] = v;
                }
            }
            this.used[v] = true;
        }
    }

    public static void main(String[] args) {
        new DijkstraII().run();
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
