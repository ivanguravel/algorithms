package org.ivzh.binary.search;

import java.io.*;
import java.util.*;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;


// https://acm.timus.ru/problem.aspx?space=1&num=1613&locale=ru
public class ForStatisticLovers {

    private BufferedReader reader;
    private StringTokenizer tokenizer;
    private PrintWriter writer;

    int n;
    Map<Long, TreeSet<Integer>> queryContainer = new HashMap<>();
    int q;
    List<QueryRequest> queries = new LinkedList<>();

    public static void main(String[] args) {

        new ForStatisticLovers().run();
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

    private void solve() {
        readData();
        StringBuilder builder = new StringBuilder();
        TreeSet<Integer> cities;
        for (QueryRequest request : queries) {
            cities = queryContainer.get(request.count);
            if (cities == null) {
                builder.append("0");
                continue;
            }

            Integer floor = cities.floor(request.to);


            boolean result = floor != null && request.from <= floor;
            builder.append(result ? "1" : "0");
        }
        println(builder.toString());
        flush();
    }

    private void readData() {
        this.n = nextInt();
        int count = 1;
        TreeSet<Integer> cities;
        while (count <= n) {
            long value = nextLong();
            cities = queryContainer.get(value);
            if (cities == null) {
                cities = new TreeSet<>();
            }
            cities.add(count++);
            queryContainer.put(value, cities);
        }
        this.q = nextInt();
        count = 1;
        while (count <= q) {
            queries.add(new QueryRequest(nextInt(), nextInt(), nextLong()));
            ++count;
        }
    }

    static class QueryRequest {
        int from;
        int to;
        long count;

        public QueryRequest(int from, int to, long count) {
            this.from = from;
            this.to = to;
            this.count = count;
        }
    }

    private long nextLong() {
        return parseLong(nextToken());
    }

    private int nextInt() {
        return parseInt(nextToken());
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
