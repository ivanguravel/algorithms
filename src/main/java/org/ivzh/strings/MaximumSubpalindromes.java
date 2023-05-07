package org.ivzh.strings;

import java.io.*;
import java.util.StringTokenizer;

// https://informatics.msk.ru/mod/statements/view.php?chapterid=1326#1
public class MaximumSubpalindromes {

    private BufferedReader reader;
    private StringTokenizer tokenizer;
    private PrintWriter writer;

    public static void main(String[] args) {
        new MaximumSubpalindromes().run();
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

        int[] arr = new int[s.length()];

        longestPalindrome(s, arr);

        for (int i : arr) {
            writer.print(i + " ");
        }
    }

    // N^2 solution
    public void longestPalindrome(String s, int[] arr) {
        if (s == null || s.length() < 1)  {
            return;
        } else {

            for (int i = 0; i < s.length(); i++) {
                int lenForPalindromeWhenOneLetterIsDifferent = expandInTheMiddle(s, i, i);
                int len1 = expandInTheMiddle(s, i, i+1);
                int result = Math.max(len1, lenForPalindromeWhenOneLetterIsDifferent);

                int start = i - (result - 1) /2;
                int end = i + result  /2;

                arr[i] = end +1 - start;
            }
        }
    }


    private static int expandInTheMiddle(String s, int left, int right) {
        int l = left;
        int r = right;
        while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
            --l;
            ++r;
        }
        return r - l -1;
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
