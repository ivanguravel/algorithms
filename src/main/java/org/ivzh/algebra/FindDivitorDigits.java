package org.ivzh.algebra;

// https://www.hackerrank.com/challenges/find-digits/
public class FindDivitorDigits {

    // Complete the findDigits function below.
    static int findDigits(int n) {
        int copy = n;
        int count = 0;
        int reminder;
        while (copy != 0) {
            reminder = copy % 10;
            copy = copy / 10;
            if (reminder != 0 && n % reminder == 0) {
                count++;
            }
        }
        return count;
    }
}
