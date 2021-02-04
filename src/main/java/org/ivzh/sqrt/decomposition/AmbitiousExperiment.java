package org.ivzh.sqrt.decomposition;

import java.io.*;
import java.util.*;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

// https://timus.online/problem.aspx?space=1&num=2062
public class AmbitiousExperiment {

    int n;
    SqrtDecomposition sqrtDecomposition;
    long[] a;
    long[] b;
    List<Integer>[] nDivisors;

    private BufferedReader reader;
    private StringTokenizer tokenizer;
    private PrintWriter writer;

    public static void main(String[] args) {
        new AmbitiousExperiment().run();
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

        this.sqrtDecomposition = new SqrtDecomposition(n);
        this.a = new long[n+1];
        this.b = new long[n+1];

        int count = 1;
        while (count <= n) {
            b[count++] = nextLong();
        }

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
                println(sqrtDecomposition.query(a, request));
              //  flush();
            } else {
                int left = Integer.parseInt(line[1]);
                int right = Integer.parseInt(line[2]);
                sqrtDecomposition.updateRange(a, left, right, Integer.parseInt(line[3]));
            }
        }
    }


    class SqrtDecomposition {
        int size;
        long[] blocks;

        public SqrtDecomposition(int n) {
            this.size = (int) (Math.sqrt(n) + 1);
            this.blocks = new long[ size];

        }

        public void updateRange(long[] a, int l, int r, long d) {
            int lBound = l / size;
            int rBound = r / size;

            if (lBound == rBound) {
                for (int i = l; i <= r; i++)
                    a[i] = a[i] + d;
            } else {
                for (int i = l, end = (lBound + 1) * size; i < end; i++)
                    a[i] = a[i] + d;
                for (int i = lBound + 1; i < rBound; i++)
                    blocks[i] = blocks[i] + d;
                for (int i = rBound * size; i <= r; i++)
                    a[i] = a[i] + d;
            }
        }


        public long query(long[] a, int position) {
            long result =  b[position], dsz = nDivisors[position].size();
            for (int i = 0; i < dsz; ++i)
                result += a[nDivisors[position].get(i)] + blocks[nDivisors[position].get(i) / size];
            return result;
        }
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
