package org.ivzh.sort;

import java.util.*;

// https://leetcode.com/problems/asteroid-collision/
public class AsteroidCollision {

    public static void main(String[] args) {
        int[] a = {1,-2,-2,-2};

        System.out.println(new AsteroidCollision().asteroidCollision(a));
    }

    public int[] asteroidCollision(int[] asteroids) {
        mergeSort(asteroids, 0, asteroids.length - 1);
        return Arrays.stream(asteroids).filter(a -> a != Integer.MIN_VALUE).toArray();
    }

    void mergeSort(int asteroids[], int left, int right) {
        if (left < right) {
            int middle = (left + right) / 2;

            mergeSort(asteroids, left, middle);
            mergeSort(asteroids, middle + 1, right);

            merge(asteroids, left, middle, right);
        }
    }


    void merge(int asteroids[], int left, int middle, int right) {
        int leftBound = left;
        while (leftBound < asteroids.length && asteroids[leftBound] <= 0) {
            leftBound++;
        }

        int rightBound = right;
        while (rightBound > middle && asteroids[rightBound] >= 0) {
            rightBound--;
        }

        leftBound--;
        rightBound++;

        int i = middle;
        int j = middle + 1;
        while (i >= leftBound + 1 && j < rightBound) {
            if (Math.abs(asteroids[i]) == Math.abs(asteroids[j])) {
                asteroids[i--] = Integer.MIN_VALUE;
                asteroids[j++] = Integer.MIN_VALUE;
            } else if (Math.abs(asteroids[i]) < Math.abs(asteroids[j])) {
                asteroids[i--] = Integer.MIN_VALUE;
            } else {
                asteroids[j++] = Integer.MIN_VALUE;
            }
        }
    }
}
