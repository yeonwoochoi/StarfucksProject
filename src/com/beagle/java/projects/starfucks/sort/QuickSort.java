package com.beagle.java.projects.starfucks.sort;

public class QuickSort {
    public static int[] quickSort(int[] inputArr){
        int count = 0;
        quick(inputArr, 0, 0, inputArr.length-1, count);
        return inputArr;
    }


    public static void quick(int[] inputArr, int pivotIndex, int start, int end, int count) {
        if (count < inputArr.length) {
            int[] newArr = new int[end - start + 1];
            int pivot = inputArr[pivotIndex];
            int i = start;
            int small = 0;
            int large = newArr.length - 1;
            while (i <= end) {
                if (i == pivotIndex) {
                    i++;
                } else {
                    if (pivot > inputArr[i]) {
                        newArr[small] = inputArr[i];
                        small++;
                    } else {
                        newArr[large] = inputArr[i];
                        large--;
                    }
                    i++;
                }
            }
            int blank = (small + large)/2;
            newArr[blank] = pivot;
            int k = start;
            for (int j = 0; j < newArr.length; j++) {
                inputArr[k] = newArr[j];
                k++;
            }
            int newPivotIndex = blank + start;

            if (newPivotIndex-1 == start) {
                count = count + 2;
            } else if (newPivotIndex == 0) {
                count++;
            } else if (newPivotIndex == start) {
                count++;
            } else {
                quick(inputArr, newPivotIndex - 1, start, newPivotIndex - 1, count);
            }

            if (newPivotIndex+1 == end) {
                count = count + 2;
            } else if (newPivotIndex == inputArr.length-1) {
                count++;
            } else if (newPivotIndex == end) {
                count++;
            } else {
                quick(inputArr, newPivotIndex+1, newPivotIndex+1, end, count);
            }
        }
    }

}
