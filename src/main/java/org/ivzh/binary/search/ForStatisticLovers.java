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
    Map<Long, List<Integer>> queryContainer = new HashMap<>();
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
        List<Integer> list;
        for (QueryRequest request : queries) {
            list = queryContainer.get(request.count);
            if (list == null) {
                builder.append("0");
                continue;
            }

            Collections.sort(list);

            int i = lowerBound(list, request.from);

            boolean result = i != list.size() && request.to >= list.get(i);
            builder.append(result ? "1" : "0");
        }
        println(builder.toString());
    }

    private void readData() {
        this.n = nextInt();
        int count = 1;
        List<Integer> cities;
        while (count <= n) {
            long value = nextLong();
            cities = queryContainer.get(value);
            if (cities == null) {
                cities = new ArrayList<>();
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

    static int lowerBound(List<Integer> a, int x) { // x is the target value or key
        int l=-1,r=a.size();
        while(l+1<r) {
            int m=(l+r) / 2;


            if(m < a.size() && a.get(m)>=x) {
                r = m;
            } else {
                l=m;
            }
        }
        return r;
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
