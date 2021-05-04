package org.ivzh.arrays;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

// https://codeforces.com/contest/1512/problem/A?locale=ru
public class SpyDetected {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int t = scanner.nextInt();
        for (int i = 0; i < t; i++) {
            int n = scanner.nextInt();
            int[] arr = new int[n];
            for (int j = 0; j < n; j++) {
                arr[j] = scanner.nextInt();
            }
            int result = calculateValue(arr);
            if (result != -1) {
                System.out.println(result);
            }
        }
    }


    private static int calculateValue(int[] arr) {
        Map<Integer, Integer> validator = new HashMap<>();
        for (int i =0; i< arr.length; i++) {
            validator.put(arr[i], validator.getOrDefault(arr[i], 0) + 1);
        }

        List<Integer> collect = validator
                .entrySet()
                .stream().filter(t -> t.getValue() == 1)
                .map(i -> i.getKey())
                .collect(Collectors.toList());

        for (int i =0; i< arr.length; i++) {
            if (arr[i] == collect.get(0)) {
                return i + 1;
            }
        }

        return -1;
    }
}
