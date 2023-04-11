package org.ivzh.scanline;

import java.io.*;
import java.util.*;

import static java.lang.Integer.parseInt;

// https://informatics.msk.ru/mod/statements/view.php?chapterid=112542#1
public class PointsAndLineSegments {

    private static final int OPEN_BRACKET = 0;
    private static final int CLOSED_BRACKET = 2;
    private static final int POINT = 1;

    private BufferedReader reader;
    private StringTokenizer tokenizer;
    private PrintWriter writer;

    public static void main(String[] args) {
        new PointsAndLineSegments().run();
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
        List<Event> container = new ArrayList<>(2*n+m);
        for (int i = 0; i < n; i++) {
            Integer x = nextInt();
            Integer y = nextInt();

            container.add(new Event(x, OPEN_BRACKET));
            container.add(new Event(y, CLOSED_BRACKET));
        }

        List<Integer> points = new LinkedList<>();
        for (int i =0; i < m; i++) {
            int point = nextInt();
            container.add(new Event(point, POINT));
            points.add(point);
        }

        Collections.sort(container);

        Map<Integer, Integer> pointCounts = new HashMap<>();
        int count = 0;
        for (Event e : container) {
            if (0 == e.bracket) {
                ++count;
            } else if (2 == e.bracket){
                --count;
            } else if (1 == e.bracket) {
                if (!pointCounts.containsKey(e.x)) {
                    pointCounts.put(e.x, Math.max(count, 0));
                } else {
                    pointCounts.put(e.x, pointCounts.get(e.x) + Math.max(count, 0));
                }
            }
        }

        StringBuilder builder = new StringBuilder();
        for (Integer point : points) {
            builder.append(pointCounts.get(point)).append(" ");
        }

        writer.println(builder);

    }

    static class Event implements Comparable<Event>{
        public int x;
        public int bracket;

        @Override
        public int compareTo(Event o) {
            int result = Integer.compare(this.x, o.x);
            return result == 0 ? Integer.compare(this.bracket, o.bracket) : result;
        }

        public Event(int x, int bracket) {
            this.x = x;
            this.bracket = bracket;
        }
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
