package org.ivzh.greddy.algos;


import java.util.*;

// https://leetcode.com/problems/plus-one/
public class PlusOne {
  
  public int[] plusOne(int[] digits) {
        for (int i = digits.length -1; i >= 0; i--) {
            if (digits[i] <= 8) {
                digits[i] = digits[i] + 1;
                return digits;
            } else {
                digits[i] = 0;
            }
        }

        int[] result = new int[digits.length +1];
        result[0] = 1;

        return result;
    }  
  
}
