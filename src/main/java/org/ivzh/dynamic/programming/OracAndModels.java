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
            numbers = new ArrayList<>(n+1);
            for (int j = 0; j < n; ++j) {
                numbers.add(j, sc.nextInt());
            }

            System.out.println(solve(numbers));
        }

        sc.close();
    }

    private static int solve(List<Integer> localNumbers) {
        List<Integer> extremums = createExtremums(localNumbers.size());

        for (int i = 1; i < localNumbers.size(); ++i) {
            for (int j = 0; j <= i; j++) {
                if (localNumbers.get(j) > localNumbers.get(i)) {
                    extremums.set(j, Math.max(extremums.get(i), extremums.get(i) + 1));
                }
            }
        }

        return extremums.parallelStream().max(Integer::compareTo).get();
    }

    private static List<Integer> createExtremums(int size) {
        List<Integer> extremums = new ArrayList<>(size+1);

        for (int i = 0; i <= size; i++) {
            extremums.add(1);
        }
        return extremums;
    }
}
