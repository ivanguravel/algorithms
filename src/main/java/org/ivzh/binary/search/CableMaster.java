package org.ivzh.binary.search;

import java.io.PrintWriter;
import java.util.Scanner;

public class CableMaster {

    int n, k;
    int[] data;
    int result = 0;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        new CableMaster().solve(in, out);
    }

    private void solve(Scanner in, PrintWriter out) {
        readData(in);

        int i = 0;
        int j = 100_000_000;
        int ans = 0;
        while ((i + 1) < j) {
            int middle = (j + i) / 2;

            this.result = 0;
            for (int z : data) {
                this.result += (z / middle);
            }

            if (this.result >= k) {
                i = middle;

                ans = middle;
            } else {
                j = middle;
            }
        }

        out.print(String.format("%.2f", ans / 100.0));
        out.flush();
    }

    private void readData(Scanner in) {
        this.n = in.nextInt();
        this.k = in.nextInt();
        this.data = new int[n+ 1];

        for (int i = 0; i < n; i++) {
            data[i] = (int) (in.nextDouble() * 100);
        }
    }


}
