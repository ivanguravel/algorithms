package org.ivzh.arrays;

import java.util.HashSet;
import java.util.Set;


// https://algocademy.com/app/#problem/longest-subarray-without-reapeating
public class LongestSubarrayWithoutReapeating {

    public int longestSubarrayWithoutReapeating(int[] nums) {
        int size = nums.length;

        Set<Integer> set = new HashSet<>();
        int max = Integer.MIN_VALUE;
        int first = 0;
        int second = 1;
        set.add(nums[first]);
        while (first < size && second < size) {
            if (!set.contains(nums[second])){
                set.add(nums[second++]);
                max = Math.max(max, second - first);
            }
            else {
                set.remove(nums[first++]);
            }
        }
        return max;
    }
}
