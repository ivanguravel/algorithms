package org.ivzh.two.pointers;

//
public class LongestSubstringWithoutRepeatingCharacters {


    public static void main(String[] args) {
        System.out.println(solve("abcade"));
    }

    private static int solve(String s) {
        char[] chars = s.toCharArray();
        int i = 0;
        int j = 1;
        int max = Integer.MIN_VALUE;
        int howLong = 0;
        while (i < chars.length && j < chars.length) {
            if (chars[i] != chars[j]) {
                howLong = j - i - 1;
                max = Math.max(howLong, max);
                ++j;
            } else {
                ++i;
            }
        }
        return max;
    }
}
