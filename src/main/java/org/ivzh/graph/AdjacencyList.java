package org.ivzh.graph;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;


// https://acmp.ru/asp/do/index.asp?main=task&id_course=2&id_section=21&id_topic=49&id_problem=633
public class AdjacencyList {

    int n;
    int m;
    Map<Integer, List<Integer>> graph;
    int[][] matrix;

    private void solve2() {
        this.n = nextInt();
        graph = new TreeMap<>();
        this.matrix = new int[n][n];

        for (int i = 1; i<=n;i++) {
            graph.put(i, new LinkedList<>());

            int vertexesCount = nextInt();
            List<Integer> vertexes = graph.get(i);
            for (int j = 0; j < vertexesCount; j++) {
                vertexes.add(nextInt());
            }
        }


        List<Integer> vertexes;
        for (int i =0; i <n; i++) {
            vertexes = graph.get(i+1);
            for (Integer j : vertexes) {
                matrix[i][j-1] = 1;
            }
        }

        println(n);
        for (int i =0; i <n; i++) {
            for (int j =0; j< n;j++) {
                print(String.format("%d ", matrix[i][j]));
            }
            println();
        }
    }


    private void solve() {
        this.n = nextInt();
        this.m = nextInt();

        graph = new TreeMap<>();

        for (int i =1; i<= n; i++) {
            graph.put(i, new LinkedList<>());
        }

        Integer v,e;
        List<Integer> vertexes;
        for (int i = 0; i < m; i++) {
            v = nextInt();
            e = nextInt();

            vertexes = graph.get(v);
            vertexes.add(e);
            graph.put(v, vertexes);
        }

        StringBuilder result;
        println(n);
        for (Map.Entry<Integer, List<Integer>> entry : graph.entrySet()) {
            result = new StringBuilder();
            result.append(String.format("%d ", entry.getValue().size()));
            for (Integer i : entry.getValue()) {
                result.append(String.format("%d ", i));
            }
            println(result.toString());
        }

    }

    private BufferedReader reader;
    private StringTokenizer tokenizer;
    private PrintWriter writer;

    public static void main(String[] args) {
        new AdjacencyList().run();
    }


    private void run() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter writer = new PrintWriter(new OutputStreamWriter(System.out))) {
            this.reader = reader;
            this.writer = writer;
            this.tokenizer = null;
            solve2();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }


    private int nextInt() {
        return parseInt(nextToken());
    }

    private long nextLong() {
        return parseLong(nextToken());
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
