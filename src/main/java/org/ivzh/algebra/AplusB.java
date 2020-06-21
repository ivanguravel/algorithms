package org.ivzh.algebra;


import java.io.PrintWriter;
import java.util.Scanner;

// https://timus.online/problem.aspx?space=1&num=1000
public class AplusB {

    int a;
    int b;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        new AplusB().solve(in, out);


        out.flush();
    }

    void solve(Scanner in, PrintWriter out) {
        readData(in);

        out.println(a + b);
    }

    void readData(Scanner in) {
        this.a = in.nextInt();
        this.b = in.nextInt();
    }
}
