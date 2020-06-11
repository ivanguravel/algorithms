package org.ivzh.other;

import java.io.PrintWriter;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

// https://timus.online/problem.aspx?space=1&num=2108
public class OlegAndLittlePonies {

    private static final String EMPTY = "";
    private static final char ONE = '1';

    int n;
    int m;
    Map<String, String> wishes = new TreeMap<>();
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

        for (int i = 0; i < n; i++) {
            if (presentToys.charAt(i) == ONE || answerBuilder.charAt(i) == ONE) {
                int zeroesNeed2BeAdded = n - i;
                String initialWithSuffix = createStringWithZerosInSuffix(i, presentToys, zeroesNeed2BeAdded, answerBuilder);
                String wish = wishes.get(initialWithSuffix);
                if (wish != null && !wish.isEmpty()) {
                    fillAnswer(answerBuilder, wish);
                }
            }
        }

        System.out.println(answerBuilder.toString());
    }

    private void readData(Scanner in) {
        this.n = in.nextInt();
        this.m = in.nextInt();
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
