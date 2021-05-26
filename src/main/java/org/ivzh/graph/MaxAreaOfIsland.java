package org.ivzh.graph;

// https://leetcode.com/problems/max-area-of-island/
public class MaxAreaOfIsland {

    int result = Integer.MIN_VALUE;
    Integer buffer = 0;

    // idea is the same as for https://leetcode.com/problems/number-of-islands/
    public int maxAreaOfIsland(int[][] grid) {

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    buffer = 1;
                    dfs(grid, i, j);
                    result = Math.max(result, buffer);
                    buffer = 0;
                }
            }
        }
        return result == Integer.MIN_VALUE ? 0 : result - 1;
    }

    boolean isValidCell(int[][] grid, int row, int col) {
        if (row < 0 || row > grid.length - 1 || col < 0 || col > grid[0].length - 1) {
            return false;
        } else {
            return grid[row][col] == 1;
        }
    }

    void dfs(int[][] grid, int row, int col) {
        if (isValidCell(grid, row, col)) {
            grid[row][col] = 0;
            buffer +=1;
            int[][] sides = {{1,0}, {-1,0}, {0, 1}, {0, -1}};
            for (int[] side : sides) {
                dfs(grid, row + side[0], col + side[1]);
            }
        }
    }

}
