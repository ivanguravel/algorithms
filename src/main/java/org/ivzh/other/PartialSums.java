package org.ivzh.other;

import java.util.Arrays;

public class PartialSums {

    private final int[] partialSums;

    public PartialSums(int[] partialSums) {
        this.partialSums = partialSums;

        for (int i = 1; i < this.partialSums.length; i++) {
            this.partialSums[i] += partialSums[i-1];
        }

        System.out.println("partial sums has been initialized");
        System.out.println(Arrays.toString(this.partialSums));
    }

    private int sumOnRangeViaConstantTime(int l, int r) {
        return partialSums[r] - partialSums[l-1];
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};

        PartialSums partialSums = new PartialSums(arr);

        System.out.println(partialSums.sumOnRangeViaConstantTime(5, 6));
        System.out.println(partialSums.sumOnRangeViaConstantTime(1, 3));
        System.out.println(partialSums.sumOnRangeViaConstantTime(1, 2));
    }
}
