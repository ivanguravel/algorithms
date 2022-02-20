package org.ivzh.graph;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

// https://acmp.ru/asp/do/index.asp?main=task&id_course=2&id_section=21&id_topic=50&id_problem=646
// topological sorting
public class PartsProduction {

    int n;
    int s;
    long[] time;
    ArrayList<Integer>[] graph;
    boolean[] used;
    long result;
    List<Integer> topology;

    private void solve() throws IOException {
        this.n = nextInt();
        this.time = new long[n+1];
        this.result = 0;
        this.topology = new LinkedList<>();

        for (int i =1; i <=n;i++) {
            time[i] = nextLong();
        }



        this.graph = new ArrayList[n+1];
        used = new boolean[n+1];

        for (int i=0; i<=n;i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i=1; i<=n;i++) {
            int k = nextInt();
            for (int j =0; j<k; j++) {
                graph[i].add(nextInt());
            }
        }

        dfs(1);

        println(result + " " + topology.size());
        for (Integer i : topology) {
            print(i + " ");
        }


    }

    private void dfs(Integer j) {

        if (used[j]) {
            return;
        }

        used[j] = true;
        result = result + time[j];

        for (Integer i : graph[j]) {
            dfs(i);
        }

        topology.add(j);
    }

    private BufferedReader reader;
    private StringTokenizer tokenizer;
    private PrintWriter writer;

    public static void main(String[] args) {
        new PartsProduction().run();
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
