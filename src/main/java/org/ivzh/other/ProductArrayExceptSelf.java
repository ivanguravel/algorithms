package org.ivzh.other;

// https://leetcode.com/problems/product-of-array-except-self/
public class ProductArrayExceptSelf {

    public int[] productExceptSelf(int[] nums) {
        int product = 1;
        int zeros = 0;
        for (int i : nums) {
            if (i == 0) {
                zeros += 1;
                continue;
            } 
            product *= i;
        }
                
        if (zeros == nums.length) {
              for (int i = 0; i< nums.length; i++) {
                 nums[i] = 0;
            }  
            return nums;
        }
        
        for (int i = 0; i< nums.length; i++) {
            if(zeros > 1 ){
                nums[i] = 0;
            } else if(zeros < 1){
                nums[i] = (product)/nums[i] ; 
            } else{
                nums[i] = (nums[i] != 0) ? 0 :product;
            }
        }
        
        return nums;
    }
}
