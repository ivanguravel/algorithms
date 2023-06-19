package org.ivzh.graph;

import java.util.LinkedList;
import java.util.Queue;

// https://leetcode.com/problems/rotting-oranges/description/
public class RottingOranges {

    public int orangesRotting(int[][] nums) {
        int countFresh = 0;
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int row = nums.length;
        int col = nums[0].length;
        Queue<int[]> q = new LinkedList<>();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (nums[i][j] == 2) {
                    q.offer(new int[]{i, j});
                }
                if (nums[i][j] != 0) {
                    countFresh++;
                }
            }
        }
        if (countFresh == 0) {
            return 0;
        }
        int countMin = 0;
        int cnt = 0;

        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};


        while (!q.isEmpty()) {
            int size = q.size();
            cnt += size;
            for (int i = 0; i < size; i++) {
                int[] point = q.poll();
                for (int j = 0; j < 4; j++) {
                    int x = point[0] + dx[j];
                    int y = point[1] + dy[j];

                    if (x < 0 || y < 0 || x >= row || y >= col || nums[x][y] == 0 || nums[x][y] == 2) {
                        continue;
                    }
                    nums[x][y] = 2;
                    q.offer(new int[]{x, y});
                }
            }
            if (q.size() != 0) {
                countMin++;
            }
        }
        return countFresh == cnt ? countMin : -1;
    }
}
