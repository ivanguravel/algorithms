package org.ivzh.tree;

import java.io.PrintWriter;
import java.util.*;

// https://timus.online/problem.aspx?space=1&num=2062
public class AmbitiousExperiment {

    int n;
    SqrtDecomposition sqrtDecomposition;
    int[] a;
    List<Integer>[] nDivisors;
    List<Integer> metrics;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        PrintWriter p = new PrintWriter(System.out);
        new AmbitiousExperiment().solve(s, p);
    }

    private void solve(Scanner s, PrintWriter p) {
        this.n = s.nextInt();

        this.sqrtDecomposition = new SqrtDecomposition(n);
        this.a = new int[n+1];
        this.metrics = new LinkedList<>();

        int count = 0;
        while (count < n) {
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
                metrics.add(Integer.parseInt(line[1]));
            } else {
                sqrtDecomposition.updateRange(a, Integer.parseInt(line[1]), Integer.parseInt(line[2]), Integer.parseInt(line[3]));
            }
        }

        for (Integer i : metrics) {
            p.println(a[i-1]);
            p.flush();
        }

    }


    class SqrtDecomposition {
        int[] blocks;



        public SqrtDecomposition(int n) {
            this.blocks = new int[Math.toIntExact(Math.round(Math.sqrt(n) + 1))];
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
            long result =  a[position] + blocks[(int) Math.sqrt(position)];


            for (Integer i : nDivisors[position]) {
                result = a[i] + blocks[(int) Math.sqrt(i)];
            }

            return result;
        }

        public int updateSingle(int[] a, int position) {
            return a[position] + blocks[(int) Math.sqrt(position)];
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
