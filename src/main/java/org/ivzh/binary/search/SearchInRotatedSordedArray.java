package org.ivzh.binary.search;


// https://leetcode.com/problems/search-in-rotated-sorted-array
public class SearchInRotatedSordedArray {


    public static void main(String[] args) {
        int[] a = {4,5,6,7,0,1,2};
        int t =0;

        System.out.println(search(a, t));


    }

    public static int search(int[] nums, int target) {
        int rotationPoint = searchRotationPoint(nums);

        int result = binarySearch(nums, 0, rotationPoint -1, target);

        if (result == -1) {
            result = binarySearch(nums, rotationPoint, nums.length -1, target);
        }

        return result;
    }

    private static int searchRotationPoint(int[] num) {
        int i =0;
        int j = num.length -1;

        while (i < j) {
            int mid = (i + j) / 2;

            if (mid < j && num[mid] > num[mid+1]) {
                return mid;
            }

            if(i<mid&&num[mid]<num[mid-1]) {
                return mid-1;
            }


            if(num[mid]<=num[i]) {
                j=mid-1;
            }

            if(num[mid]>num[i]) {
                i=mid+1;
            }

        }


        return -1;
    }

    public static int binarySearch(int[] nums, int start, int end, int value) {
        int middle = -1;
        while (start <= end) {
            middle = (start + end) / 2;
            if (nums[middle] == value) {
                return middle;
            } else if (nums[middle] > value) {
                end = middle - 1;
            } else {
                start = middle + 1;
            }
        }
        return -1;
    }


}
