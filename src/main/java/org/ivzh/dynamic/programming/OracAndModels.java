package org.ivzh.dynamic.programming;


import java.util.*;

// https://codeforces.com/problemset/problem/1350/B
public class OracAndModels {

    static int n;
    static List<Integer> numbers;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int k = sc.nextInt();
        for (int i = 0; i < k; ++i) {
            n = sc.nextInt();
            numbers = new ArrayList<>(n);
            for (int j = 0; j < n; ++j) {
                numbers.add(j, sc.nextInt());
            }

            System.out.println(solve());
        }

        sc.close();
    }

    private static int solve() {
        List<Integer> extremums = createExtremums(numbers.size());

        Integer max;
        for (int i = 0; i < extremums.size(); ++i) {
            for (int j = i * 2 + 1; j < extremums.size(); j = j + i + 1) {
                if (numbers.get(j) > numbers.get(i)) {
                    max = Math.max(extremums.get(j), extremums.get(i) + 1);
                    extremums.set(j, max);
                }
            }
        }

        return extremums.parallelStream().max(Integer::compareTo).get();
    }

    private static List<Integer> createExtremums(int size) {
        List<Integer> extremums = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            extremums.add(1);
        }
        return extremums;
    }
}
