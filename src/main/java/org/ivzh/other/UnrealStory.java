package org.ivzh.other;

import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Collectors;

// https://acm.timus.ru/problem.aspx?space=1&num=1491
public class UnrealStory {

    int n;
    int[][] data;
    int[] result;

    int min = Integer.MAX_VALUE;
    int max = Integer.MIN_VALUE;

    Map<Interval, Integer> container;

    Set<Interval> enabledIntervals = new HashSet<>();

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        new UnrealStory().solve(in, out);
    }

    void solve(Scanner in, PrintWriter out) {
        readData(in);

        for (int i = this.min; i <= this.max; i++) {
            Integer sum = 0;
            prepareEnabledIntervals(i);

            for (Interval enabled : enabledIntervals) {
                sum = sum + container.get(enabled);
            }

            result[i] = sum;
        }

        StringBuilder builder = new StringBuilder();
        for (int i = 1; i < n+1; i++) {
            builder.append(result[i] + " ");
        }

        out.println(builder.toString());
        out.flush();
    }


    // O(n^2)
    void solve2(Scanner in, PrintWriter out) {
        readData(in);

        for (int[] d : data) {
            for (int j = d[0]; j <= d[1]; j++) {
                result[j] = result[j] + d[2];
            }
        }

        StringBuilder builder = new StringBuilder();
        for (int i = 1; i < n+1; i++) {
            builder.append(result[i] + " ");
        }

        out.println(builder.toString());
        out.flush();
    }

    private void readData(Scanner in) {
        this.n = in.nextInt();
        this.result = new int[n+1];
        this.container = new HashMap<>();
        in.nextLine();
        for (int i = 0; i < n+1; i++) {
            String[] line = in.nextLine().split(" ");
            Integer key1 = Integer.parseInt(line[0]);
            Integer key2 = Integer.parseInt(line[1]);
            Integer val = Integer.parseInt(line[2]);


            this.container.put(new Interval(key1, key2), val);

            if (min > key1) {
                min = key1;
            }

            if (min > key2) {
                min = key2;
            }

            if (max < key1) {
                max = key1;
            }

            if (max < key2) {
                max = key2;
            }
        }
    }

    private void prepareEnabledIntervals(int i) {
        Set<Interval> collect = container.keySet()
                .stream()
                .filter(integer -> integer.isInsideInterval(i))
                .collect(Collectors.toSet());

        enabledIntervals.removeAll(enabledIntervals.stream().filter(e -> !e.isInsideInterval(i)).collect(Collectors.toSet()));
        enabledIntervals.addAll(collect);
    }

    static class Interval {
        int one;
        int two;

        public Interval(int one, int two) {
            this.one = one;
            this.two = two;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Interval interval = (Interval) o;
            return one == interval.one &&
                    two == interval.two;
        }

        @Override
        public int hashCode() {
            return Objects.hash(one, two);
        }

        public boolean isInsideInterval(int i) {
            return this.one <= i && i <= this.two;
        }
    }
}
