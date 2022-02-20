package org.ivzh.graph;


import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

// https://acmp.ru/asp/do/index.asp?main=task&id_course=2&id_section=21&id_topic=50&id_problem=639
public class TreeValidation {

    int n;
    int s;
    ArrayList<Integer>[] graph;
    boolean[] used;
    int count;

    private void solve() {
        this.n = nextInt();
        this.graph = new ArrayList[n+1];
        used = new boolean[n+1];
        count = 0;

        int edges = 0;

        for (int i =0; i<n; i++) {
            for (int j =0; j< n; j++) {
                if (nextInt() == 1) {
                    ArrayList<Integer> list = graph[i];
                    if (list == null) {
                        list = new ArrayList<>();
                    }
                    list.add(j);
                    graph[i] = list;
                    ++edges;
                }
            }
        }

        dfs(0);

        boolean condition = ((n-1) == (edges / 2)) && (count == n);
        String answer = condition ? "YES" : "NO";
        println(answer);
    }

    private void dfs(Integer i) {
        if (used[i]) {
            return;
        }

        used[i] = true;
        ++count;
        List<Integer> s = graph[i];
        if (s != null ) {
            for (int j : s) {
                dfs(j);
            }
        }
    }

    private BufferedReader reader;
    private StringTokenizer tokenizer;
    private PrintWriter writer;

    public static void main(String[] args) {
        new TreeValidation().run();
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
