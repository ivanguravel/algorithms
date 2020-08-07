package org.ivzh.arrays;

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
}
