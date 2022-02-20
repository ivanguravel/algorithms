package org.ivzh.graph;

import java.io.*;
import java.util.*;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

// https://acmp.ru/asp/do/index.asp?main=task&id_course=2&id_section=21&id_topic=50&id_problem=640
public class Banquet {

    int n;
    int m;
    ArrayList<Integer>[] graph;
    int[] color;
    boolean answer;
    int count = 0;

    private void solve() {
        this.n = nextInt();
        this.m = nextInt();
        this.graph = new ArrayList[n+1];
        color = new int[n+1];
        this.answer = true;
        this.count = 0;

        for (int i=0; i<=n;i++) {
            graph[i] = new ArrayList<>();
        }

        Integer v,e;

        for (int i = 0; i< m; i++) {
            v = nextInt()-1;
            e=nextInt()-1;
            graph[v].add(e);
        }

        for (int i = 0; i<n;i++) {
            if (color[i] == 0) {
                dfs(i, 1);
            }
        }

        if (answer) {
            println("YES");
        }

    }

    private void dfs(Integer j, Integer c) {

        if (!answer) {
            return;
        }

        this.color[j] = c;
        for (Integer i : graph[j]) {
            if (color[i] == 0) {
                dfs(i, c == 1 ? 2 : 1);
            } else if (color[i] == c) {
                answer = false;
                if (count < 1) {
                    print("NO");
                    ++count;
                }
                return;
            }
        }
    }

    private BufferedReader reader;
    private StringTokenizer tokenizer;
    private PrintWriter writer;

    public static void main(String[] args) {
        new Banquet().run();
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
