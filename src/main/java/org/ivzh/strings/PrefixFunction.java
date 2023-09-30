package org.ivzh.strings;

import java.io.*;
import java.util.StringTokenizer;

// https://informatics.msk.ru/mod/statements/view.php?chapterid=1323#1
public class PrefixFunction {

    private BufferedReader reader;
    private StringTokenizer tokenizer;
    private PrintWriter writer;

    public static void main(String[] args) {
        new PrefixFunction().run();
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
        String s = nextToken();
        int [] arr = prefixFunction(s);
        for (int i : arr) {
            writer.print(i + " ");
        }
    }

    public int[] prefixFunction(String s) {
        int[] pi = new int[s.length()];
        int  j = 0;
        int i =1;

        while (i < s.length()) {
            if (s.charAt(i) == s.charAt(j)) {
                pi[i] = j+1;
                ++j;
                ++i;
            } else {
                if (j == 0) {
                    pi[i] =0;
                    ++i;
                } else {
                    j = pi[j-1];
                }
            }
        }

        return pi;
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

}
