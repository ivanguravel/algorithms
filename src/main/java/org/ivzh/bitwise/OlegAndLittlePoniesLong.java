package org.ivzh.bitwise;

import java.io.PrintWriter;
import java.util.*;


// https://timus.online/problem.aspx?space=1&num=2108
// TODO
public class OlegAndLittlePoniesLong {

    int n;
    int m;

    long[][] data;
    long presentToys;
    boolean[] visited;


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        new OlegAndLittlePoniesLong().solve(in, out);
    }

    void solve(Scanner in, PrintWriter out) {
        // read data
        readData(in);


        while (true) {
            boolean continueLoop = false;
            for (int i =0; i < m; i++) {
                if (!visited[i]) {
                    long buffer = this.presentToys;
                    buffer = buffer & data[i][0];

                    if (buffer == data[i][0]) {
                        visited[i] = true;
                        presentToys = presentToys | data[i][1];
                        continueLoop = true;
                    }
                }
            }

            if (!continueLoop) {
                break;
            }
        }

        String binary = Long.toBinaryString(this.presentToys);


        if (binary.length() < this.n) {
            StringBuilder sb = new StringBuilder();
            int forAdd = this.n - binary.length();
            for (int i =0; i < forAdd; i++) {
                sb.append("0");
            }
            sb.append(binary);
            binary = sb.toString();
        }

        out.println(binary);
        out.flush();
    }


    private void readData(Scanner in) {
        this.n = in.nextInt();
        this.m = in.nextInt();

        this.data = new long[4002][2];
        //Arrays.fill(this.data, new BitSet[2]);

        this.visited = new boolean[4002];

        in.nextLine();
        for (int i = 0 ; i < m; i++) {
            String s = in.nextLine();
            String[] split = s.split(" ");

            data[i][0] = Long.parseLong(split[0], 2);
            data[i][1] = Long.parseLong(split[1], 2);
        }

        this.presentToys = Long.parseLong(in.nextLine(), 2);
    }
}
