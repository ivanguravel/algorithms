package org.ivzh.dynamic.programming;

import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FalseMirrors {

    int n;
    List<Integer> balcons = new LinkedList<>();
    Map<String, Integer> cache;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        new FalseMirrors().solve(in, out);

        out.flush();
    }

    private void solve(Scanner in, PrintWriter out) {
        // read data
        readData(in);


        int max = Integer.MIN_VALUE;
        for (int i = 0; i< n; i++) {
            while (i + 3 < n) {
                int threeElemsSum = 0;
               if (threeElemsSum == max) {
                  // cache("hash()", threeElemsSum);
               } else if (threeElemsSum > max) {
                   max = threeElemsSum;
               } else {

               }
            }


            // String forCache = String.format("%i%i%i", balcons[j], balcons[j+1], Integer.toString(balcons[j+2]));
        }
    }

    void readData(Scanner in) {
        n = in.nextInt();
        in.nextLine();
        String[] split = in.nextLine().split(" ");
        balcons.addAll(Stream.of(split).map(Integer::valueOf).collect(Collectors.toList()));
    }
}
