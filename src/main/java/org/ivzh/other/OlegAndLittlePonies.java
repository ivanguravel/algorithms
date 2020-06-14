package org.ivzh.other;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// https://timus.online/problem.aspx?space=1&num=2108
public class OlegAndLittlePonies {

    private static final String EMPTY = "";
    private static final char ONE = '1';

    int n;
    int m;
    Map<String, String> wishes;
    String base;
    String presentToys;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        new OlegAndLittlePonies().solve(in, out);
    }

    void solve(Scanner in, PrintWriter out) {
        // read data
        readData(in);

        StringBuilder answerBuilder = new StringBuilder(presentToys);

        solve(answerBuilder);

        System.out.println(answerBuilder.toString());
    }

    void solve(StringBuilder answer) {
        for (int i = 0; i < n; i++) {
            int zeroesNeed2BeAdded = n - i;
            String wish = wishes.get(createStringWithZerosInSuffix(i, presentToys, zeroesNeed2BeAdded, answer));
            String wishBackup = null;
            while (wish != null && !wish.isEmpty() && !wish.equalsIgnoreCase(wishBackup)) {
                wishBackup = wish;
                fillAnswer(answer, wish);
                wish = wishes.get(wish);
            }
        }


        for (int i = 0; i < n; i++) {
            for (int j =1; j < n; j++) {
                StringBuilder fillUnused = new StringBuilder(this.base);
                fillUnused.setCharAt(i, answer.charAt(i));
                fillUnused.setCharAt(j, answer.charAt(j));
                String wish = wishes.get(fillUnused.toString());
                String wishBackup = null;
                while (wish != null && !wish.isEmpty() && !wish.equalsIgnoreCase(wishBackup)) {
                    wishBackup = wish;
                    fillAnswer(answer, wish);
                    wish = wishes.get(wish);
                }
            }
        }
    }

    private void readData(Scanner in) {
        this.n = in.nextInt();
        this.m = in.nextInt();
        this.wishes = new HashMap<>(m + 1);
        this.base = Stream.generate(() -> "0").limit(n).collect(Collectors.joining());
        in.nextLine();
        for (int i = 0 ; i < m; i++) {
            String s = in.nextLine();
            String[] split = s.split(" ");
            wishes.put(split[0], split[1]);
        }
        this.presentToys = in.nextLine();
    }

    private void fillAnswer(StringBuilder answerBuilder, String wish) {
        for (int i = 0; i < wish.length(); i++) {
            if (wish.charAt(i) == ONE) {
                answerBuilder.setCharAt(i, ONE);
            }
        }
    }

    private static String createStringWithZerosInSuffix(int prefixSize, String initial, int suffixSize, StringBuilder answerBuilder) {
        StringBuilder prefix = new StringBuilder(initial.substring(0, prefixSize));
        for (int j = 0; j < prefixSize; ++j) {
            if (answerBuilder.charAt(j) == ONE) {
                prefix.setCharAt(j , ONE);
            }
        }
        StringBuilder suffix = new StringBuilder();
        for (int i = 0; i < suffixSize; i++) {
            suffix.append("0");
        }

        return String.join(EMPTY, prefix.toString(), suffix.toString());
    }
}
