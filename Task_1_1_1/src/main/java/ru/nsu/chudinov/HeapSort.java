package ru.nsu.chudinov;

import java.util.Arrays;

/**
 * some text
 */
public class HeapSort {
    /**
     * @param arr text
     * @return text
     */
    public static int[] sort(int[] arr) {
        int[] copy = Arrays.copyOf(arr, arr.length);

        maxHeap(copy);
        for (int i = copy.length - 1; i >= 0; i--) {
            int tmp = copy[0];
            copy[0] = copy[i];
            copy[i] = tmp;
            heapify(copy, i, 0);
        }
        return copy;
    }

    /**
     *
     * @param arr text
     */
    private static void maxHeap(int[] arr) {
        for (int i = arr.length / 2 - 1; i > -1; i--) {
            heapify(arr, arr.length, i);
        }
    }

    /**
     *
     * @param arr text
     * @param lengthOfHeap text
     * @param i text
     */
    private static void heapify(int[] arr, int lengthOfHeap, int i) {
        int rootIndex = i;
        int leftSonIndex = i * 2 + 1;
        int rightSonIndex = i * 2 + 2;

        if (leftSonIndex < lengthOfHeap && arr[leftSonIndex] > arr[rootIndex]) {
            rootIndex = leftSonIndex;
        }
        if (rightSonIndex < lengthOfHeap && arr[rightSonIndex] > arr[rootIndex]) {
            rootIndex = rightSonIndex;
        }

        if (rootIndex != i) {
            int cmp = arr[i];
            arr[i] = arr[rootIndex];
            arr[rootIndex] = cmp;
            heapify(arr, lengthOfHeap, rootIndex);
        }
    }
}