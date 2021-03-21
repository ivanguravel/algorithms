package org.ivzh.arrays;


import java.util.LinkedList;
import java.util.List;

// https://leetcode.com/problems/word-search-ii/
public class WordSearchII {

    boolean[][] visited;

    public List<String> findWords(char[][] board, String[] words) {
        List<String> result = new LinkedList<>();
        for (String w : words) {
            if (exist(board, w)) {
                result.add(w);
            }
        }
        return result;
    }

    public boolean exist(char[][] board, String word) {
        visited = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == word.charAt(0) && dfs(board, word, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    boolean dfs(char[][] board, String word, int i, int j, Integer index) {

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
