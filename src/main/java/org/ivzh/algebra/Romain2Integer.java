package org.ivzh.algebra;


import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/roman-to-integer/
public class Romain2Integer {


    static Map<Character, Integer> cache = new HashMap<>();

    public static void main(String[] args) {
        System.out.println(romanToInt(                "IX"));
    }

    public static int romanToInt(String s) {
        setCache();

        int result = 0;
        int prev = 0;
        boolean isFirst = true;
        for (char ch : s.toCharArray()) {
            Integer j = cache.get(ch);
            if (isFirst) {
                result = result + j;
                isFirst = false;
            } else if (j > prev) {
                result = result - prev;
                int buffer = j - prev;
                result = result + buffer;
            } else {
                result = result + j;
            }
            prev = j;
        }
        return result;
    }


    static void setCache() {
        cache.put('I', 1);
        cache.put('V', 5);
        cache.put('X', 10);
        cache.put('L', 50);
        cache.put('C', 100);
        cache.put('D', 500);
        cache.put('M', 1000);
    }
}
