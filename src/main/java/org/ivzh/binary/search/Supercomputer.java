package org.ivzh.binary.search;

import java.io.PrintWriter;
import java.util.Scanner;

public class Supercomputer {

    int n;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        new Supercomputer().solve(in, out);
    }

    private void solve(Scanner in, PrintWriter out) {
        readData(in);

        int i = 1;
        int j = Integer.MAX_VALUE-3;

        int ans = -1;
        while ((i + 1) < j) {
            int middle  = (i + j) / 2;
            int forCheck = (middle*(middle + 1)) / 2;

            if (forCheck == this.n) {
                ans = middle;
                break;
            } else if (forCheck > this.n) {
                if (j - i <= 1000) {
                    --j;
                } else {
                    j = j / 2;
                }
            } else {
                if (j - i <= 1000) {
                    ++i;
                } else {
                    i = i * 2;
                }
            }
        }

        out.print(ans);
        out.flush();
    }

    private void readData(Scanner in) {
        this.n = in.nextInt();
    }
}
