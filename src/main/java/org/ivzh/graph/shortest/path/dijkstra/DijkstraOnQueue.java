package org.ivzh.graph.shortest.path.dijkstra;

import java.io.*;
import java.util.*;

import static java.lang.Integer.parseInt;

// https://acmp.ru/asp/do/index.asp?main=task&id_course=2&id_section=32&id_topic=54&id_problem=1038
public class DijkstraOnQueue {

    int[] distances;
    private Set<Integer> visited = new HashSet<>();
    int n;
    PriorityQueue<Node> pq = new PriorityQueue<>();

    private void solve() {

        this.n = nextInt();
        int m = nextInt();
        int s = nextInt();

        this.distances = new int[n];

        for (int i = 0; i < n; i++) {
            distances[i] = 2009000999;
        }

        Map<Integer, List<Node>> graph = new HashMap<>();

        for (int i = 0; i < n; i++) {
            List<Node> item = new ArrayList<>();
            graph.put(i, item);
        }

        for (int i = 0; i < m; i++) {
            int a = nextInt();
            int b = nextInt();
            int c = nextInt();
            graph.get(a).add(new Node(b, c));
            graph.get(b).add(new Node(a, c));
        }

        dijkstra(graph, s);

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (i != s) {
                result.append(distances[i]).append(" ");
            } else {
                result.append(0).append(" ");
            }
        }

        writer.println(result);

    }

    public void dijkstra(Map<Integer, List<Node>> graph, int src) {
        pq.add(new Node(src, 2009000999));

        distances[src] = 0;

        while (!pq.isEmpty()) {
            int polledNode = pq.poll().node;

            if (!visited.contains(polledNode)) {
                visited.add(polledNode);

                Integer currentDistance;
                Integer newDistance;

                for (int i = 0; i < graph.get(polledNode).size(); i++) {
                    Node v = graph.get(polledNode).get(i);

                    if (!visited.contains(v.node)) {
                        currentDistance = v.distance;
                        newDistance = distances[polledNode] + currentDistance;

                        if (newDistance < distances[v.node])
                            distances[v.node] = newDistance;

                        pq.add(new Node(v.node, distances[v.node]));
                    }
                }
            }
        }
    }

    static class Node implements Comparable<Node> {

        public int node;
        public int distance;


        public Node() {
        }

        public Node(int node, int distance) {

            this.node = node;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.distance, o.distance);
        }
    }

    public static void main(String[] args) {
        new DijkstraOnQueue().run();
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
