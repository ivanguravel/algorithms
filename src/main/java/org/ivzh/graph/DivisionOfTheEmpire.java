package org.ivzh.graph;


import java.io.*;
import java.util.*;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

// https://acmp.ru/asp/do/index.asp?main=task&id_course=2&id_section=21&id_topic=50&id_problem=1217
public class DivisionOfTheEmpire {

    int n;
    int s;
    int capitalsCount;
    ArrayList<Integer>[] graph;
    boolean[] used;
    Set<Integer> capitals;
    int count;

    private void solve() {
        this.n = nextInt();
        this.s = nextInt();
        this.graph = new ArrayList[n+1];
        used = new boolean[n+1];
        count = 0;
        Integer v,e;

        for (int i =0; i< s; i++) {
            v = nextInt();
            e=nextInt();

            ArrayList<Integer> l = graph[v];
            if (l == null) {
                l = new ArrayList<>();
            }
            l.add(e);
            graph[v] = l;

            l = graph[e];
            if (l == null) {
                l = new ArrayList<>();
            }
            l.add(v);
            graph[e] = l;
        }
        capitalsCount = nextInt();

        this.capitals = new HashSet<>(capitalsCount);

        for (int i = 0; i<capitalsCount; i++) {
            capitals.add(nextInt());
        }

        List<List<Integer>> result = new ArrayList<>();
        for (int c : capitals) {
            List<Integer> components = new ArrayList<>();
            dfs(c, components);
            if (!components.isEmpty()) {
                result.add(components);
            }
        }


        for (List<Integer> l : result) {
            StringBuilder sb = new StringBuilder();
            println(l.size());
            for (Integer i : l) {
                sb.append(i).append(" ");
            }
            println(sb.toString());
        }

    }

    private void dfs(Integer i, List<Integer> component) {
        if (used[i]) {
            return;
        }

        if (capitals.contains(i)) {
            ++count;
        }

        if (count > 1) {
            count = 0;
            return;
        }

        used[i] = true;
        component.add(i);
        List<Integer> s = graph[i];
        if (s != null ) {
            for (int j : s) {
                dfs(j, component);
            }
        }
    }

    private BufferedReader reader;
    private StringTokenizer tokenizer;
    private PrintWriter writer;

    public static void main(String[] args) {
        new DivisionOfTheEmpire().run();
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
