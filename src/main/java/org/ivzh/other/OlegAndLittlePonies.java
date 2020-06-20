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

    //
    void solve(StringBuilder answer) {
        for (int i =0 ; i < n; i++) {
            for (Map.Entry<String, String> e : wishes.entrySet()) {
                if (containsSimilarBits(answer, e.getKey())) {
                    fillAnswer(answer, e.getValue());
                }
            }
        }
    }

    boolean containsSimilarBits(StringBuilder answer, String key) {
        return (Integer.parseInt(answer.toString(), 2) & Integer.parseInt(key, 2)) > 0;
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
}
