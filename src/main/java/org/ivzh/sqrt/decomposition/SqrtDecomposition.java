package org.ivzh.sqrt.decomposition;

import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;

public class SqrtDecomposition {

    int n;
    int[] a;
    List<Integer>[] nDivisors;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        PrintWriter p = new PrintWriter(System.out);
        new SqrtDecomposition().solve(s, p);
    }

    private void solve(Scanner s, PrintWriter p) {

    }


    class Decomposition {
        int[] blocks;

        public Decomposition(int n) {
            this.blocks = new int[(int) (Math.sqrt(n) + 1)];
        }

        public void updateRange(int[] a, int l, int r, int d) {
            int lBound = (int) Math.sqrt(l);
            int rBound = (int) Math.sqrt(r);

            for (int i = l; (int) Math.sqrt(i) == lBound; i++) {
                a[i] = a[i] + d;
            }

            for (int i = lBound + 1; i < rBound; i++) {
                blocks[i] = blocks[i] + d;
            }

            for (int i = r; (int) Math.sqrt(i) == rBound; i--) {
                a[i] = a[i] + d;
            }
        }

        public long get(int[] a, int position) {
            long result =  a[position] + blocks[(int) Math.sqrt(position)];


            for (Integer i : nDivisors[position]) {
                result = a[i] + blocks[(int) Math.sqrt(i)];
            }

            return result;
        }


    }

    private void calculateDivisors(int n) {
        for (int c = 1; c < n; ++c) {
            for (int j = 1; j <= Math.sqrt(c); ++j) {
                if (c % j == 0) {
                    nDivisors[c].add(j);
                    if ((c / j) != j) {
                        nDivisors[c].add(c / j);
                    }
                }
            }
        }
    }


}
