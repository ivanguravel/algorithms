package org.ivzh.other;

public class KadaneExercises {

    public static void main(String[] args) {
        int[] arr = {1, -2, 100, 200, 500, -10000, 8, 111, 3};
        System.out.println(kadaneSumMax(arr, 3));
    }

    private static Tuple kadaneSumMax(int[] arr, int k) {
        sanityCheckForArray(arr);

        Integer max = Integer.MIN_VALUE;
        int sequenceCount =0, left= 0, right =0, sum = 0, leftBuff = 0;

        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];

            if (sum > max && sequenceCount == k) {
                max = sum;
                left = leftBuff+1;
                right = i;
            }

            if (sum < 0) {
                sum = 0;
                leftBuff = i;
                sequenceCount = 0;
            }

            ++sequenceCount;
        }

        return new Tuple(left, right, max);
    }

    public static class Tuple {
        int left;
        int right;
        int max;

        public Tuple(int left, int right, int max) {
            this.left = left;
            this.right = right;
            this.max = max;
        }

        @Override
        public String toString() {
            return "Tuple{" +
                    "left=" + left +
                    ", right=" + right +
                    ", max=" + max +
                    '}';
        }
    }

    private static void sanityCheckForArray(int[] arr) {
        if (arr == null || arr.length <= 0) {
            throw new IllegalArgumentException("can't process empty array");
        }
    }
}
