package org.ivzh.strings;

import java.util.LinkedList;
import java.util.List;


// find pattern inside string
public class KnuthMorrisPrat {

    List<Integer> kmp(String pattern, String txt) {
        int m = pattern.length();
        int n = txt.length();

        int[] longestProperPrefix = new int[m];
        int j = 0;

        List<Integer> result = new LinkedList<>();

        longestProperPrefix(pattern, m, longestProperPrefix);

        int i = 0;
        while ((n - i) >= (m - j)) {
            if (pattern.charAt(j) == txt.charAt(i)) {
                j++;
                i++;
            }
            if (j == m) {
                result.add(i -j);
                j = longestProperPrefix[j - 1];
            } else if (i < n && pattern.charAt(j) != txt.charAt(i)) {
                if (j != 0) {
                    j = longestProperPrefix[j - 1];
                } else {
                    i = i + 1;
                }
            }
        }
        return result;
    }

    void longestProperPrefix(String pattern, int M, int lps[]) {
        int length = 0;
        int i = 1;
        lps[0] = 0;
        while (i < M) {
            if (pattern.charAt(i) == pattern.charAt(length)) {
                length++;
                lps[i] = length;
                i++;
            } else {
                if (length != 0) {
                    length = lps[length - 1];
                } else {
                    lps[i] = length;
                    i++;
                }
            }
        }
    }

    int kmpRotationCheck(String txt, String p) {
        if (txt.length() != p.length()) {
            return -1;
        }

        txt = txt + txt;

        int n = txt.length();
        int m = p.length();

        int lps [] = new int[m];
        longestProperPrefix(p, p.length(), lps);


        int i = 0, j = 0;
        int first = -1;

        while(i<n){

            if(p.charAt(j) == txt.charAt(i)) {
                first = j;
                i++;
                j++;

            }

            if(j==m) {
                return first;
            } else if(i<n && p.charAt(j) != txt.charAt(i)) {
                if(j!=0) {
                    j = lps[j - 1];
                } else {
                    i++;
                }
            }

        }
        return -1;
    }
}
