package org.ivzh.backtracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


// https://leetcode.com/problems/combination-sum-iii/
public class CombinationSumThird {

    public static void main(String[] args) {
        new CombinationSumThird().combinationSum3(9, 45);
    }

    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new LinkedList<>();
        List<Integer> currentState = new ArrayList<>();
        backtracking(result, currentState, n, k, 1);
        return result;
    }

    private void backtracking(List<List<Integer>> result, List<Integer> currentState, int n, int k, int start) {
        if (n == 0 && currentState.size() == k) {
            result.add(new ArrayList<>(currentState));
            System.out.println(currentState);
            return;
        }

        if (n < 0 || currentState.size() > k) {
            return;
        }

        //

        for (int i = start; i <=9; i++) {
            n = n - i;
            currentState.add (i);
            backtracking(result,currentState, n, k, i + 1);
            currentState.remove(currentState.size() - 1);
            n = n + i;
        }
    }
}
