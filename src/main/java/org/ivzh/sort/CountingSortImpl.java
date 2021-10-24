package org.ivzh.sort;


public class CountingSortImpl {

    public static void main(String[] args) {
        int[] a = {2, 0, 7, 5,};
        countingSort(a);
    }

    public static void countingSort(int[] a){
        
        int[] counter = new int[10];
        
        int[] result = new int[a.length];
        
        for (int i : a) {
            counter[i] += 1;
        }
        
        for (int i = 1; i < counter.length; i++) {
            counter[i] = counter[i] + counter[i-1];
        }
        
        for (int i = a.length -1; i >= 0; i--) {
            counter[a[i]] = counter[a[i]] - 1;
            result[counter[a[i]]] = a[i];
        }
        
        System.out.println(java.util.Arrays.toString(result));
     }

}
