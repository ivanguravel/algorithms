package org.ivzh.graph;


import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

// https://acmp.ru/asp/do/index.asp?main=task&id_course=2&id_section=21&id_topic=48&id_problem=629
public class TravelingInSpace {

    int n;
    Map<Integer, List<Integer>> graph;

    private void solve() {
        this.n = nextInt();

        this.graph = new TreeMap<>();

        for (int i = 1; i <= n; i++) {
            graph.put(i, new LinkedList<>());
        }

        Integer v,e;
        for (int i =1; i<=(n-1); i++) {
            v = nextInt();
            e = nextInt();

            List<Integer> verteges = graph.get(v);
            verteges.add(e);
            graph.put(v, verteges);

            verteges = graph.get(e);
            verteges.add(v);
            graph.put(e, verteges);
        }


        List<Map.Entry<Integer, List<Integer>>> collect = graph.entrySet().stream().sorted(Comparator
                .comparingInt((Map.Entry<Integer, List<Integer>> e3) -> e3.getValue().size())
                .thenComparingInt(Map.Entry::getKey)).collect(Collectors.toList());

        int result = collect.isEmpty() ? 0 : collect.get(collect.size()-1).getKey();

        println(result);
    }

    private BufferedReader reader;
    private StringTokenizer tokenizer;
    private PrintWriter writer;

    public static void main(String[] args) {
        new TravelingInSpace().run();
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
