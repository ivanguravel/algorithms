package org.ivzh.sort;

import java.util.Arrays;

public class ShellSort {

    public static void main(String[] args) {
        int[] a = {64,25,12,22,11};
        shell(a);
        System.out.println(Arrays.toString(a));
    }


    private static void shell(int[] arr) {
        for (int i = arr.length / 2; i >0; i/=2) {
            for (int j = 0; (j + i) < arr.length; j++) {
                int z = j +i;
                int val = arr[z];

                while ((j - i) >=0 && arr[j-i] > val) {
                    arr[z] = arr[z - i];
                    z = z - i;
                }
                arr[z] = val;
            }
        }
    }
}
