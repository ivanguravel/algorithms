package org.ivzh.graph;

import java.io.*;
import java.util.*;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

// https://acmp.ru/asp/do/index.asp?main=topic&id_course=2&id_section=21&id_topic=50
public class Friends {

    int n;
    int s;
    int[][] matrix;
    Map<Integer, Set<Integer>> graph;
    Set<Integer> used;
    int friendsCount;

    private void solve() {
        this.n = nextInt();
        this.s = nextInt();
        this.matrix = new int[n+1][n+1];
        graph = new TreeMap<>();
        used = new HashSet<>();
        this.friendsCount = 0;

        for (int i = 1; i <= n; i++) {
            for (int j =1; j <=n; j++) {
                matrix[i][j] = nextInt();
                if (matrix[i][j] == 1) {
                    if (graph.get(i) == null) {
                        graph.put(i, new TreeSet<>());
                    }

                    Set<Integer> set = graph.get(i);
                    set.add(j);
                    graph.put(i, set);

                    if (graph.get(j) == null) {
                        graph.put(j, new TreeSet<>());
                    }

                    set = graph.get(j);
                    set.add(i);
                    graph.put(j, set);
                }
            }
        }
        dfs(s);
        println(friendsCount-1);


    }

    private void dfs(Integer i) {
        if (!used.contains(i)) {
            used.add(i);
            ++friendsCount;
            Set<Integer> s = graph.get(i);
            if (s != null ) {
                for (int j : graph.get(i)) {
                    dfs(j);
                }
            }
        }
    }

    private BufferedReader reader;
    private StringTokenizer tokenizer;
    private PrintWriter writer;

    public static void main(String[] args) {
        new Friends().run();
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
