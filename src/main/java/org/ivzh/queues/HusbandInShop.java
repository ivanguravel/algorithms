package org.ivzh.queues;

import java.io.*;
import java.util.*;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

// https://timus.online/problem.aspx?space=1&num=1795
public class HusbandInShop {


    int m;
    Map<String, Integer> goods;
    int n;
    Deque<Husband> q;


    private BufferedReader reader;
    private StringTokenizer tokenizer;
    private PrintWriter writer;

    public static void main(String[] args) {
        new HusbandInShop().run();
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
        this.m = nextInt();
        this.goods = new HashMap<>(m+1);
        Integer tmpVal;
        String name;
        while (m-- > 0) {
            tmpVal = nextInt();
            nextToken();
            name = nextToken();
            goods.put(name, tmpVal);
        }
        this.n = nextInt();
        this.q = new ArrayDeque<>();
        while (n-- > 0) {
            tmpVal = nextInt();
            nextToken();
            name = nextToken();
            q.add(new Husband(name, tmpVal));
        }

        int result = 0;
        Husband lastInQ = new Husband("", -1);
        Husband fromQ;
        while (!q.isEmpty()) {
            fromQ = q.pollFirst();

            // handle last
            if (lastInQ.value > 0) {
                lastInQ.value = goods.get(lastInQ.name);
                q.addFirst(lastInQ);
                lastInQ.value = -1;
            }

            if (!goods.containsKey(fromQ.name)) {
                goods.put(fromQ.name, 0);
            } else if (fromQ.value <= goods.get(fromQ.name)) {
                goods.put(fromQ.name, goods.get(fromQ.name) - fromQ.value);
            } else {
                lastInQ = fromQ;
            }
            ++result;
        }
        println(result);
        flush();
    }


    static class Husband {
        String name;
        int value;

        public Husband(String name, int value) {
            this.name = name;
            this.value = value;
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
