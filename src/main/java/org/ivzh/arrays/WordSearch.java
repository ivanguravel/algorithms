package org.ivzh.arrays;

public class WordSearch {

    boolean[][] visited;

    public boolean exist(char[][] board, String word) {
        visited = new boolean[board.length + 1][board[0].length];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == word.charAt(0) && dfs(board, word, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }


    private boolean dfs(char[][] board, String word, int i, int j, Integer index) {

        if (index == word.length()) {
            return true;
        }

        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || visited[i][j]) {
            return false;
        }

        if (board[i][j] == word.charAt(index)) {
            visited[i][j] = true;

            ++index;

            boolean found = dfs(board, word, i, j + 1, index) ||
                    dfs(board, word, i, j - 1, index) || 
                    dfs(board, word, i + 1, j, index) ||
                    dfs(board, word, i - 1, j, index);

            visited[i][j] = false;
            return found;
        }

        return false;
    }
}
