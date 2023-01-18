package org.ivzh.two.pointers;

import java.io.*;
import java.util.*;

import static java.lang.Integer.parseInt;

//  https://codeforces.com/problemset/problem/1354/B
public class TernaryString {

    private BufferedReader reader;
    private StringTokenizer tokenizer;
    private PrintWriter writer;

    public static void main(String[] args) {
        new TernaryString().run();
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
        int t = nextInt();
        List<String> strings = new ArrayList<>(t);
        List<Integer> result = new ArrayList<>(t);

        for (int i =0; i < t; i++) {
            lengthCalculator(nextToken(), result);
        }

        for (Integer i : result) {
            writer.println(i);
        }
    }

    private void lengthCalculator(String s, List<Integer> result) {
        if (s.length() < 3) {
            result.add(0);
            return;
        }

        Map<Character, Integer> map = new HashMap<>();
        map.put('1', 1);
        map.put('2', 1);
        map.put('3', 1);

        Map<Character, Integer> frequencySubstring = new HashMap<>();

        int i =0;
        int j =0;
        int start =0;
        int end = 0;
        int min = Integer.MAX_VALUE;

        while (i < s.length()) {
            while (!checkFrequency(frequencySubstring, map) && j < s.length()) {
                char c = s.charAt(j++);
                frequencySubstring.merge(c, 1, Integer::sum);
            }

            int diff = j - i;
            if (checkFrequency(frequencySubstring, map) && diff < min) {
                min = Math.min(min, diff);
                start = i;
                end = j;
            }
            frequencySubstring.put(s.charAt(i), frequencySubstring.get(s.charAt(i)) - 1);
            ++i;
        }


        result.add(end - start);
    }

    private boolean checkFrequency(Map<Character, Integer> forValidation, Map<Character, Integer> map) {
        for (Character ch : map.keySet()) {
            if (!forValidation.containsKey(ch) || forValidation.get(ch) < map.get(ch)) {
                return false;
            }
        }
        return true;
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
}
