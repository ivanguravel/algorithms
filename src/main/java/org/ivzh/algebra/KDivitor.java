package org.ivzh.algebra;

import java.io.PrintWriter;

import java.util.*;

// https://codeforces.com/problemset/problem/762/A
public class KDivitor {

    Long n;
    Long k;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        new KDivitor().solve(in, out);
    }

    private void solve(Scanner in, PrintWriter out) {
        readData(in);

        Set<Long> container = new TreeSet<>();

        calculateDevidors(container);

        if (container.size() < k) {
            out.println(-1);

        } else {
            long count = 1;
            for (Long l : container) {
                if (count == k) {
                    out.println(l);
                    break;
                }
                ++count;
            }
        }
        out.flush();
    }

    private void calculateDevidors(Set<Long> container) {
        for (long i = 1; i*i <= n; i++) {
            if (n % i == 0) {
                container.add(i);
                container.add(n/i);
            }
        }
    }

    private void readData(Scanner in) {
        this.n = new Long(in.next());
        this.k = new Long(in.next());
    }
}
