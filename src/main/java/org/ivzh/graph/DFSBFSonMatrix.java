package org.ivzh.graph;

import java.util.LinkedList;
import java.util.Queue;

public class DFSBFSonMatrix {

    private static boolean[][] visited = new boolean[4][4];

    public static void main(String[] args) {
        int[][] matrix = new int[4][4];

        int[] a = {1, 2, 3, 4};
        int[] b = {5, 6, 7, 8};
        int[] c = {9, 10, 11, 12};
        int[] d = {13, 14, 15, 16};

        matrix[0] = a;
        matrix[1] = b;
        matrix[2] = c;
        matrix[3] = d;

        //dfs(matrix, 1, 2);

        printMatrix(matrix);
        bfs(matrix);
    }

    private static void printMatrix(int[][] matrix) {
        try {
            Thread.sleep(1_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("////////////////////////////////////////////// ");
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println(" ");
        }
        System.out.println("////////////////////////////////////////////// ");
    }

    private static void dfs(int[][] matrix, int i, int j) {
        if (i >= 0 && i < matrix.length && j >= 0 && j < matrix[0].length && !visited[i][j]) {

            printMatrix(matrix);
            matrix[i][j] = 0;
            visited[i][j] = true;

            dfs(matrix, i+1, j);
            dfs(matrix, i-1, j);
            dfs(matrix, i, j+1);
            dfs(matrix, i, j-1);

        }
    }

    private static void bfs(int[][] matrix) {
        int[][] dirs = new int[][]{{1, 0}, {0, 1}};
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{0, 0});
        while (!q.isEmpty()) {
            int[] point = q.poll();
            int i = point[0];
            int j = point[1];
            if (!visited[i][j]) {
                visited[i][j] = true;
                for (int[] dir : dirs) {
                    int ii = i + dir[0];
                    int jj = j + dir[1];
                    if (ii >=0 && ii<matrix.length && jj >= 0 && jj < matrix[0].length) {
                        q.add(new int[]{ii, jj});
                        matrix[ii][jj] = 0;
                        printMatrix(matrix);
                    }
                }
            }
        }
    }
}
