package org.ivzh.graph.shortest.path.dijkstra;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

// https://acmp.ru/asp/do/index.asp?main=task&id_course=2&id_section=32&id_topic=54&id_problem=675
public class GasStations {

    int[] distances;
    int[] cost;
    boolean[] used;
    int n;

    private void solve() {

        this.n = nextInt();
        this.cost = new int[n+1];

        for (int i = 1; i <= n; i++) {
            cost[i] = nextInt();
        }

        int[][] matrix = new int[n+1][n+1];

        this.distances = new int[n+1];
        Arrays.fill(this.distances, Integer.MAX_VALUE);
        distances[1] = 0;

        this.used = new boolean[n+1];

        int m = nextInt();

        for (int i = 1; i <= m; i++) {
            int j = nextInt();
            int k = nextInt();
            matrix[j][k] = cost[j];
            matrix[k][j] = cost[k];
        }

        dijkstra(matrix);

        int result = distances[n] == Integer.MAX_VALUE ? -1 : distances[n];
        writer.println(result);
    }

    private void dijkstra(int[][] matrix) {
        for (int i = 1; i <n; i++) {
            int v = -1;
            int min = Integer.MAX_VALUE;
            for (int j = 1; j <= n; j++) {
                if (!used[j] && distances[j] < min) {
                    v = j;
                    min = distances[j];
                }
            }

            if (v < 0) {
                break;
            }

            for (int j = 1; j <= n; j++) {
                if (!used[j] && matrix[v][j] != -1 && (distances[v] + matrix[v][j]) < distances[j]) {
                    distances[j] = distances[v] + matrix[v][j];
                }
            }
            this.used[v] = true;
        }
    }

    public static void main(String[] args) {
        new GasStations().run();
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
