package org.ivzh.graph.shortest.path.ford.bellman;//package graph;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

// https://acmp.ru/asp/do/index.asp?main=topic&id_course=2&id_section=32&id_topic=53
public class LoopWithWeightBeforeZero {

    int[] distances;
    int n;

    List<Node> graph;

    private void solve() {

        this.n = nextInt();
        this.graph = new ArrayList<>(n+1);

        this.distances = new int[n+1];
        Arrays.fill(distances, Integer.MAX_VALUE);

        Integer distance;
        for (int i = 0; i< n; i++) {
            for (int j =0; j < n; j++) {
                distance = nextInt();
                graph.add(new Node(i, j, distance));
            }
        }

        fordBellman();

    }

    public void fordBellman() {
        distances[0] = 0;
        boolean flag = true;
        for (int i = 0; i <n; i++) {
            flag = false;
            for (int j=0; j< graph.size(); j++) {
                if (distances[graph.get(j).from] < Integer.MAX_VALUE) {
                    if (distances[graph.get(j).to] > distances[graph.get(j).from] + graph.get(j).distance) {
                        distances[graph.get(j).to] = distances[graph.get(j).from] + graph.get(j).distance;
                        flag = true;
                    }
                }
            }
            if (!flag) {
                break;
            }
        }
        if (flag)  {
            writer.println("YES");
        } else {
            writer.println("NO");
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
        new LoopWithWeightBeforeZero().run();
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
