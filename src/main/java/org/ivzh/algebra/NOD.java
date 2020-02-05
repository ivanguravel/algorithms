package org.ivzh.algebra;

public class NOD {

    public static void main(String[] args) {
        System.out.println(nod(1234567890, 2));
        System.out.println(recursiveNod(1234567890, 2));
    }

    // Evclid algorithm
    public static int nod(int a, int b) {
        while (a != b) {
            if (a > b) {
                a = a - b;
            } else {
                b = b - a;
            }
        }
        return a;
    }

    public static int recursiveNod(int a, int b) {
        return b == 0 ? a : recursiveNod(b, a % b);
    }
}
