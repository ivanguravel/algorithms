package org.ivzh.dynamic.programming;

// https://algocademy.com/app/#problem/permutations-of-given-length
public class PermutationsGivenLength {
    public int countPermutations(int n) {
        int[] arr = new int[n+1];
        arr[0] = 0;
        arr[1] = 1;

        for (int i=2; i <=n; i++) {
            arr[i] = i*arr[i-1];
        }
        return arr[n];
    }
}
