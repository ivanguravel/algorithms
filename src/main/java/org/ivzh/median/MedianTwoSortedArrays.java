package org.ivzh.median;

import java.util.Comparator;
import java.util.PriorityQueue;

// https://leetcode.com/problems/median-of-two-sorted-arrays
public class MedianTwoSortedArrays {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        MedianFinder medianFinder = new MedianFinder();
        for (int i : nums1) {
            medianFinder.addNum(i);
        }

        for (int j : nums2) {
            medianFinder.addNum(j);
        }

        return medianFinder.findMedian();
    }

    static class MedianFinder {

        private static final double RADIX = 0.0;

        private final PriorityQueue<Integer> left;
        private final PriorityQueue<Integer> right;

        public MedianFinder() {
            this.left = new PriorityQueue<>(Comparator.reverseOrder());
            this.right = new PriorityQueue<>();
        }

        public void addNum(int num) {
            if(left.isEmpty() || num < left.peek()){
                left.add(num);
            } else {
                right.add(num);
            }

            if (left.size() > right.size() + 1){
                right.add(left.poll());
            }

            if (right.size() > left.size() + 1){
                left.add(right.poll());
            }
        }

        public double findMedian() {
            if (left.size() == right.size()) {
                int peeksSum = left.peek() + right.peek();
                return (peeksSum + RADIX) / 2;
            } else if (right.size() > left.size()) {
                return right.peek();
            } else {
                return left.peek();
            }
        }
    }
}
