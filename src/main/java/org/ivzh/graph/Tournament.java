package org.ivzh.graph;

import java.io.*;
import java.util.*;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

// https://acmp.ru/asp/do/index.asp?main=task&id_course=2&id_section=21&id_topic=49&id_problem=1001
public class Tournament {

    int n;
    int m;
    boolean[][] connected;

    private void solve() {
        this.n = nextInt();
        this.m = nextInt();

        this.connected = new boolean[n][n];

        Integer v,e;
        for (int i =0; i< m; i++) {
            v = nextInt();
            e = nextInt();

            connected[v-1][e-1] = true;
        }

        for (int i =0; i< n; i++) {
            for (int j = i+1; j<n;j++) {
                if (connected[i][j] && connected[j][i]) {
                    println("NO");
                    return;
                } else if (!connected[i][j] && !connected[j][i]) {
                    println("NO");
                    return;
                }
            }
        }

        println("YES");
    }

    private void initGraph(Map<Integer, Integer> graph, int size) {
        graph = new TreeMap<>();

        for (int i =1; i<= size; i++) {
            graph.put(i, 0);
        }
    }

    private BufferedReader reader;
    private StringTokenizer tokenizer;
    private PrintWriter writer;

    public static void main(String[] args) {
        new Tournament().run();
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
