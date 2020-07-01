package org.ivzh.bitwise;

import java.io.PrintWriter;
import java.util.*;


// https://timus.online/problem.aspx?space=1&num=2108
public class OlegAndLittlePonies4 {

    int n;
    int m;

    BitSet[][] data;
    BitSet presentToys;
    boolean[] visited;


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        new OlegAndLittlePonies3().solve(in, out);
    }

    void solve(Scanner in, PrintWriter out) {
        // read data
        readData(in);


        while(true)
        {
            boolean doStop = false;
            for(int i = 0; i < m; i++)
            {
                if(visited[i]) {
                    continue;
                }

                BitSet key = data[i][0];

                if (key == null) {
                    continue;
                }

                BitSet buffer = BitSet.valueOf(key.toByteArray());
                buffer.and(presentToys);

                if(buffer.equals(data[i][0]))
                {
                    doStop = true;
                    visited[i] = true;
                    presentToys.or(data[i][1]);
                }
            }
            if(!doStop) {
                break;
            }
        }

        String binary = String.format("%8s", Integer.toBinaryString(presentToys.toByteArray()[0] & 0xFF)).replace(' ', '0');

        out.println(binary.substring(binary.length()-n));
        out.flush();
    }


    private void readData(Scanner in) {
        this.n = in.nextInt();
        this.m = in.nextInt();

        this.data = new BitSet[m + 1][2];

        this.visited = new boolean[4010];

        Arrays.fill(this.visited, false);

        in.nextLine();
        for (int i = 0 ; i < m; i++) {
            String s = in.nextLine();
            String[] split = s.split(" ");

            data[i][0] = BitSet.valueOf(split[0].getBytes());
            data[i][1] = BitSet.valueOf(split[1].getBytes());
        }

        this.presentToys = BitSet.valueOf(in.nextLine().getBytes());
    }

}
