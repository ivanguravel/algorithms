package org.ivzh.graph;

// https://leetcode.com/problems/number-of-islands/
// https://algocademy.com/app/#problem/number-of-islands
public class NumberOfIslands {

    public int numIslands(char[][] grid) {
        int result = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    result++;
                    dfs(grid, i, j);
                }
            }
        }
        return result;
    }


    boolean isValidCell(char[][] grid, int row, int col) {
        if (row > grid.length -1 || row < 0 || col > grid[0].length -1 || col < 0) {
            return false;
        }  else {
            return grid[row][col] == '1';
        }
    }

    void dfs(char[][] grid, int row, int col) {
        if (!isValidCell(grid, row, col)) {
            return;
        }
        grid[row][col] = 0;
        int[][] directions = {{1,0}, {-1,0}, {0, 1}, {0, -1}};
        for (int [] d : directions) {
            dfs(grid, row + d[0], col + d[1]);
        }
    }
}
