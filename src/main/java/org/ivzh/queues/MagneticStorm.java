package org.ivzh.queues;


import java.io.PrintWriter;
import java.util.*;


// https://acm.timus.ru/problem.aspx?space=1&num=1126
public class MagneticStorm {

    int m;
    List<Integer> values;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        new MagneticStorm().solve(in, out);
    }

    private void solve(Scanner in, PrintWriter out) {
        readData(in);
        Integer index = 0;
        Integer max = Integer.MIN_VALUE;
        for (Integer v : values) {
            ++index;
            max = Math.max(max, v);
            if (index == this.m) {
                out.println(max);
                out.flush();
                max = Integer.MIN_VALUE;
                index = 0;
            }
        }
    }

    private void readData(Scanner in) {
        this.m = in.nextInt();
        this.values = new LinkedList<>();
        while(true) {
            int val = in.nextInt();
            if (val != -1) {
                this.values.add(val);
            } else {
                break;
            }
        }
    }
}
