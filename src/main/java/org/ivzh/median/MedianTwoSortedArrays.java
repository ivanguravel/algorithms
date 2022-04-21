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
    
    public double findMedianSortedArraysTwo(int[] nums1, int[] nums2) {
        int[] medianArray = new int[nums1.length + nums2.length];
        int i = 0;
        int j = 0;
        int k = 0;
        int len1 = nums1.length;
        int len2 = nums2.length;
       
        while(i < len1 && j < len2){
            if(nums1[i] <= nums2[j]){
                medianArray[k++] = nums1[i++];
            }
            else if(nums1[i] >= nums2[j]){
                medianArray[k++] = nums2[j++];
            }
        }
        while(i < len1){
            medianArray[k++] = nums1[i++];
        }
        while(j < len2){
            medianArray[k++] = nums2[j++];
        }  
        
        if(medianArray.length % 2 != 0){
            return medianArray[medianArray.length/2];
        }
        if(medianArray.length % 2 == 0){
            return (medianArray[(medianArray.length - 1)/2] + medianArray[(medianArray.length + 1)/2])/2.0;
        }
        return 0;
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
