package org.ivzh.graph;


import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

// https://acmp.ru/asp/do/index.asp?main=task&id_course=2&id_section=21&id_topic=48&id_problem=626
public class Semaphores {

    int n;
    int m;
    Map<Integer, TreeSet<Integer>> graph;

    private void solve() {
        this.n = nextInt();
        this.m = nextInt();
        this.graph = new TreeMap<>();

        for (int i =1; i<=n;i++) {
            graph.put(i, new TreeSet<>());
        }

        Integer v,e;
        for (int i = 0; i <m; i++) {
            v = nextInt();
            e = nextInt();


            TreeSet<Integer> s = graph.get(v);
            s.add(e);
            graph.put(v, s);

            s = graph.get(e);
            s.add(v);
            graph.put(e, s);
        }

        String result = String.join(" ", graph.entrySet()
                .stream().map(entry -> Integer.toString(entry.getValue().size())).collect(Collectors.toList()));

        println(result);
    }

    private BufferedReader reader;
    private StringTokenizer tokenizer;
    private PrintWriter writer;

    public static void main(String[] args) {
        new Semaphores().run();
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
