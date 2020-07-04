package org.ivzh.graph;


// https://leetcode.com/problems/surrounded-regions
public class SurroundedRegions {

    private static final char FOR_REPLACE_WITH_O = 'R';

    int rowSize = 0;
    int columnSize = 0;

    public void solve(char[][] board) {
        if (board == null) {
            return;
        } else if (board.length == 0) {
            return;
        }



        rowSize = board.length;
        columnSize = board[0].length;

        if (rowSize == 0 || columnSize == 0) {
            return;
        }



        // we'll try to skip bordering near the ends of matrix
        for(int i = 0; i < rowSize; i++){
            // go via all rows
            if(board[i][0] == 'O') {
                dfs(board, i, 0);
                // go via all rows , but on the other size
            } else if (board[i][columnSize - 1] == 'O') {
                dfs(board, i, columnSize - 1);
            }
        }

        for(int j = 0; j < columnSize; j++){
            // go via all collumns
            if(board[0][j] == 'O') {
                dfs(board, 0, j);
                // go via all collumns, but on the other size
            } else if(board[rowSize - 1][j] == 'O') {
                dfs(board, rowSize - 1, j);
            }
        }

        for(int i = 0; i < rowSize; i++){
            for(int j = 0; j < columnSize; j++){
                if(board[i][j] == 'O') {
                    board[i][j] = 'X';
                } else if(board[i][j] == FOR_REPLACE_WITH_O) {
                    board[i][j] = 'O';
                }
            }
        }
    }

    void dfs(char[][] board, int i, int j) {
        boolean isBoardEndReached = isSituatedOutOfTheBorder(board, i, j);
        if (!isBoardEndReached) {
            board[i][j] = FOR_REPLACE_WITH_O;

            dfs(board, i - 1, j);
            dfs(board, i, j - 1);
            dfs(board, i, j + 1);
            dfs(board, i + 1, j);
        }
    }

    private boolean isSituatedOutOfTheBorder(char[][] board, int i, int j) {
        return i < 0 || i >= rowSize || j < 0 || j >= columnSize || board[i][j] != 'O';
    }
}
