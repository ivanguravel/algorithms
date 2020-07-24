package org.ivzh.arrays;

import java.util.Arrays;
import java.util.List;
import java.util.stream.StreamSupport;

public class BalancingElements {

    public static void main(String[] args) {

        int[] arr = {5, 5, 2, 5, 8};


        System.out.println(countBalancingElements(Arrays.asList(2, 1, 6 ,4)));
    }


    public static int countBalancingElements(List<Integer> arr) {
        int result = 0;

        int[] oddAndEven = calculateOddAndEvenElementsSum(arr);

        int evenSum = oddAndEven[0];
        int oddSum = oddAndEven[1];

        int previousOdd = 0;
        int previousEven = 0;

        // 0(n)
        for(int i=0;i<arr.size();i++){
            int evens = evenSum - previousEven;
            int odds = oddSum - previousOdd;
            if (i % 2 == 0) {
                evens -= arr.get(i);
            } else {
                odds -= arr.get(i);
            }
            if(previousOdd + evens == previousEven + odds)
                ++result;
            if(i%2==0) {
                previousEven += arr.get(i);
            }
            else {
                previousOdd += arr.get(i);
            }
        }
        return result;
    }

    // O(n)
    private static int[] calculateOddAndEvenElementsSum(List<Integer> arr) {
        int[] result = new int[2];

        for(int i = 0; i< arr.size(); i++){
            if(i%2==0) {
                result[0] += arr.get(i);
            } else {
                result[1] += arr.get(i);
            }
        }

        return result;
    }

}
