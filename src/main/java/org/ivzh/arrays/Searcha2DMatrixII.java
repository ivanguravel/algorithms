package org.ivzh.arrays;

import java.util.Arrays;

// https://leetcode.com/problems/search-a-2d-matrix-ii/
public class Searcha2DMatrixII {
  
  
  // 0(n*logN)
   public boolean searchMatrix(int[][] matrix, int target) {
        for (int i = 0; i < matrix.length; i++) {
            int position = Arrays.binarySearch(matrix[i], target);
            if (position >= 0) {
                return true;
            }
        }
        return false;
    }
  
  
    public boolean searchMatrix2(int[][] matrix, int target) {
        if( matrix.length == 0)  {
            return false;
        } else if (matrix.length == 1 && matrix[0].length == 0) {
           return false; 
        } else {        
            int i = 0;
            int j = matrix[0].length -1;

            while (i < matrix.length && j >= 0) {
                if (matrix[i][j] == target) {
                    return true;
                } else if (matrix[i][j] < target) {
                    i++;
                } else {
                    j--;
                }
            }
               
        }
        return false;       
    }
}
