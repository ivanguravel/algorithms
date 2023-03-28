package org.ivzh.arrays;


// https://leetcode.com/problems/range-sum-query-immutable/
public class RangeSumQueryImmutable {

    int[] data = null;
    int[] data2 = null;

    public RangeSumQueryImmutable(int[] nums) {
        data = nums;
        data2 = new int[nums.length];
        data2[0] = nums[0];

        for (int i = 1; i < nums.length; i++) {
            data2[i] = data2[i-1] + nums[i];
        }
    }

    public int sumRangeSimple(int i, int j) {
        int res = 0;
        for (int k = i; k <= j; k++) {
            res = res + data[k];
        }
        return res;
    }

    public int sumRangePrefixSum(int left, int right) {
        if (left == 0) {
            return data2[right];
        } else {
            return data2[right] - data2[left-1];
        }
    }
}
