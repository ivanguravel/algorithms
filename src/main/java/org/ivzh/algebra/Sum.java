package org.ivzh.algebra;


import java.io.PrintWriter;
import java.util.Scanner;

// https://timus.online/problem.aspx?space=1&num=1068
public class Sum {

    int n;
    int result;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        new Sum().solve(in, out);


        out.flush();
    }

    void solve(Scanner in, PrintWriter out) {
        readData(in);


        if ( n > 0) {
            for (int i = 1; i <= n; i++) {
                this.result = this.result + i;
            }
        } else {
            for (int i = 1; i >= n; i--) {
                this.result = this.result + i;
            }
        }

        out.println(this.result);
    }

    void readData(Scanner in) {
        this.n = in.nextInt();
        this.result = 0;
    }
}
