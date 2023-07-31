package org.ivzh.graph.shortest.path.ford.bellman;//package graph;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

import static java.lang.Integer.parseInt;

// https://acmp.ru/asp/do/index.asp?main=topic&id_course=2&id_section=32&id_topic=53
public class FordBellman {

    int[] distances;
    int n;

    List<Node> graph;

    private void solve() {

        this.n = nextInt();
        int m = nextInt();

        this.distances = new int[n];
        Arrays.fill(distances, Integer.MAX_VALUE);

        distances[0] = 0;

        this.graph = new ArrayList<>(m);

        for (int i = 0; i < m ; i++) {
            int from = nextInt()-1;
            int to = nextInt()-1;
            int distance = nextInt();
            graph.add(new Node(from, to, distance));
        }

        fordBellman(m);

        for (int i = 0; i< n; i++) {
            if (distances[i] == Integer.MAX_VALUE) {
                distances[i] = 30_000;
            }
        }


        writer.println(Arrays.stream(distances)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(" ")));

    }

    public void fordBellman(int m) {
        for (int i = 0; i < n-1; i++) {
            for (int j = 0; j < m; j++) {
                Node node = graph.get(j);
                if (distances[node.from] != Integer.MAX_VALUE) {
                    distances[node.to] = Math.min(distances[node.to], distances[node.from] + node.distance);
                }
            }
        }
    }

    static class Node {
        int from;
        int to;
        int distance;

        public Node(int from, int to, int distance) {
            this.from = from;
            this.to = to;
            this.distance = distance;
        }
    }


    public static void main(String[] args) {
        new FordBellman().run();
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
