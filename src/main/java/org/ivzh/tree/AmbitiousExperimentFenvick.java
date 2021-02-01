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

        this.a = new long[n+2];
        this.b = new long[n+1];

        int count = 1;
        while (count <= n) {
            b[count++] = nextLong();
        }

        Arrays.fill(this.a, 0);

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
                flush();
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

    long query(int i) {
        long sum = 0;
        while (i > 0) {
            sum += a[i];
            i -= lowbit(i);
        }
        return sum;
    }

    void add(int i, long k) {
        while (i <= n) {
            a[i] += k;
            i += lowbit(i);
        }
    }

    void update(int j, int k, long d) {
        add(j, d);
        add(k + 1, -d);
    }

    int lowbit(int i) {
        return i & -i;
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
