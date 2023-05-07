package org.ivzh.strings;

import java.io.*;
import java.util.StringTokenizer;

// https://informatics.msk.ru/mod/statements/view.php?chapterid=1324#1
public class ZFunction {


    private BufferedReader reader;
    private StringTokenizer tokenizer;
    private PrintWriter writer;

    public static void main(String[] args) {
        new ZFunction().run();
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

        int[] arr = zFunction(s);

        for (int i : arr) {
            writer.print(i + " ");
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

    public void findPalindromes(String s, int[] arr) {
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
