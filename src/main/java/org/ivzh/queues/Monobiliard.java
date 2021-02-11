package org.ivzh.queues;

import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

// https://timus.online/problem.aspx?space=1&num=1494
public class Monobiliard {

    int n;
    Stack<Long> stack;

    private BufferedReader reader;
    private StringTokenizer tokenizer;
    private PrintWriter writer;

    public static void main(String[] args) {
        new Monobiliard().run();
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
        this.n = nextInt();
        this.stack = new Stack<>();

        boolean isCheater = false;

        long count = 0;

        for (int i = 0; i < n; i++) {
            long j = nextLong();
            while (count < j) {
                stack.push(++count);
            }

            isCheater = (j != stack.lastElement());

            if (isCheater) {
                break;
            }

            stack.pop();
        }

        if (isCheater) {
            println("Cheater");
        } else {
            println("Not a proof");
        }

        flush();
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
