package org.ivzh.algebra;

// https://algocademy.com/app/#problem/magical-number/
public class MagicNumber {

    public static void main(String[] args) {
        System.out.println(new MagicNumber().magicNumber1(928435));
    }

    public  int magicNumber1(int n) {
        int sum = sumOfDigit(n);

        if (sum / 10 ==0) {
            return sum;
        } else {
            return magicNumber1(sum);
        }
    }

    public int sumOfDigit(int n) {
        int sum = 0;
        while (n != 0) {
            sum = sum + (n % 10);
            n = n / 10;
        }
        return sum;
    }
}
