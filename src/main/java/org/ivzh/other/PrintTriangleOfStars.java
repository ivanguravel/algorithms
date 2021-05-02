package org.ivzh.other;

// https://algocademy.com/app/#problem/print-triangle-of-stars
public class  PrintTriangleOfStars {
    public void printStars(int n) {
        int i =1;
        printer(i, n);
    }
    
    private void printer(int i, int n) {
        if (i <= n) {
            for (int j = 0; j < i; j++) {
                System.out.print("*");
            }
            System.out.println("");
            printer(++i, n);
        }
    }
}
