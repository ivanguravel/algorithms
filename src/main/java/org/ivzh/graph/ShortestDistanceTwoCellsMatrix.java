package org.ivzh.graph;

import java.util.ArrayDeque;
import java.util.Queue;

public class ShortestDistanceTwoCellsMatrix {

    static Queue<Node> queue = new ArrayDeque<>();

    static int[][] matrix = {
            { 1, 2, 0, 0 },
            { 1, 1, 0, 0 },
            { 0, 1, 1, 1 },
            { 0, 1, 1, 1 },
            { 0, 0, 0, 2 }
    };

    static int rows = matrix.length;
    static int columns = matrix[0].length;

    static int[] rowsArray = { -1, 0, 0, 1 };
    static int[] colsArray = { 0, -1, 1, 0 };

    static boolean[][] visited = new boolean[rows+1][columns+1];


    static int minPath(int[][] matrix) {

        Node source = new Node(0,0,0);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (matrix[i][j] == 2) {
                    source.row = i;
                    source.column = j;
                    visited[i][j] = true;
                } else {
                    visited[i][j] = matrix[i][j] == 0;
                }
            }
        }

        queue.add(source);
        visited[source.row][source.column] = true;

        while (!queue.isEmpty()) {

            Node item = queue.poll();

            if (matrix[item.row][item.column] == 2) {
                return item.distance;
            }

            // iterate over matrix

        }

       throw new IllegalArgumentException("can't find data");

    }

    public static void main(String[] args) {
        System.out.println(minPath(matrix));
    }

    static class Node {

        int row;
        int column;
        int distance;

        Node(int x, int y, int d) {
            this.row = x;
            this.column = y;
            this.distance = d;
        }
    }
}
