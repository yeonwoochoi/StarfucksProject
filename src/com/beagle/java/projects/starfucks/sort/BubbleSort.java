package com.beagle.java.projects.starfucks.sort;

public class BubbleSort {

    public static int[] bubbleSort(int[] inputLIst) {
        for (int i = inputLIst.length-1; i > 0; i-- ) {
            for (int j = 0; j < i; j++) {
                if (inputLIst[j] > inputLIst[j+1]) {
                    int temp = inputLIst[j];
                    inputLIst[j] = inputLIst[j+1];
                    inputLIst[j+1] = temp;
                }

            }
        }
        return inputLIst;
    }
}
