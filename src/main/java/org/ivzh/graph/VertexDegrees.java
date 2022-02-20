package org.ivzh.graph;


import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

// https://acmp.ru/asp/do/index.asp?main=task&id_course=2&id_section=21&id_topic=49&id_problem=1002
public class VertexDegrees {

    int n;
    Map<Integer, Integer> map = new LinkedHashMap<>();

    private void solve() {
        this.n = nextInt();

        for (int i = 0; i < n; i++) {
            map.put(i, 0);
        }

        Integer vertex;
        for (int i =0; i < n; i++) {
            for (int j =0; j < n ; j++) {
                vertex = nextInt();
                if (vertex == 1) {
                    map.merge(i, 1, Integer::sum);
                }
            }
        }

        List<String> strings = map.entrySet()
                .stream()
                .map(e -> e.getValue())
                .map(v -> String.format("%d ", v))
                .collect(Collectors.toList());

        for (String s : strings) {
            print(s);
        }
    }

    private BufferedReader reader;
    private StringTokenizer tokenizer;
    private PrintWriter writer;

    public static void main(String[] args) {
        new VertexDegrees().run();
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
