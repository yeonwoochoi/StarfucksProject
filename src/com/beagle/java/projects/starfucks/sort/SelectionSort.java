package com.beagle.java.projects.starfucks.sort;


public class SelectionSort {

    public static int[] selectionSort (int[] inputArr) {
        int inputSize = inputArr.length;
        for (int i = inputSize-1; i > -1; i--) {
            for (int j = 0; j < i; j++) {
                if (inputArr[i] < inputArr[j]) {
                    int updatedMax = inputArr[j];
                    inputArr[j] = inputArr[i];
                    inputArr[i] = updatedMax;
                }
            }
        }
        return inputArr;
    }
}
