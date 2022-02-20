package org.ivzh.graph;

import java.io.*;
import java.util.*;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;


// https://acmp.ru/asp/do/index.asp?main=task&id_course=2&id_section=21&id_topic=50&id_problem=638
public class HorseRacing {

    int n;
    int s;
    Map<Integer, Set<Integer>> graph;
    Set<Integer> used;
    int count;

    private void solve() {
        this.n = nextInt();
        this.s = nextInt();
        graph = new TreeMap<>();
        used = new HashSet<>();
        count = 0;

        Integer v,e;

        while (true) {
            v=nextInt();
            if (v == 0) {
                break;
            }
            e=nextInt();
            Set<Integer> set = graph.get(v);
            if (set == null) {
                set = new TreeSet<>();
            }
            set.add(e);
            graph.put(v, set);
        }

        dfs(s);

        if (count == n) {
            print("Yes");
        } else {
            print("No");
        }


    }

    private void dfs(Integer i) {
        if (used.contains(i)) {
            return;
        }
        used.add(i);
        ++count;
        Set<Integer> s = graph.get(i);
        if (s != null ) {
            for (int j : graph.get(i)) {
                dfs(j);
            }
        }
    }

    private BufferedReader reader;
    private StringTokenizer tokenizer;
    private PrintWriter writer;

    public static void main(String[] args) {
        new HorseRacing().run();
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
