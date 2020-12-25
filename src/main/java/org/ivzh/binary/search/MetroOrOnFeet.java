package org.ivzh.binary.search;


import java.io.PrintWriter;
import java.util.*;

// https://acm.timus.ru/problem.aspx?space=1&num=1205&locale=ru
public class MetroOrOnFeet {

    int speedOnFeet;
    int speedOnMetro;
    int countOfMetroStations;

    Map<Integer, List<Integer>> graph = new HashMap<>();

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        new MetroOrOnFeet().solve(in, out);
        out.flush();
    }

    private void solve(Scanner in, PrintWriter out) {
        this.speedOnFeet = in.nextInt();
        this.speedOnMetro = in.nextInt();
        this.countOfMetroStations = in.nextInt();

        int count = 0;
        Integer i = -1;
        Integer j = -1;
        while (i !=0 && j !=0) {
            i = in.nextInt();
            j = in.nextInt();

            List<Integer> children = graph.get(i);

            if (children != null) {
                children = new LinkedList<>();
            }

            children.add(j);



          //  if ()
        }
    }


}
