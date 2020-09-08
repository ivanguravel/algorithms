package org.ivzh.other;

import java.io.PrintWriter;
import java.util.*;


// https://timus.online/problem.aspx?space=1&num=2108
// TODO
public class OlegAndLittlePoniesBitSet {

    int n;
    int m;

    BitSet[][] wishes;
    BitSet presentToys;
    boolean[] visited;


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        new OlegAndLittlePoniesBitSet().solve(in, out);
    }

    void solve(Scanner in, PrintWriter out) {
        // read data
        readData(in);


        while (true) {
            Boolean continueLoop = false;
            for (int i =0; i < m; i++) {
                if (!visited[i]) {
                    BitSet buffer = (BitSet) presentToys.clone();
                    buffer.and(wishes[i][0]);
                    if (buffer.equals(wishes[i][0])) {
                        visited[i] = true;
                        presentToys.or(wishes[i][1]);
                        continueLoop = true;
                    }
                }
            }

            if (!continueLoop) {
                break;
            }
        }

        String binary = new String(presentToys.toByteArray());
        out.println(binary.substring(binary.length()-n));
        out.flush();
    }


    private void readData(Scanner in) {
        this.n = in.nextInt();
        this.m = in.nextInt();

        this.wishes = new BitSet[4002][2];
        //Arrays.fill(this.data, new BitSet[2]);

        this.visited = new boolean[4002];

        in.nextLine();
        for (int i = 0 ; i < m; i++) {
            String s = in.nextLine();
            String[] split = s.split(" ");

            wishes[i][0] = BitSet.valueOf(split[0].getBytes());
            wishes[i][1] = BitSet.valueOf(split[1].getBytes());
        }

        this.presentToys = BitSet.valueOf(in.nextLine().getBytes());
    }

}
