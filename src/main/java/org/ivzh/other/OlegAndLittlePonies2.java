package org.ivzh.other;

import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// https://timus.online/problem.aspx?space=1&num=2108
public class OlegAndLittlePonies2 {

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

        new OlegAndLittlePonies2().solve(in, out);
    }

    // 0(n^3)
    void solve(Scanner in, PrintWriter out) {
        // read data
        readData(in);

        StringBuilder answerBuilder = new StringBuilder(presentToys);

        dfs(answerBuilder, presentToys);

        out.println(answerBuilder.toString());
        out.flush();
    }

    // 0(n^3)
    void dfs(StringBuilder answer, String start) {
        List<String> wishes = wishes.get(start);

        Deque<String> deque = new ArrayDeque<>();

        for (String wish : wishes) {
            if (containsSimilarBits(answer, wish)) {
                deque.add(wish);
            }
        }

        while (!deque.isEmpty()) {
            String wish = deque.pollFirst();
            fillAnswer(answer, wish);
            dfs(answer, wish);
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

    // 0(1)
    private String createStringWithZerosInSuffix(int prefixSize, StringBuilder answerBuilder) {
        return new StringJoiner(EMPTY, answerBuilder.toString().substring(0, prefixSize), base.substring(prefixSize)).toString();
    }
}
