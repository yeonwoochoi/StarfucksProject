package com.beagle.java.projects.starfucks.utils;

public class TestArray {
    private TestArray() {}

    private static TestArray testArray = new TestArray();
    public static TestArray getInstance() {
        return testArray;
    }

    public int[] randomArray() {
        int size = 1000000;
        int[] inputList = new int[size];
        for(int i = 0; i < size; i++) {
            int randomInt = (int) ((Math.random() * 9999) +1);
            inputList[i] = randomInt;
        }
        return inputList;
    }
}
