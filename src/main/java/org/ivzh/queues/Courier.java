package org.ivzh.queues;

import java.io.*;
import java.util.*;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

// https://timus.online/problem.aspx?space=1&num=1403&locale=en
public class Courier {

    int n;
    PriorityQueue<Item> priorityQueue;

    private BufferedReader reader;
    private StringTokenizer tokenizer;
    private PrintWriter writer;

    public static void main(String[] args) {
        new Courier().run();
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


    private void solve() throws IOException {
        this.n = nextInt();
        int count = 0;
        this.priorityQueue = new PriorityQueue<>();

        while (count < n) {
            priorityQueue.add(new Item(++count, nextInt(), nextInt()));
        }

        List<Item> items = new LinkedList<>();
        Set<Integer> validator = new HashSet<>();
        for (Item item : priorityQueue) {
            if (!validator.contains(item.day)) {
                items.add(item);
                validator.add(item.day);
            }
        }

        Collections.sort(items);
        println(items.size());
        StringBuilder result = new StringBuilder();
        for (Item item : items) {
            result.append(String.format("%d ", item.number));
        }
        println(result.toString());
        flush();
    }

    static class Item implements Comparable<Item> {
        int number;
        int day;
        int money;

        public Item(int number, int day, int money) {
            this.number = number;
            this.day = day;
            this.money = money;
        }

        @Override
        public int compareTo(Item o) {
            int result = Integer.compare(this.day, o.day);
            return result == 0 ? Integer.compare(o.money, this.money) : result;
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
