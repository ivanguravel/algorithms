package org.ivzh.graph;

import java.util.*;

// https://leetcode.com/problems/flood-fill/
public class FloodFill {
  
    boolean[][] visited;
    Integer initialColor;
    
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        this.visited = new boolean[image.length][image[0].length];
        this.initialColor = image[sr][sc];
        fill(image, sr, sc, color);
        return image;
    }
    
    private void fill(int[][] image, int i, int j, int color) {
        if (isValid(image, i, j) && !visited[i][j] && image[i][j] == initialColor) {
            visited[i][j] = true;
            image[i][j] = color;
            int[][] directions = {{1,0}, {-1,0}, {0, 1}, {0, -1}};
            
            for (int[] direction : directions) {
                fill(image, i + direction[0], j + direction[1], color);
            }
        }
    }
    
    
    private boolean isValid(int[][] image, int i, int j) {
        return i < image.length && i >= 0 && j < image[0].length && j >= 0;
    }
}
