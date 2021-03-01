package org.ivzh.two.pointers;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

//  https://codeforces.com/contest/165/problem/C
public class AnotherStringProblem {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int K = in.nextInt();
        String s = in.next();

        if (s == null || s.length() == 0) {
            System.out.println(0);
            return;
        }

        int zeroCount = 0;
        for (char ch : s.toCharArray()) {
            if (ch == '0') {
                ++zeroCount;

            }
        }
        System.out.println(zeroCount);


        Window w1 = new Window();
        Window w2 = new Window();
        int j=0;
        int z = 0;
        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            while (w1.different() < K && j < s.length()) {
                w1.add(Character.getNumericValue(s.charAt(j++)));
            }

            while (w2.different() <= K && z < s.length()) {
                w2.add(Character.getNumericValue(s.charAt(z++)));

            }
            w1.remove(Character.getNumericValue(s.charAt(i)));
            w2.remove(Character.getNumericValue(s.charAt(i)));
            result = result + (z - j);

        }
        if (result == 0) {
            System.out.println(0);
        } else {
            System.out.println(result + 2);
        }

    }


    static class Window {
        Map<Integer, Integer> count;

        Window() {
            this.count = new HashMap<>();
        }

        void add(int x) {
            count.put(x, count.getOrDefault(x, 0) + 1);
        }

        void remove(int x) {
            if (count.containsKey(x)) {
                count.put(x, count.get(x) - 1);
            }
        }

        int different() {
            return count.getOrDefault(1, 0);
        }
    }
}
