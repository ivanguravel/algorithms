package org.ivzh.backtracking;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;
import static java.lang.Math.min;

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
        //int k = nextInt();
        //binaryStringsWhichAreAboutKOnesOnly(new LinkedList<>(), n, k,0);

//        LinkedList<String> list = new LinkedList<>();
//        for (int i = 1; i <= n; i++) {
//            list.add(Integer.toString(i));
//        }
//        permutations(list, n, 0);

//        int[] set = new int[n+1];
//        int count = 0;
//        for (int i = 1; i <= n; i++) {
//            set[i] = i;
//        }
//        kDigitsWithLengthN(set, "", n, k, 0);

//        List<List<String>> result = new LinkedList<>();
//        termsDecomposition2(new LinkedList<>(), n, n, result);
//        Collections.reverse(result);
//        for (List<String> s : result) {
//            writer.println(String.join(" ", s));
//        }

        correctParenthesis(0, n, new LinkedList<>());
    }

    private static void termsDecomposition2(LinkedList<Integer> list, int n, int lastTaken, List<List<String>> result) {
        if (n==0) {
            result.add(list.stream().map(String::valueOf).collect(Collectors.toList()));
            return;
        }


        for (int k = min(n,lastTaken); k >= 1; k--) {
            list.add(k);
            termsDecomposition2(list, n-k, k ,result);
            list.removeLast();
        }

    }

    private static void termsDecomposition(LinkedList<Integer> list, int n, int lastTaken) {
        if (n==0) {
            System.out.println(String.join(" ", list.stream().map(String::valueOf).collect(Collectors.toList())));
            return;
        }


        for (int k = min(n,lastTaken); k >= 1; k--) {
            list.add(k);
            termsDecomposition(list, n-k, k);
            list.removeLast();
        }

    }

    void correctParenthesis2(int balance, int n, String s) {
        if (s.length() == n) {
            writer.println(s);
            return;
        }

        if (balance < n/2) {
            correctParenthesis2(balance+1, n, s + "(");
        }

        if (balance > 0) {
            correctParenthesis2(balance-1, n, s + ")");
        }

    }

    void correctParenthesis(int open, int closed, LinkedList<String> s) {
        if (open <= closed) {
            if (open <= 0 && closed == 0) {
                writer.println(String.join("", s));
                return;
            }  else {
                s.add("(");
                correctParenthesis(open - 1, closed, new LinkedList<>(s));
                s.removeLast();
                s.add("(");
                correctParenthesis(open, closed - 1, new LinkedList<>(s));
                s.removeLast();
            }
        }

    }


    void kDigitsWithLengthN(int[] set,
                            String s,
                            int n, int k, int start) {
        if (start ==k) {
            boolean isIncreasing = true;
            for (int i = 1; i < s.length(); i++) {
                Integer first = Character.getNumericValue(s.charAt(i));
                Integer second = Character.getNumericValue(s.charAt(i-1));

                // if (first >= second) { - for task H
                if (first >= second) {
                    isIncreasing = false;
                    break;
                }
            }
            if (isIncreasing) {
                StringBuilder sb = new StringBuilder();
                for (char c : s.toCharArray()) {
                    sb.append(c).append(" ");
                }
                writer.println(sb.toString());
            }
            return;
        }

        for (int i = 1; i <= n; i++) {
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

