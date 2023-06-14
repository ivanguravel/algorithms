package org.ivzh.dynamic.programming.two.dimensions;


// https://leetcode.com/problems/unique-paths/
public class UniquePaths {

    public int uniquePaths(int m, int n) {
        if (m <= 0 || n <= 0) {
            return 0;
        } else {
            int[][] result = new int[m+1][n+1];
            fillHorizontal(result, m);
            fillVertical(result, n);
            calculateResult(result, m, n);
            return result[m - 1][n - 1];
        }
    }

    void fillHorizontal(int[][] result, int m) {
        for (int i=0; i<= m; i++) {
            result[i][0] = 1;
        }
    }

    void fillVertical(int[][] result, int n) {
        for (int i=0; i<= n; i++) {
            result[0][i] = 1;
        }
    }

    void calculateResult(int[][] result, int m, int n) {
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                result[i][j] = result[i - 1][j] + result[i][j - 1];
            }
        }
    }
}
