package org.ivzh.graph;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

// https://acmp.ru/asp/do/index.asp?main=task&id_course=2&id_section=21&id_topic=49&id_problem=1003
public class OriginsAndDrains {

    int n;
    Map<Integer, Integer> sources;
    Set<Integer> stocks;
    int[][] matrix;

    private void solve() {
        this.n = nextInt();
        this.matrix = new int[n][n];
        sources = new TreeMap<>();

        for (int i =1; i<= n; i++) {
            sources.put(i, 0);
        }
        stocks = new TreeSet<>();

        for (int i= 0; i < n; i++) {
            for (int j= 0; j < n; j++) {
                matrix[i][j] = nextInt();
                if (1 == matrix[i][j]) {
                    sources.put(j+1, sources.get(j+1) +1);
                }
            }
        }

        List<Integer> detectedSources = sources.entrySet()
                .stream()
                .filter(e -> e.getValue() == 0).map(e -> e.getKey())
                .collect(Collectors.toList());

        String sourcesAsString = String.join(" ", detectedSources
                .stream()
                .map(value -> Integer.toString(value)).collect(Collectors.toList()));

        println(String.format("%d ", detectedSources.size()) + sourcesAsString);

        for (int i =0; i< n;i++) {
            int outputsCount = 0;
            for (int j : matrix[i]) {
                if (j == 1) {
                    ++outputsCount;
                }
            }
            if (outputsCount == 0) {
                stocks.add(i+1);
            }
        }

        String stocksAsString = String.join(" ", stocks
                .stream().map(v -> Integer.toString(v)).collect(Collectors.toList()));

        println(String.format("%d ", stocks.size()) + stocksAsString);
    }

    private BufferedReader reader;
    private StringTokenizer tokenizer;
    private PrintWriter writer;

    public static void main(String[] args) {
        new OriginsAndDrains().run();
    }


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
