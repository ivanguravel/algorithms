package org.ivzh.graph;


import java.io.PrintWriter;
import java.util.*;

// https://acm.timus.ru/problem.aspx?space=1&num=1205&locale=ru
public class MetroOrOnFeet {

    double speedOnFeet;
    double speedOnMetro;
    int countOfMetroStations;
    Point[] coordinates;
    int[][] connections;
    double[][] graph;
    Point a;
    Point b;


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        new MetroOrOnFeet().solve(in, out);
        out.flush();
    }

    private void solve(Scanner in, PrintWriter out) {
        readData(in);
        prepareData();

    }

    private void readData(Scanner in) {
        this.speedOnFeet = in.nextDouble();
        this.speedOnMetro = in.nextDouble();
        this.countOfMetroStations = in.nextInt();
        coordinates = new Point[203];
        connections = new int[203][203];
        graph = new double[203][203];

        int count = 0;
        while (count < countOfMetroStations) {
            coordinates[count] = new Point(in.nextInt(), in.nextInt());
        }

        Integer i = -1;
        Integer j = -1;

        while ((i != 0) && (j != 0)) {
            i = in.nextInt();
            j = in.nextInt();

            connections[i][j] = 1;
            connections[j][i] = 1;
        }

        a = new Point(in.nextInt(), in.nextInt());
        b = new Point(in.nextInt(), in.nextInt());
    }

    private void prepareData() {
        for (int i = 0; i < countOfMetroStations + 1; i++) {
            for (int j = 0; j < countOfMetroStations + 1; j++) {
                double speed = connections[i][j] == 1 ? speedOnMetro : speedOnFeet;
                graph[i][j] = calculatePoints(i, j, speed);
            }
        }
    }

    private double calculatePoints(int i, int j, double speed) {

        int coordinate = (coordinates[i].x - coordinates[j].x) * (coordinates[i].x - coordinates[j].x) +
                (coordinates[i].y - coordinates[j].y) * (coordinates[i].y - coordinates[j].y);

        double sqrt = Math.sqrt(coordinate);

        return sqrt / speed;
    }

    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }


}
