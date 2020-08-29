package org.ivzh.binary.search;


// https://leetcode.com/problems/koko-eating-bananas/
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

    private boolean canKokoEatsBanasFrom(int[] piles, int K, int H) {
        int hours = 0;
        for (Integer i : piles) {
            if (i % K != 0) {
                hours = hours + 1;
            }
            hours = hours + i / K;
        }

        return hours <= H;
    }
}
