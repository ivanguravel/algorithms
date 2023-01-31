package org.ivzh.backtracking;

import java.io.*;
import java.util.Collections;
import java.util.LinkedList;
import java.util.StringTokenizer;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

// https://informatics.msk.ru/mod/statements/view.php?id=23327&chapterid=84#1
public class EasyBinaryAndPermutationsPrintingBacktracking {

    private BufferedReader reader;
    private StringTokenizer tokenizer;
    private PrintWriter writer;

    public static void main(String[] args) {
        new EasyBinaryAndPermutationsPrintingBacktracking().run();
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
        int k = nextInt();
        //binaryStringsWhichAreAboutKOnesOnly(new LinkedList<>(), n, k,0);

//        LinkedList<String> list = new LinkedList<>();
//        for (int i = 1; i <= n; i++) {
//            list.add(Integer.toString(i));
//        }
//        permutations(list, n, 0);

        int[] set = new int[k];
        int count = 0;
        for (int i = 0; i < k; i++) {
            set[i] = i;
        }
        kDigitsWithLengthN(set, "", n, k, 0);
    }


    void kDigitsWithLengthN(int[] set,
                            String s,
                            int n, int k, int start) {
        if (start ==k) {
            writer.println(s);
            return;
        }

        for (int i = 0; i < n; i++) {
            kDigitsWithLengthN(set, s + set[i], n, k, start +1);
        }
    }


    private void permutations(LinkedList<String> list, int n, int start) {
        if (start == n) {
            writer.println(String.join("", list));
            return;
        }

        for (int i = start; i < list.size(); i++) {
            Collections.swap(list, start, i);
            permutations(list, n, start+1);
            Collections.swap(list, i, start);
        }
    }



    public void binaryStringsBacktrack(LinkedList<String> list, int n, String param1, String param2) {
        if (list.size() == n) {
            writer.println(String.join("", list));
            return;
        }

        list.add(param1);
        binaryStringsBacktrack(list, n, param1, param2);
        list.removeLast();
        list.add(param2);
        binaryStringsBacktrack(list, n, param1, param2);
        list.removeLast();
    }

    public void binaryStringsWhichAreAboutKOnesOnly(LinkedList<String> list, int n, int k, int count) {
        if (list.size() == n) {
            if (count == k) {
                writer.println(String.join("", list));
            }
            return;
        }

        list.add("0");
        binaryStringsWhichAreAboutKOnesOnly(list, n, k, count);
        list.removeLast();
        list.add("1");
        binaryStringsWhichAreAboutKOnesOnly(list, n, k, count+1);
        list.removeLast();
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
    private long nextLong() {
        return parseLong(nextToken());
    }
    private double nextDouble() {
        return parseDouble(nextToken());
    }
}
