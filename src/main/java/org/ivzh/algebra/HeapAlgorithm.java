package org.ivzh.algebra;


import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// https://leetcode.com/problems/permutations/
public class HeapAlgorithm {

    public static void main(String[] args) {

        int[] n = {1, 2, 3};

        HeapAlgorithm heapAlgorithm = new HeapAlgorithm();
        List<List<Integer>> results = heapAlgorithm.permute(n);
        for (List<Integer> l : results) {
            System.out.println(l);
        }
    }

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> results = new ArrayList<List<Integer>>();
        generatePermutationsHeap(results, nums, nums.length);
        return results;
    }

    // O(n!)
    private void generatePermutationsHeap(List<List<Integer>> results, int[] nums, int k) {
        if (k == 1) {
            results.add(Arrays.stream(nums).boxed().collect(Collectors.toList()));
        } else {
            for (int i = 0; i < k; i++) {
                generatePermutationsHeap(results, nums, k-1);
                if (k % 2 == 0) {
                    swap(nums, i, k-1);
                } else {
                    swap(nums, 0, k-1);
                }
            }
        }

    }

    private static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
