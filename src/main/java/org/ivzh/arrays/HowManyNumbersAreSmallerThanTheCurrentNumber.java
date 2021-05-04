package org.ivzh.arrays;

import java.util.*;


// https://leetcode.com/problems/how-many-numbers-are-smaller-than-the-current-number
public class HowManyNumbersAreSmallerThanTheCurrentNumber {

    public static void main(String[] args) {

    }

    public int[] smallerNumbersThanCurrent(int[] nums) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int i : nums) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        int i = 0;
        for (int key : map.keySet()) {
            int tmp = map.get(key);
            map.put(key, i);
            i = i + tmp;
        }
        for (i = 0; i < nums.length; i++) {
            nums[i] = map.get(nums[i]);
        }
        return nums;
    }
}
