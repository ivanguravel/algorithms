package org.ivzh.graph.shortest.path.dijkstra;//package graph;

import java.io.*;
import java.util.*;

import static java.lang.Integer.parseInt;

// https://acmp.ru/asp/do/index.asp?main=task&id_course=2&id_section=32&id_topic=54&id_problem=675
public class Buses {

    int[] time;
    boolean[] used;
    int n;

    private void solve() {

        this.n = nextInt();
        int d = nextInt();
        int v = nextInt();

        Map<Integer, List<Node>> graph = new HashMap<>();

        this.time = new int[n+1];
        Arrays.fill(this.time, Integer.MAX_VALUE);
        time[d] = 0;

        this.used = new boolean[n+1];

        int m = nextInt();

        for (int i = 1; i <= n; i++) {
            graph.put(i, new LinkedList<>());
        }

        for (int i = 1; i <= m; i++) {
            int from = nextInt();
            int startTime = nextInt();
            int to = nextInt();
            int endTime = nextInt();

            graph.get(from).add(new Node(startTime, to, endTime));
        }

        dijkstra(graph, d);

        int result = time[v] == Integer.MAX_VALUE ? -1 : (time[v]);
        writer.println(result);
    }

    private void dijkstra(Map<Integer, List<Node>> graph, int minVillage) {
        int minTime = 0;

        while (minTime < Integer.MAX_VALUE) {

            int start = minVillage;
            for (Node node : graph.get(start)) {

                if (time[start] <= node.startTime && node.endTime < time[node.to]) {
                    time[node.to] = node.endTime;
                }
            }

            minTime = Integer.MAX_VALUE;
            for (int i = 0; i <n; i++) {
                if (!used[i] && time[i] < minTime) {
                    minTime = time[i];
                    minVillage = i;
                }
            }
            used[minVillage] = true;
        }
    }

    static class Node {
        int startTime;
        int to;
        int endTime;

        public Node(int startTime, int to, int endTime) {
            this.startTime = startTime;
            this.to = to;
            this.endTime = endTime;
        }
    }

    public static void main(String[] args) {
        new Buses().run();
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
