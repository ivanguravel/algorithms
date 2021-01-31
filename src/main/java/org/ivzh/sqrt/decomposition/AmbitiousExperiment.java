package org.ivzh.sqrt.decomposition;

import java.io.PrintWriter;
import java.util.*;

// https://timus.online/problem.aspx?space=1&num=2062
public class AmbitiousExperiment {

    int n;
    SqrtDecomposition sqrtDecomposition;
    long[] a;
    long[] b;
    List<Integer>[] nDivisors;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        PrintWriter p = new PrintWriter(System.out);
        new AmbitiousExperiment().solve(s, p);
    }

    private void solve(Scanner s, PrintWriter p) {
        this.n = s.nextInt();

        this.sqrtDecomposition = new SqrtDecomposition(n);
        this.a = new long[n+1];
        this.b = new long[n+1];

        int count = 1;
        while (count <= n) {
            b[count++] = s.nextInt();
        }

        Arrays.fill(this.a, 0);

        this.nDivisors = new List[n+1];
        for (int i = 0; i < nDivisors.length; i++) {
            nDivisors[i] = new ArrayList<>();
        }
        calculateDivisors(n);


        int linesCount = s.nextInt();
        String[] line;
        s.nextLine();
        while (linesCount-- > 0) {
            line = s.nextLine().split(" ");
            if ("1".equalsIgnoreCase(line[0])) {
                int request = Integer.parseInt(line[1]);
                p.println(sqrtDecomposition.query(a, request));
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
        long[] blocks;

        public SqrtDecomposition(int n) {
            this.size = (int) (Math.sqrt(n) + 1);
            this.blocks = new long[ size];

        }

        public void updateRange(long[] a, int l, int r, long d) {
            int lBound = l / size;
            int rBound = r / size;

            if (lBound == rBound) {
                for (int i = l; i <= r; i++) {
                    a[i] = a[i] + d;
                }
            } else {
                for (int i = l; (i / size) == lBound; i++) {
                    a[i] = a[i] + d;
                }

                for (int i = (lBound + 1); i < rBound; i++) {
                    blocks[i] = blocks[i] + d;
                }

                for (int i = r; (i / size) == rBound; i--) {
                    a[i] = a[i] + d;
                }
            }
        }


        public long query(long[] a, int position) {
            long result =  b[position];


            for (Integer i : nDivisors[position]) {
                result += a[i] + blocks[(i / size)];
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
                        nDivisors[c].add((c / j));
                    }
                }
            }
        }
    }
}
