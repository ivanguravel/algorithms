package org.ivzh.strings;

// https://leetcode.com/problems/string-to-integer-atoi/
public class String2Int {

    public int myAtoi(String value) {
        value = value.trim();
        int n = value.length();

        if(n == 0 || (n == 1 && !Character.isDigit(value.charAt(0)))) {
            return 0;
        }

        boolean positiveOrNegative = value.charAt(0) == '+' || value.charAt(0) == '-';
        int positiveOrNegativeFlag = !positiveOrNegative || value.charAt(0) == '+' ? 1: -1;

        int startIndex = positiveOrNegative ? 1 : 0;
        long result = 0;
        boolean isOverflowed = false;

        for(int i = startIndex; i < n && Character.isDigit(value.charAt(i)); i++) {
            // parse int by char octets. that's looks like some sort of finding digits in number
            result = result * 10 + value.charAt(i) - '0';
            // if result more than int positive border - exit from loop
            if(result > Integer.MAX_VALUE) {
                isOverflowed = true;
                break;
            }
        }

        return prepareResult(result, positiveOrNegativeFlag, isOverflowed);
    }

    private static int prepareResult(long result, int positiveOrNegativeFlag, boolean isOverflowed) {
        // check about int borders
        if(isOverflowed) {
            return positiveOrNegativeFlag == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        }

        // convert to int and return
        return (int)result * positiveOrNegativeFlag;
    }
}
