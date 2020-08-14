package org.ivzh.other;

import java.io.PrintWriter;
import java.util.*;

// https://acm.timus.ru/problem.aspx?space=1&num=1491
public class UnrealStory {

    int n;
    int[][] data;
    int[] result;

    TreeMap<Integer, Integer> tree;
    Map<Tuple, Integer> container;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        new UnrealStory().solve(in, out);
    }

    // O(n*logn)
    void solve(Scanner in, PrintWriter out) {
        readData(in);

        for (int i = this.tree.firstKey(); i <= this.tree.lastKey(); i++) {
            Integer sum = 0;
            if (this.tree.containsKey(i)) {
                sum = sum + this.tree.get(i);
            }

            Map.Entry<Integer, Integer> prev = this.tree.lowerEntry(i);
            Map.Entry<Integer, Integer> next = this.tree.higherEntry(i);

            if (prev != null && next != null) {
                Integer prevKey = prev.getKey();
                Integer nextKey = next.getKey();

                Tuple tuple = new Tuple(prevKey, nextKey);
                if (prevKey < i && i < nextKey && this.container.containsKey(tuple)) {
                    sum = sum + this.container.get(tuple);
                }
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
        this.data = new int[n+1][3];
        this.result = new int[n+1];
        this.tree = new TreeMap<>();
        this.container = new HashMap<>();
        in.nextLine();
        for (int i = 0; i < n+1; i++) {
            String[] line = in.nextLine().split(" ");
            Integer key1 = Integer.parseInt(line[0]);
            Integer key2 = Integer.parseInt(line[1]);
            Integer val = Integer.parseInt(line[2]);

            this.data[i][0] = key1;
            this.data[i][1] = key2;
            this.data[i][2] = val;

            this.container.put(new Tuple(key1, key2), val);

            if (this.tree.containsKey(key1)) {
                Integer val1 = this.tree.get(key1);
                this.tree.put(key1, val1 + val);
            } else {
                this.tree.put(key1, val);
            }

            if (!key1.equals(key2)) {
                if (this.tree.containsKey(key2)) {
                    Integer val1 = this.tree.get(key2);
                    this.tree.put(key2, val1 + val);
                } else {
                    this.tree.put(key2, val);
                }
            }

        }
    }

    static class Tuple {
        int one;
        int two;

        public Tuple(int one, int two) {
            this.one = one;
            this.two = two;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Tuple tuple = (Tuple) o;
            return one == tuple.one &&
                    two == tuple.two;
        }

        @Override
        public int hashCode() {
            return Objects.hash(one, two);
        }
    }
}
