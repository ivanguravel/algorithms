package org.ivzh.sqrt.decomposition;//package sqrt;

import java.io.*;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;


// https://acmp.ru/asp/do/index.asp?main=task&id_course=2&id_section=20&id_topic=45&id_problem=598
public class Superminimum {

    private BufferedReader reader;
    private StringTokenizer tokenizer;
    private PrintWriter writer;

    public static void main(String[] args) {
        new Superminimum().run();
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
        int m = nextInt();
        Decomposition decomposition = new Decomposition(n);
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = nextInt();
            decomposition.add(i, arr);
        }


        int l = 0;
        int r = m-1;
        StringBuilder result = new StringBuilder();

        while (r < arr.length) {
            result.append(decomposition.min(l, r, arr)).append(" ");
            ++l;
            ++r;
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
        int[] min;
        int k;

        public Decomposition(int n) {
            this.k = (int) Math.sqrt(n) +1;
            this.min = new int[k];
        }

        public void add(int i, int[] array) {
            min[i/k] = Math.min(min[i/k], array[i]);
        }

        public int min(int l, int r, int[] array) {
            int answer = Integer.MAX_VALUE;

            for (int i =l; i <= r;) {
                if (i % k == 0 && i+k -1 <= r) {
                    answer = Math.min(answer, min[i/k]);
                    i = i + k;
                } else {
                    answer = Math.min(answer, array[i++]);
                }
            }
            return answer;
        }
    }
}
