package org.ivzh.sqrt.decomposition;

import java.io.*;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

// https://acmp.ru/asp/do/index.asp?main=task&id_course=2&id_section=20&id_topic=45&id_problem=597
public class CountZeroesInRange {

    private BufferedReader reader;
    private StringTokenizer tokenizer;
    private PrintWriter writer;

    public static void main(String[] args) {
        new CountZeroesInRange().run();
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
        int[] arr = new int[n+1];
        for (int i = 1; i <= n; i++) {
            arr[i] = nextInt();
            decomposition.add(i, arr);
        }

        StringBuilder result = new StringBuilder();
        int m = nextInt();
        for (int i = 0; i < m; i++) {
            int l = nextInt();
            int r = nextInt();
            result.append(decomposition.count(l, r, arr)).append(" ");

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
        int[] count;
        int k;

        public Decomposition(int n) {
            this.k = (int) Math.sqrt(n) +1;
            this.count = new int[k];
        }

        public void add(int i, int[] array) {
            int zeroCount = array[i] == 0 ? 1 : 0;
            count[i/k] = count[i/k] + zeroCount;
        }

        public int count(int l, int r, int[] array) {
            int answer = 0;

            for (int i =l; i <= r;) {
                if (i % k == 0 && i+k -1 <= r) {
                    answer = answer + count[i/k];
                    i = i + k;
                } else {
                    int zeroCount = array[i++] == 0 ? 1 : 0;
                    answer = answer + zeroCount;
                }
            }
            return answer;
        }


    }
}
