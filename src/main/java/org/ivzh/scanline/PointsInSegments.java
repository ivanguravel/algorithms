package org.ivzh.scanline;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

import static java.lang.Integer.parseInt;

// https://codeforces.com/problemset/problem/1015/A
public class PointsInSegments {

    private BufferedReader reader;
    private StringTokenizer tokenizer;
    private PrintWriter writer;

    public static void main(String[] args) {
        new PointsInSegments().run();
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
        int m = nextInt();

        int[][] segments = new int[n][2];

        for (int i = 0; i < n; i++) {
            segments[i][0] = nextInt();
            segments[i][1] = nextInt();
        }

        Map<Integer, Integer> answer = new HashMap<>();

        for (int i =1; i <= m ;i++) {
            answer.put(i, 0);
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 0; j < n; j++) {
                if (segments[j][0] <= i && segments[j][1] >= i) {
                    answer.merge(i, 1, Integer::sum);
                }
            }
        }

        List<Map.Entry<Integer, Integer>> entries =
                answer.entrySet().stream().filter(e -> e.getValue() <= 0).collect(Collectors.toList());

        writer.println(entries.size());

        if (entries.size() <= 0) {
            return;
        }

        StringBuilder result = new StringBuilder();
        for (Map.Entry<Integer, Integer> e : entries) {
            result.append(e.getKey()).append(" ");
        }

        writer.println(result.toString());
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
