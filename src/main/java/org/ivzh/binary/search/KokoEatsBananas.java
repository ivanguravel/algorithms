package org.ivzh.binary.search;

import java.util.Arrays;

public class KokoEatsBananas {


    public static void main(String[] args) {

    }

    public int minEatingSpeed(int[] piles, int H) {
        int rightBound = Arrays.stream(piles).max().getAsInt();
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

    private boolean canKokoEatsBanasFrom(int[] piles, int middle, int h) {

        return true;
    }
}
