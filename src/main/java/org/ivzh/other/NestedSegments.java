package org.ivzh.other;

import java.io.*;
import java.util.Comparator;
import java.util.StringTokenizer;
import java.util.TreeSet;

import static java.lang.Integer.parseInt;


// https://timus.online/problem.aspx?space=1&num=1987&locale=en
public class NestedSegments {

    int  n;
    TreeSet<IntervalDescriptor> intervalDescriptors;
    int m;


    private BufferedReader reader;
    private StringTokenizer tokenizer;
    private PrintWriter writer;

    public static void main(String[] args) {
        new NestedSegments().run();
    }


    private void run() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in), 32768);
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
        TreeSet<IntervalDescriptor> bufferForEndIntervalsComparison = new TreeSet<>((o1, o2) -> o1.end == o2.end ? o1.id - o2.id : o1.end - o2.end);
        Integer query;
        while (m-- > 0) {
            query = nextInt();
            while (!intervalDescriptors.iterator().hasNext() && intervalDescriptors.first().start <= query) {
                bufferForEndIntervalsComparison.add(intervalDescriptors.pollFirst());
            }
            while (!bufferForEndIntervalsComparison.isEmpty() && bufferForEndIntervalsComparison.first().end < query) {
                bufferForEndIntervalsComparison.pollFirst();
            }
            showResult(bufferForEndIntervalsComparison);
        }

    }


    private void readData() {
        this.n = nextInt();
        this.intervalDescriptors = new TreeSet<>((o1, o2) -> o1.start == o2.start ? o1.id - o2.id : o1.start - o2.start);
        for (int i = 0; i < n; ++i) {
            intervalDescriptors.add(new IntervalDescriptor(nextInt(), nextInt(), i));
        }
        this.m = nextInt();
    }

    private void showResult(TreeSet<IntervalDescriptor> intervalDescriptors) {
        if (!intervalDescriptors.isEmpty()) {
            println((intervalDescriptors.first().id + 1));
        } else {
            println(-1);
        }
    }


    static class IntervalDescriptor {
        int start;
        int end;
        int id;

        public IntervalDescriptor(int start, int end, int id) {
            this.start = start;
            this.end = end;
            this.id = id;
        }
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


    private void println() {
        writer.println();
    }
}
