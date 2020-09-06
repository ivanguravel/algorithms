package org.ivzh.graph;

import java.io.PrintWriter;
import java.util.*;


// https://timus.online/problem.aspx?space=1&num=2108
// TODO
public class OlegAndLittlePoniesNew {

    private static final String EMPTY = "";
    private static final char ONE = '1';
    private static final char ZERO = '0';

    int n;
    int m;


    Map<Integer, List<Integer>> presentToys;
    Map<Integer, List<Integer>> wishes;

    int[] counter;
    boolean[] result;

    Deque<Integer> queue = new ArrayDeque<>();

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        new OlegAndLittlePoniesNew().solve(in, out);
    }

    void solve(Scanner in, PrintWriter out) {
        // read data
        readData(in);

       //
        bfs();

        StringBuilder result = new StringBuilder(EMPTY);

        for (int i = 0; i < n; i++) {
            result.append(this.result[i] ? ONE : ZERO);
        }

        out.println(result.toString());
        out.flush();
    }

    void bfs() {
        while (!queue.isEmpty()) {
            List<Integer> present = presentToys.get(queue.pollFirst());
            if (present != null) {
                for (Integer integer : present) {
                    if (counter[integer] != 0) {
                        --counter[integer];
                        List<Integer> wishesList = wishes.get(integer);
                        for (int i = 0; i < wishesList.size(); i++) {
                            if (!result[wishesList.get(i)]) {
                                result[wishesList.get(i)] = true;
                                queue.push(wishesList.get(i));
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

        this.presentToys = new HashMap<>(4010);
        this.wishes = new HashMap<>(4010);

        this.counter = new int[4010];
        this.result = new boolean[4010];

        in.nextLine();


        for (int i = 0; i < m; i++) {
            String s = in.nextLine();
            String[] split = s.split(" ");

            String forToysRecognition = split[0];
            String forWishes = split[1];

            for (int j = 0; j < n; ++j) {
                if (forToysRecognition.charAt(j) == ONE) {

                    List<Integer> list = presentToys.get(i);

                    if (list == null) {
                        list = new LinkedList<>();

                    }
                    list.add(i);
                    presentToys.put(j, list);
                    ++counter[i];
                }
            }

            for (int j = 0; j < n; ++j) {
                if (forWishes.charAt(j) == ONE) {

                    List<Integer> list = wishes.get(i);

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
                result[i] = true;
            }
        }
    }
}
