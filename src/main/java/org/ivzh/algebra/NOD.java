package org.ivzh.algebra;

public class NOD {

    public static void main(String[] args) {
        NOD nod = new NOD();
        System.out.println(nod.nod(1234567890, 2));
        System.out.println(nod.recursiveNod(1234567890, 2));
        System.out.println(nod.bitsNod(1234567890, 2));
    }

    // Evclid algorithm
    public int nod(int a, int b) {
        while (a != b) {
            if (a > b) {
                a = a - b;
            } else {
                b = b - a;
            }
        }
        return a;
    }

    public long bitsNod(long u, long v) {
        int shift;

        if (u == 0) return v;
        if (v == 0) return u;

        for (shift = 0; ((u | v) & 1) == 0; ++shift) {
            u >>= 1;
            v >>= 1;
        }

        while ((u & 1) == 0) {
            u >>= 1;
        }

        do {
            while ((v & 1) == 0) {
                v >>= 1;
            }

            if (u > v) {
                long t = v;
                v = u;
                u = t;
            }

            v = v - u;
        } while (v != 0);

        return u << shift;
    }

    public int recursiveNod(int a, int b) {
        return b == 0 ? a : recursiveNod(b, a % b);
    }
}
