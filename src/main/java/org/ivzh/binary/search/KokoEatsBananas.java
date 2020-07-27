package org.ivzh.binary.search;

import java.util.Arrays;

public class KokoEatsBananas {


    public static void main(String[] args) {
        int[] arr = {30,11,23,4,20};
        System.out.println(new KokoEatsBananas().minEatingSpeed(arr, 5));
    }

    public int minEatingSpeed(int[] piles, int H) {
        int rightBound = 1_000_000_000;
        int leftBound = 1;

        while (leftBound < rightBound) {
            int middle = leftBound + (rightBound - leftBound) / 2;
            if (canKokoEatsBanasFrom(piles, middle, H)) {
                rightBound = middle;
            } else {
                leftBound = middle + 1;
            }
        }
        return leftBound;
    }

    private boolean canKokoEatsBanasFrom(int[] piles, int middle, int H) {
        int K = 0;
        for (Integer i : piles) {
            if (i % middle == 0) {
                K = K + i / middle;
            } else {
                K = K + i / middle + 1;
            }
        }

        return K <= H;
    }
}
