package org.ivzh.sort;

import java.util.Arrays;
import java.util.LinkedList;

// https://leetcode.com/problems/single-number-iii/
public class SingleNumberIII {

    public static void main(String[] args) {
        int[] s = {1,2,1,3,2,5};
        int[] ints = new SingleNumberIII().singleNumber(s);
        System.out.println(Arrays.toString(ints));
    }

    public int[] singleNumber(int[] nums) {
        int[] result = new int[2];
        if (nums.length == 2) {
            if (nums[0] != nums[1]) {
                result[0] = nums[0];
                result[1] = nums[1];

            }
        }  else {
            Arrays.sort(nums);
            int index = 0;
            for (int i = 0; i < nums.length - 1; ) {
                if (nums[i] != nums[i+1]) {
                    result[index++] = nums[i];
                    if (index > 1) {
                        break;
                    }
                }
                i = i +2;
            }

            if (result[1] == 0) {
                result[1] = nums[nums.length -1];
            }
        }
        return result;
    }
}
