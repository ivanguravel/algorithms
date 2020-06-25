package org.ivzh.bitwise;


// https://leetcode.com/problems/xor-operation-in-an-array/
public class XOROperationInArray {


    public static void main(String[] args) {
        xorOperation(5, 0);
    }


    public static int xorOperation(int n, int start) {

        if (n == 1) {
            return start;
        }

        int counter = 0;
        int result = 0;
        while (counter < n) {

            result = result ^ (start + counter * 2);
            counter = counter + 1;

        }
        return result;
    }
}
