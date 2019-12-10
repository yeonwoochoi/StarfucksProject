package com.beagle.java.projects.starfucks.sort;

public class HeapSort {
    public static int[] heapSort(int[] inputArr) {
        heap(inputArr, inputArr.length-1);
        return inputArr;
    }


    private static void heap(int[] inputArr, int index) {
        if (index > 0) {
            int leaf;
            if (index % 2 == 0) {
                if (inputArr[index] < inputArr[index-1]) {
                    changeNode(inputArr, index, index-1);
                    heapify(inputArr, index-1);
                }
                leaf = index;
            } else {
                if (index != inputArr.length-1) {
                    if (inputArr[index] > inputArr[index+1]) {
                        changeNode(inputArr, index, index+1);
                        heapify(inputArr, index);
                    }
                    leaf = index+1;
                } else {
                    leaf = index;
                }
            }
            int head = (leaf-1)/2;
            if (inputArr[head] < inputArr[leaf]) {
                changeNode(inputArr ,head, leaf);
                if (inputArr[leaf] < inputArr[leaf-1]) {
                    changeNode(inputArr, leaf, leaf-1);
                    heapify(inputArr, leaf-1);
                }
                heapify(inputArr, leaf);
            }

            index = index - 2;
            heap(inputArr, index);
        }
    }


    private static void heapify(int[] inputArr, int index) {
        int left = index * 2 + 1;
        int right = index * 2 + 2;
        if (inputArr.length > right) {
            if (inputArr[left] > inputArr[right]) {
                changeNode(inputArr, left, right);
                heapify(inputArr, left);
            }
            if (inputArr[right] > inputArr[index]) {
                changeNode(inputArr, index, right);
                if (inputArr[left] > inputArr[right]) {
                    changeNode(inputArr, left, right);
                    heapify(inputArr, left);
                }
                heapify(inputArr, right);
            }
        } else {
            if (inputArr.length > left) {
                if (inputArr[left] > inputArr[index]) {
                    changeNode(inputArr, index, left);
                }
            } else {
                if (index % 2 == 0) {
                    if (inputArr[index] < inputArr[index-1]) {
                        changeNode(inputArr,index, index-1);
                    }
                }
            }
        }
    }

    private static void changeNode (int[] inputArr, int index1, int index2) {
        int temp = inputArr[index1];
        inputArr[index1] = inputArr[index2];
        inputArr[index2] = temp;
    }}
