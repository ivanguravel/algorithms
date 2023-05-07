package org.ivzh.strings;

import java.io.*;
import java.util.StringTokenizer;

// https://informatics.msk.ru/mod/statements/view.php?chapterid=3369#1
public class FindSubstringInString {

    private BufferedReader reader;
    private StringTokenizer tokenizer;
    private PrintWriter writer;

    public static void main(String[] args) {
        new FindSubstringInString().run();
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
        String p = nextToken();



        int[] arr = zFunction(s + "#" + p);

        StringBuilder sb = new StringBuilder();
        int count = 0;
        for (int i : arr) {
            if (i == p.length()) {
                count++;
                sb.append(i).append(" ");
            }
        }

        writer.println(count);
        if (count >0) {
            writer.println(sb);
        }
    }

    private int[] zFunction(String s) {
        int result[] = new int[s.length()];


        int l = 0;
        int r = 0;

        for(int i = 1; i < s.length(); i++) {
            result[i] = 0;
            if (r > i) {
                result[i] = Math.min(r - i, result[i - l]);
            }
            while (i + result[i] < s.length() && s.charAt(i+result[i]) == s.charAt(result[i])) {
                result[i]++;
            }
            if (i + result[i] > r) {
                l = i;
                r = i + result[i];
            }
        }

        result[0] = s.length();

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
