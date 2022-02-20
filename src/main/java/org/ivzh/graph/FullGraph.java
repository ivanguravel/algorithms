package org.ivzh.graph;

import java.io.*;
import java.util.*;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

// https://acmp.ru/asp/do/index.asp?main=task&id_course=2&id_section=21&id_topic=49&id_problem=1000
public class FullGraph {

    int n;
    int m;
    boolean[][] connected;

    private void solve() {
        this.n = nextInt();
        this.m = nextInt();

        this.connected = new boolean[n+1][n+1];

        Integer v,e;
        for (int i =0; i< m; i++) {
            v = nextInt();
            e = nextInt();
            connected[v][e] = true;
            connected[e][v] = true;
        }

        for (int i =1; i<= n; i++) {
            for (int j = i+1; j<=n;j++) {
                if (!connected[i][j]) {
                    println("NO");
                    return;
                }
            }
        }

        println("YES");
    }

    private BufferedReader reader;
    private StringTokenizer tokenizer;
    private PrintWriter writer;

    public static void main(String[] args) {
        new FullGraph().run();
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
