package org.ivzh.graph;

import java.io.PrintWriter;
import java.util.*;


// https://timus.online/problem.aspx?space=1&num=2108
// TODO
public class OlegAndLittlePonies3 {

    private static final String EMPTY = "";
    private static final char ONE = '1';
    private static final char ZERO = '0';

    int n;
    int m;


    Map<Integer, List<Integer>> store;
    Map<Integer, List<Integer>> wishes;

    int[] counter;
    boolean[] visited;

    Deque<Integer> queue = new ArrayDeque<>();

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        new OlegAndLittlePonies3().solve(in, out);
    }

    void solve(Scanner in, PrintWriter out) {
        // read data
        readData(in);

        dfs();

        StringBuilder result = new StringBuilder(EMPTY);

        for (int i = 0; i < n; i++) {
            result.append(visited[i] ? ONE : ZERO);
        }

        out.println(result.toString());
        out.flush();
    }

    void dfs() {
        while (!queue.isEmpty()) {
            List<Integer> stored = store.get(queue.poll());
            if (stored != null) {
                for (Integer integer : stored) {

                    if (counter[integer] != 0) {
                        --counter[integer];
                        List<Integer> integers = wishes.get(integer);
                        for (int i = 0; i < wishes.size(); i++) {
                            if (integers != null && integers.size() > i) {
                                if (!visited[integers.get(i)]) {
                                    visited[integers.get(i)] = true;
                                    queue.push(integers.get(i));
                                }
                            }
                        }
                    }
                }
            }
        }
    }


    private void readData(Scanner in) {
        this.n = in.nextInt();
        this.m = in.nextInt();

        this.store = new HashMap<>(4010);
        this.wishes = new HashMap<>(4010);

        this.counter = new int[4010];
        this.visited = new boolean[4010];

        Arrays.fill(this.visited, false);

        in.nextLine();


        for (int i = 0; i < m; i++) {
            String s = in.nextLine();
            String[] split = s.split(" ");

            String forToysRecognition = split[0];
            String forWishes = split[1];

            for (int j = 0; j < n; ++j) {
                if (forToysRecognition.charAt(j) == ONE) {

                    List<Integer> list = store.get(j);

                    if (list == null) {
                        list = new LinkedList<>();

                    }
                    list.add(i);
                    store.put(j, list);
                    ++counter[i];
                }
            }

            for (int j = 0; j < n; ++j) {
                if (forWishes.charAt(j) == ONE) {

                    List<Integer> list = store.get(i);

                    if (list == null) {
                        list = new LinkedList<>();
                    }

                    list.add(j);

                    wishes.put(i, list);
                }
            }


        }

        String presentToys = in.nextLine();
        for (int i = 0; i < n; ++i) {
            if (presentToys.charAt(i) == ONE) {
                queue.add(i);
                visited[i] = true;
            }
        }
    }
}
