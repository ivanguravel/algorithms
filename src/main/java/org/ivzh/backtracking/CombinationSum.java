package org.ivzh.backtracking;

import java.util.*;


// https://leetcode.com/problems/combination-sum/
public class CombinationSum {

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new LinkedList<>();
        backtrack(target, 0, new LinkedList<>(), result, candidates);
        return result;
    }

    private void backtrack(int target, int start, LinkedList<Integer> combination,
                           List<List<Integer>> result, int[] candidates) {

        if (target == 0) {
            result.add(new ArrayList<>(combination));
            return;
        }

        if (target < 0) {
            return;
        }


        for (int i = start; i < candidates.length; i++) {
            combination.add(candidates[i]);
            backtrack(target - candidates[i], i, combination, result, candidates);
            combination.removeLast();
        }

    }
}
