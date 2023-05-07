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
        int[] result = new int[s.length()];
        for (int i = 1; i < s.length(); i++) {
            int j = result[i-1];
            while (j > 0 && s.charAt(i) != s.charAt(j))
                j = result[j-1];
            if (s.charAt(i) == s.charAt(j))
                j++;
            result[i] = j;
        }
        return result;
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
