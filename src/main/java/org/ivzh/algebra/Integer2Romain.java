package org.ivzh.algebra;


import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

// https://leetcode.com/problems/integer-to-roman
public class Integer2Romain {

    Map<Integer, String> cache = new TreeMap<>(Comparator.reverseOrder());

    public String intToRoman(int n) {
        setCache();
        StringBuilder result = new StringBuilder();
        while (n > 0) {
            for (Integer i : cache.keySet()) {
                if (n % i != n || n % i == 0) {
                    result.append(cache.get(i));
                    n = n - i;
                    if (n <=0 || n % i != n) {
                        break;
                    }
                }

            }
        }
        return result.toString();
    }

    void setCache() {
        cache.put(1, "I");
        cache.put(4, "IV");
        cache.put(5, "V");
        cache.put(9, "IX");
        cache.put(10, "X");
        cache.put(40, "XL");
        cache.put(50, "L");
        cache.put(90, "XC");
        cache.put(100, "C");
        cache.put(400, "CD");
        cache.put(500, "D");
        cache.put(900, "CM");
        cache.put(1000, "M");
    }
}
