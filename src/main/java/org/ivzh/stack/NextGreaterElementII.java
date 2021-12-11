package org.ivzh.stack;

import java.util.Stack;


// https://leetcode.com/problems/next-greater-element-ii/
public class NextGreaterElementII {

    public int[] nextGreaterElements(int[] nums) {
        Stack<Integer> stack = new Stack<>();
        int[] result = new int[nums.length];
        Integer v;
        for (int i = nums.length -1; i >= 0; i--) {
            v = nums[i];

            while (!stack.isEmpty() && v > stack.peek()) {
                stack.pop();
            }

            if (!stack.isEmpty()) {
                result[i] = stack.pop();
            } else {
                result[i] = -1;
            }

            stack.push(v);
        }

        return result;
    }
}
