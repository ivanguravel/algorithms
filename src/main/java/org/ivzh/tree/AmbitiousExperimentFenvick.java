package org.ivzh.tree;



import java.io.*;
import java.util.*;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

public class AmbitiousExperimentFenvick {

    int n;
    long[] a;
    long[] b;
    List<Integer>[] nDivisors;

    private BufferedReader reader;
    private StringTokenizer tokenizer;
    private PrintWriter writer;

    public static void main(String[] args) {
        new AmbitiousExperimentFenvick().run();
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

    private void solve() throws IOException {
        this.n = nextInt();

        this.a = new long[n+2];
        this.b = new long[n+1];

        for (int i = 1; i <= n; ++i)
            b[i] = nextInt();

        this.nDivisors = new List[n+1];
        for (int i = 0; i < nDivisors.length; i++) {
            nDivisors[i] = new ArrayList<>();
        }
        calculateDivisors(n);


        int linesCount = nextInt();
        String[] line;
        while (linesCount-- > 0) {
            line = reader.readLine().split(" ");
            if ("1".equalsIgnoreCase(line[0])) {
                int request = Integer.parseInt(line[1]);
                println(queryWithDividors(request));
            } else {
                int left = Integer.parseInt(line[1]);
                int right = Integer.parseInt(line[2]);
                update(left, right, Integer.parseInt(line[3]));
            }
        }
    }

    long queryWithDividors(int i) {
        long ans = b[i];
        for(Integer j : nDivisors[i]) {
            ans += query(j);
        }
        return ans;
    }

    private long query(int to) {
        long result = 0;
        while (to >= 0) {
            result += a[to];
            to = (to & (to + 1)) - 1;
        }
        return result;
    }

    public void add(int at, long value) {
        while (at < this.a.length) {
            this.a[at] += value;
            at = at | (at + 1);
        }
    }

    void update(int i, int j, long d) {
        add(i, d);
        add(j + 1, -d);
    }



    private void calculateDivisors(int n) {
        for (int c = 1; c <= n; ++c) {
            for (int j = 1; j <= Math.sqrt(c); ++j) {
                if (c % j == 0) {
                    nDivisors[c].add(j);
                    if ((c / j) != j) {
                        nDivisors[c].add((c / j));
                    }
                }
            }
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
