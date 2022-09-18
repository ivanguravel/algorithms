package org.ivzh.arrays;


// https://leetcode.com/problems/rotate-image/
public class RotateImage {

    public void rotate(int[][] matrix) {
        for (int i =0; i< matrix.length; i++) {
            for (int j = i+1; j< matrix.length; j++) {
                int t = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = t;
            }
        }

        for (int i =0; i < matrix.length; i++) {
            int l = 0;
            int r = matrix.length -1;

            while (l < r) {
                int t = matrix[i][l];
                matrix[i][l] = matrix[i][r];
                matrix[i][r] = t;

                l++;
                r--;
            }
        }
    }
}
