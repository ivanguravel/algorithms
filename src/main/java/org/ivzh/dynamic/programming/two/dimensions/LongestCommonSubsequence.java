package org.ivzh.dynamic.programming.two.dimensions;

import java.util.Arrays;

// https://leetcode.com/problems/longest-common-subsequence/
public class LongestCommonSubsequence {

    public static void main(String[] args) {
        System.out.println(longestCommonSubsequenceRecursive("aab", "azb"));
        System.out.println(longestCommonSubsequenceRecursive("abcde", "ace"));
        System.out.println(longestCommonSubsequenceRecursive("adc", "adc"));

        System.out.println(longestCommonSubsequence("aab", "azb"));
        System.out.println(longestCommonSubsequence("abcde", "ace"));
        System.out.println(longestCommonSubsequence("adc", "adc"));
    }

    public static int longestCommonSubsequence(String text1, String text2) {
        int n = text1.length();
        int m = text2.length();

        int[][] dp = new int[n+1][m+1];

        for (int i =0; i <= n; i++) {
            for (int j =0; j <= m; j++) {
                if (i ==0 || j == 0) {
                    dp[i][j] = 0;
                } else if (text1.charAt(i-1) == text2.charAt(j-1)) {
                    dp[i][j] = 1 + dp[i-1][j-1];
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }

        return dp[n][m];
    }


    public static int longestCommonSubsequenceRecursive(String text1, String text2) {
        int size1 = text1.length()- 1;
        int size2 = text2.length()- 1;

        if (text1.length() == 0 || text2.length() == 0) {
            return 0;
        }

        if (text1.charAt(size1) == text2.charAt(size2)) {
            return longestCommonSubsequenceRecursive(text1.substring(0, size1), text2.substring(0, size2))
                    + 1;
        } else {
            return Math.max(longestCommonSubsequenceRecursive(text1.substring(0, size1), text2),
                    longestCommonSubsequenceRecursive(text1, text2.substring(0, size2)));
        }

    }

    // recursion with cache
    public int longestCommonSubsequence3(String text1, String text2) {
        Integer [][] dp = new Integer[text1.length()][text2.length()];
        for (Integer[] arr: dp) {
            Arrays.fill(arr, -100);
        }
        return findLCS(text1, text2, text1.length() -1, text2.length() -1, dp);
    }

    int findLCS(String text1, String text2, int i, int j, Integer [][] dp) {
        if (i < 0 || j < 0) {
            return 0;
        }

        if (dp[i][j] != -100) {
            return dp[i][j];
        }

        if (text1.charAt(i) == text2.charAt(j)) {
            return dp[i][j] = findLCS(text1, text2, i -1, j -1, dp) + 1;
        }

        dp[i][j] = Math.max(findLCS(text1, text2, i -1, j, dp), findLCS(text1, text2, i, j -1, dp));
        return dp[i][j];
    }
}
