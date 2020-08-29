package org.ivzh.arrays;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


// https://leetcode.com/problems/two-sum/
// https://web.stanford.edu/class/cs9/sample_probs/TwoSum.pdf
public class TwoSum {

    private static int[] twoSum1(int[] arr, int target) {
        int[] result = new int[2];

        Map<Integer, Void> container = new HashMap<>();

        for (int i : arr) {
            int j = target - i;
            if (container.containsKey(j)) {
                result[0] = j;
                result[1] = i;
            } else {
                container.put(i, null);
            }

        }
        return result;
    }

    public int[] twoSum(int[] nums, int target) {
        Arrays.sort(nums);
        int start = 0;
        int end = nums.length -1;
        while(start + 1 <= end) {
            int version = nums[start] + nums[end];
            if (target == version) {
                int[] res = new int[2];
                res[0] = start;
                res[1] = end;
                return res;
            } else if (target < version) {
                end = end - 1;
            } else {
                start = start + 1;
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }
}
