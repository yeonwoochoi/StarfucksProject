package com.beagle.java.projects.starfucks.sort;

public class QuickSort {

    private static int count;

    public static int[] quickSort(int[] inputArr){
        count = 0;
        quick(inputArr, 0, 0, inputArr.length-1);
        return inputArr;
    }


    public static void quick(int[] inputArr, int pivotIndex, int start, int end) {
        if (count < inputArr.length) {
            int[] newArr = new int[end - start + 1];
            int pivot = inputArr[pivotIndex];
            int i = start;
            int sameCount = 0;
            int small = 0;
            int large = newArr.length - 1;
            while (i <= end) {
                if (i == pivotIndex) {
                    i++;
                } else {
                    if (pivot > inputArr[i]) {
                        newArr[small] = inputArr[i];
                        small++;
                    } else if (pivot == inputArr[i]){
                        sameCount++;
                    } else {
                        newArr[large] = inputArr[i];
                        large--;
                    }
                    i++;
                }
            }
            int blank = large;
            for (int m = blank; m >= blank - sameCount; m--) {
                newArr[m] = pivot;
            }
            int k = start;
            for (int j = 0; j < newArr.length; j++) {
                inputArr[k] = newArr[j];
                k++;
            }
            int newPivotIndex = blank + start;
            int newPivotIndex1 = newPivotIndex - sameCount;
            int newPivotIndex2 = newPivotIndex;

            if (newPivotIndex1 - 1 == start) {
                count = count + 2;
            } else if (newPivotIndex1 == 0) {
                count++;
            } else if (newPivotIndex1 == start) {
                count++;
            } else {
                quick(inputArr, newPivotIndex1-1, start, newPivotIndex1-1);
            }


            if (newPivotIndex2+1 == end) {
                count = count + 2;
            } else if (newPivotIndex2 == inputArr.length-1) {
                count++;
            } else if (newPivotIndex2 == end) {
                count++;
            } else {
                quick(inputArr, newPivotIndex2 + 1, newPivotIndex2 + 1, end);
            }
        }
    }

}
