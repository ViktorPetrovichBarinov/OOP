package ru.nsu.chudinov;

import java.util.Arrays;
import java.util.Scanner;

/**
 * some text.
 */
public class Main {

    /**
     *
     * @param args text
     */
    public static void main(String[] args) {
        int[] arr = new int[4];
        System.out.print("Enter in integer: ");
        int val = new Scanner(System.in).nextInt();
        arr[0] = val;
        System.out.print("One more thing: ");
        val = new Scanner(System.in).nextInt();
        arr[1] = val;
        System.out.print("One more thing:) ");
        val = new Scanner(System.in).nextInt();
        arr[2] = val;
        System.out.print("And finally the last one: ");
        val = new Scanner(System.in).nextInt();
        arr[3] = val;
        System.out.print("Here is your array:" + Arrays.toString(arr) + "\n");
        System.out.print("And here it is sorted:" + Arrays.toString(HeapSort.sort(arr)) + "\n");
    }
}