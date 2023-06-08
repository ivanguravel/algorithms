package org.ivzh.backtracking;

import java.util.*;


// https://leetcode.com/problems/partition-to-k-equal-sum-subsets/
public class PartitionToKEqualSumSubsets {

    // brute force solution. wrong answer for some reason. problem should be revisited O(n^2)
    public boolean canPartitionKSubsets(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        List<List<Integer>> subsets = subsets(nums);

        for (List<Integer> subset : subsets) {
            int sum = subset.stream().reduce(0, Integer::sum);
            map.merge(sum, 1, Integer::sum);
        }


        return map.values().stream().anyMatch(t -> t == k);
    }

    public List<List<Integer>> subsets(int[] a) {
        List<List<Integer>> out = new LinkedList<>();
        List<Integer> current;
        for (int mask = 0; mask < (1 << a.length); mask++) {
            current = new ArrayList<>(a.length);
            for (int i = 0; i < a.length; i++) {
                if (((1 << i) & mask) != 0) {
                    current.add(a[i]);
                }
            }
            out.add(current);
        }
        return out;
    }
}
