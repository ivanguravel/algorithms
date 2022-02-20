package org.ivzh.graph;

import java.io.*;
import java.util.*;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

// https://acmp.ru/asp/do/index.asp?main=task&id_course=2&id_section=21&id_topic=50&id_problem=1004
public class ConnectedComponents {

    int n;
    int s;
    ArrayList<Integer>[] graph;
    boolean[] used;
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

        List<List<Integer>> result = new ArrayList<>();

        for (int i = 1; i <=n; i++) {
            List<Integer> component = new ArrayList<>();
            dfs(i, component, true);
            if (!component.isEmpty()) {
                result.add(component);
            }
        }

        println(count);
        for (List<Integer> l : result) {
            println(l.size());
            for (Integer k : l) {
                print(k +" ");
            }
            println();
        }


    }

    private void dfs(Integer i, List<Integer> component, boolean isFirstComponent) {
        if (used[i]) {
            return;
        }

        used[i] = true;
        component.add(i);
        if (isFirstComponent) {
            ++count;
        }
        List<Integer> s = graph[i];
        if (s != null ) {
            for (int j : s) {
                dfs(j, component, false);
            }
        }
    }

    private BufferedReader reader;
    private StringTokenizer tokenizer;
    private PrintWriter writer;

    public static void main(String[] args) {
        new ConnectedComponents().run();
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
