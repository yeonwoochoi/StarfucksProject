package com.beagle.java.projects.starfucks.sort;

public class MergeSort {
    public static int[] mergeSort(int[] inputArr) {
        int standard = 1;
        int start = 0;
        int middle = 0;
        int end = 0;
        int before = 0;
        MergeSort mergeSort = new MergeSort();
        while (standard <= inputArr.length) {
            standard = standard * 2;
            for (int i = 0; i+standard-1 < inputArr.length; i = i + standard) {
                start = i;
                end = start + standard -1;
                middle = (start + end) / 2;
                mergeSort.merge(inputArr, start, middle, end);
            }

            if (inputArr.length - end == 4 && standard == 4) {
                for (int i = end+1; i < inputArr.length-1; i++) {
                    if (inputArr[i] > inputArr[i+1]) {
                        int large = inputArr[i];
                        inputArr[i] = inputArr[i+1];
                        inputArr[i+1] = large;
                    }
                }
                before  = 3;
            }
            if (inputArr.length - end < 4) {
                before = inputArr.length - end -1;
            }
            if (inputArr.length - end > 4) {
                mergeSort.merge(inputArr, end+1, inputArr.length-1-before,inputArr.length-1);
                before = inputArr.length - end -1;
            }
        }

        /*
        for (int i = end; i > end-21; i--) {
            System.out.println(inputArr[i]);
        }

         */
        mergeSort.merge(inputArr, 0, end, inputArr.length-1);
        return inputArr;
    }

    public static void merge(int[] inputArr, int start, int middle, int end) {
        int i = start;
        int j = middle + 1;
        int k = end;
        int[] newArr = new int[end - start + 1];
        int index = 0;
        while (i <= middle && j <= end) {
            if (inputArr[i] > inputArr[j]) {
                newArr[index] = inputArr[j];
                index++;
                j++;
            } else {
                newArr[index] = inputArr[i];
                index++;
                i++;
            }
        }
        if (i == middle+1 && index < newArr.length) {
            while (j <= end) {
                newArr[index] = inputArr[j];
                index++;
                j++;
            }
        }
        if (j == end + 1 && index < newArr.length) {
            while (i <= middle) {
                newArr[index] = inputArr[i];
                index++;
                i++;
            }
        }
        int m = 0;
        while (m < newArr.length) {
            inputArr[start] = newArr[m];
            m++;
            start++;
        }
    }
}
