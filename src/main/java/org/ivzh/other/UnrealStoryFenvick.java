package org.ivzh.other;

import java.io.PrintWriter;
import java.util.Scanner;


// https://acm.timus.ru/problem.aspx?space=1&num=1491
public class UnrealStoryFenvick {


    int n;
    BinaryIndexedTree bit;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        new UnrealStoryFenvick().solve(in, out);
    }

    void solve(Scanner in, PrintWriter out) {
        readData(in);

        StringBuilder builder = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            builder.append(this.bit.getSum(i) + " ");
        }

        out.println(builder.toString());
        out.flush();
    }


    private void readData(Scanner in) {
        this.n = in.nextInt();
        this.result = new int[n+1];

        in.nextLine();

        this.bit = new BinaryIndexedTree();
        this.bit.constructBITree(n);

        for (int i = 0; i < n+1; i++) {
            String[] line = in.nextLine().split(" ");
            Integer key1 = Integer.parseInt(line[0]);
            Integer key2 = Integer.parseInt(line[1]);
            Integer val = Integer.parseInt(line[2]);

            this.bit.updateBIT(key2, val);
            this.bit.updateBIT(key1 -1, -val);
        }
    }


    static class BinaryIndexedTree {
        // Max tree size
        final static int MAX = 100001;

        int BITree[] = new int[MAX];
        int queryRange = 0;

        int getSum(int index) {
            int sum = 0;

            for (int i = index; i <= queryRange; i += i & (-i)) {
                sum = sum + BITree[i];
            }

            return sum;
        }


        public void updateBIT(int index, int val) {
            for (int i = index; i > 0; i -= i & (-i)) {
                BITree[i] = BITree[i] + val;
            }
        }

        void constructBITree(int n) {
            for (int i = 0; i <= n; i++) {
                BITree[i] = 0;
            }
            this.queryRange = n;
        }
    }
}
