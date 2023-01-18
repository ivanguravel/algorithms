package org.ivzh.two.pointers;

import java.io.*;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

// https://informatics.msk.ru/mod/statements/view.php?chapterid=111975#1
public class CityChe {

    private BufferedReader reader;
    private StringTokenizer tokenizer;
    private PrintWriter writer;

    public static void main(String[] args) {
        new CityChe().run();
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
        int r = nextInt();

        int[] a = new int[n];

        for (int k =0; k < n; k++) {
            a[k] = nextInt();
        }

        int i=0;
        int j =1;
        int count = 0;

        while (i < a.length -1 && j < a.length) {
            int diff = a[j] - a[i];
            if (diff <= r) {
                ++j;
            } else {
                ++i;
                count += n -j;
            }
        }
        writer.println(count);
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
}
