package org.ivzh.backtracking;

import java.util.*;


// https://leetcode.com/problems/subsets/
public class Subsets { 

    int j =0;
    List<List<Integer>> list = new LinkedList<>();

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

    public List<List<Integer>> subsets2(int[] nums) {
        for ( j = 0; j <= nums.length; j++) {
            backtrack(0, new ArrayList<>(), nums);
        }

        return list;
    }

    private void backtrack(int start, List<Integer> l, int[] nums) {
        if (l.size() == j) {
            list.add(new ArrayList<>(l));
        } else {
            for (int i = start; i < nums.length; i++) {
                
                l.add(nums[i]);
                backtrack(i + 1, l, nums);
                l.remove(l.size() - 1);
            }
        }

    }
}
