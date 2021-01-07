package org.ivzh.algebra;

import java.util.*;

// https://leetcode.com/problems/subtract-the-product-and-sum-of-digits-of-an-integer/
public class SubtractTheProductAndSumOfDigitsOfAnInteger {
  
  public int subtractProductAndSum(int n) {
        int product = 1;
        int sum = 0;
        while (n != 0) {
            int temp = n %10;
            product = product * temp;
            sum = sum + temp;
            n = n / 10;
        }
        
        return product - sum;
    }


}
