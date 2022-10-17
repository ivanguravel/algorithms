package org.ivzh.binary.search;

// https://algocademy.com/app/#problem/searching/lang/java/solution/2-1
public class BinarySearch {


    public int stupid(int[] nums, int value) {
        int start = 0;
        int end = nums.length - 1;
        int middle = -1;
        while (start <= end) {
            middle = (start + end) / 2;
            if (nums[middle] == value) {
                return middle;
            } else if (nums[middle] > value) {
                end = middle - 1;
            } else {
                start = middle + 1;
            }
        }
        return -1;
    }
    
    
    public static void main(String[] args) {
        // 1 Найти позицию правого нуля в больщой строке. 00000000000000000111111111
        String s = "00000000000000000111111111";
        String s0 = "";
        String s1 = "01";
        String s2 = "1111";

        System.out.println(upperBound(-1, s.length(), c -> s.charAt(c) < '1'));
        System.out.println(upperBound(-1, s0.length(), c -> s0.charAt(c) < '1'));
        System.out.println(upperBound(-1, s1.length(), c -> s1.charAt(c) < '1'));
        System.out.println(upperBound(-1, s2.length(), c -> s2.charAt(c) < '1'));

        // 1. Найти индекс вставки элемента в сортированный массив.
        System.out.println("======== insert index =========");
        int[] arr = {0, 1, 2, 4, 5, 6};

        System.out.println(upperBound(-1, arr.length, integer -> arr[integer] <= 3) + 1);
        System.out.println(upperBound(-1, arr.length, integer -> arr[integer] <= 7) + 1);
        System.out.println(upperBound(-1, arr.length, integer -> arr[integer] <= -1));
        // 2. Найти первую и последнюю позицию x в отсортированном массиве если она есть.
        System.out.println("======== lower & upper bound =========");
        int[] arr0 = {0, 1, 2, 4, 5, 5, 5, 5, 6};
        System.out.println(lowerBound(-1, arr0.length, integer -> arr0[integer] > 4));
        System.out.println(upperBound(-1, arr0.length, integer -> arr0[integer] < 6));

        // 3. Дается массив который строго падает, потом поднимается. Найти экстремум позицию. [6, 4, 3, 2, 5, 6, 7, 12, 20]
        System.out.println("======== extremum =========");
        int[] arr1 = new int[]{6, 4, 3, 2, 5, 6, 7, 12, 20, 0, 1};
        System.out.println(upperBound(-1, arr1.length-1, integer -> arr1[integer] > arr1[integer -1] && arr1[integer] < arr1[integer + 1]) +1);

    }

    private static int upperBound(int inclusiveLeft, int exclusiveRight, Predicate<Integer> predicate) {
        while (exclusiveRight - inclusiveLeft > 1) {
            int middle = inclusiveLeft + (exclusiveRight - inclusiveLeft) / 2;
            if (predicate.test(middle)) {
                inclusiveLeft = middle;
            } else {
                exclusiveRight = middle;
            }
        }
        return inclusiveLeft;
    }

    private static int lowerBound(int exclusiveLeft, int inclusiveRight, Predicate<Integer> predicate) {
        while (inclusiveRight - exclusiveLeft > 1) {
            int middle = exclusiveLeft + (inclusiveRight - exclusiveLeft) / 2;
            if (predicate.test(middle)) {
                inclusiveRight = middle;
            } else {
                exclusiveLeft = middle;
            }
        }
        return inclusiveRight;
    }
    
    static double findRootNthCount(int n, int m) {
        double l=1, r=1000;

        double epsilon = 0.00000001;

        double guess = (l + r) / 2;
        while (Math.abs((n - Math.pow(guess, m)))
                >= epsilon) {
            guess = (l + r) / 2;
            if (Math.pow(guess, n) > n) {
                r = guess;
            }
            else {
                l = guess;
            }

        }

        return guess;
    }
    
    private int lowerBinarySearch(int i) {
        int l = 0;
        int r = arr.length;

        while (r - l > 1) {
            int m = (l+r) / 2;

            if (arr[m] > i) {
                r = m;
            } else {
                l = m;
            }
        }

        return r;
    }

    private int upperBinarySearch(int i) {
        int l = 0;
        int r = arr.length;

        while (r - l > 1) {
            int m = (l+r) / 2;

            if (arr[m] >= i) {
                r = m;

            } else {
                l = m;
            }
        }

        return r+1;
    }
}
