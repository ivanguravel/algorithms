package org.ivzh.graph;


import java.io.*;
import java.util.*;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

// https://acmp.ru/asp/do/index.asp?main=task&id_course=2&id_section=21&id_topic=50&id_problem=645
public class Parade {

    int n;
    int s;
    ArrayList<Integer>[] graph;
    State[] used;
    int count;
    boolean hasLoop;

    private void solve() {
        this.n = nextInt();
        this.s = nextInt();
        this.graph = new ArrayList[n+1];
        used = new State[n+1];
        Arrays.fill(used, State.NOT_VISIT);
        hasLoop = false;

        for (int i=0; i<=n;i++) {
            graph[i] = new ArrayList<>();
        }

        Integer v,e;

        for (int i =0; i< s; i++) {
            v = nextInt();
            e=nextInt();
            graph[v].add(e);
        }

        for (int i = 1; i<=n;i++) {
            dfs(i);
        }

        if (hasLoop) {
            println("No");
        } else {
            println("Yes");
        }

    }

    private void dfs(Integer j) {
        used[j] = State.IN_PROGRESS;

        for (int vertex : graph[j]) {
            if (used[vertex] == State.NOT_VISIT) {
                dfs(vertex);
            } else if (used[vertex] == State.IN_PROGRESS) {
                hasLoop = true;
                return;
            }
        }


        used[j] = State.VISITED;
    }

    enum State {
        NOT_VISIT,
        IN_PROGRESS,
        VISITED
    }

    private BufferedReader reader;
    private StringTokenizer tokenizer;
    private PrintWriter writer;

    public static void main(String[] args) {
        new Parade().run();
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
