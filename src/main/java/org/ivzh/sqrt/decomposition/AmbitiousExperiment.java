package org.ivzh.sqrt.decomposition;

import java.io.PrintWriter;
import java.util.*;

// https://timus.online/problem.aspx?space=1&num=2062
public class AmbitiousExperiment {

    int n;
    SqrtDecomposition sqrtDecomposition;
    int[] a;
    List<Integer>[] nDivisors;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        PrintWriter p = new PrintWriter(System.out);
        new AmbitiousExperiment().solve(s, p);
    }

    private void solve(Scanner s, PrintWriter p) {
        this.n = s.nextInt();

        this.sqrtDecomposition = new SqrtDecomposition(n);
        this.a = new int[n+1];

        int count = 1;
        while (count <= n) {
            a[count++] = s.nextInt();
        }

        this.nDivisors = new ArrayList[n+1];
        Arrays.fill(nDivisors, new ArrayList<>());
        calculateDivisors(n);


        int linesCount = s.nextInt();
        String[] line;
        s.nextLine();
        while (linesCount-- > 0) {
            line = s.nextLine().split(" ");
            if ("1".equalsIgnoreCase(line[0])) {
                int request = Integer.parseInt(line[1]);
                p.println(sqrtDecomposition.getValueByPosition(a, request));
                p.flush();
            } else {
                int left = Integer.parseInt(line[1]);
                int right = Integer.parseInt(line[2]);
                sqrtDecomposition.updateRange(a, left, right, Integer.parseInt(line[3]));
            }
        }
    }


    class SqrtDecomposition {
        int size;
        int[] blocks;

        public SqrtDecomposition(int n) {
            this.size = (int) (Math.sqrt(n) + 1);
            this.blocks = new int[size];

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


        public long getValueByPosition(int[] a, int position) {
            long result = a[position] + blocks[(int) Math.sqrt(position)];


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
