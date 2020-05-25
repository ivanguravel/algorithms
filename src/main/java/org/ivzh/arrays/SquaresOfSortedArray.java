package org.ivzh.arrays;

import java.util.Arrays;
import java.util.stream.Stream;

// https://leetcode.com/problems/squares-of-a-sorted-array/
public class SquaresOfSortedArray {

    public int[] sortedSquares(int[] A) {
        for (int i =0; i < A.length; i++) {
            A[i] = square(A[i]);
        }
        Arrays.sort(A);
        return A;
    }

    private int square(int a) {
        return a*a;
    }
}
