package org.ivzh.sqrt.decomposition;

import java.io.*;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

// https://acmp.ru/asp/do/index.asp?main=task&id_course=2&id_section=20&id_topic=45&id_problem=596
public class RangeMaximumQuery {

    private BufferedReader reader;
    private StringTokenizer tokenizer;
    private PrintWriter writer;

    public static void main(String[] args) {
        new RangeMaximumQuery().run();
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
        int n = nextInt();
        Decomposition decomposition = new Decomposition(n);
        int[] arr = new int[n+2];
        for (int i = 1; i <= n; i++) {
            arr[i] = nextInt();
            decomposition.add(i, arr);
        }

        StringBuilder result = new StringBuilder();
        int m = nextInt();
        for (int i = 0; i < m; i++) {
            int l = nextInt();
            int r = nextInt();
            result.append(decomposition.max(l, r, arr)).append(" ");

        }
        writer.println(result);
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

    private int nextInt() {
        return parseInt(nextToken());
    }

    static class Decomposition {
        int[] max;
        int k;

        public Decomposition(int n) {
            this.k = (int) Math.sqrt(n) +1;
            this.max = new int[k];
        }

        public void add(int i, int[] array) {
            max[i/k] = Math.max(max[i/k], array[i]);
        }

        public int max(int l, int r, int[] array) {
            int answer = Integer.MIN_VALUE;

            for (int i =l; i <= r;) {
                if (i % k == 0 && i+k -1 <= r) {
                    answer = Math.max(answer, max[i/k]);
                    i = i + k;
                } else {
                    answer = Math.max(answer, array[i++]);
                }
            }
            return answer;
        }
    }
}
