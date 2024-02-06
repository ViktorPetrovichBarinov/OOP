package org.example;

import static org.example.Logic.*;

public class Main {
    public static void main(String[] args) {
        long[] array = {

                1000000000000000003L,
                1000000000000000009L,
                1000000000000000031L,
                1000000000000000079L,
                1000000000000000177L,
                1000000000000000183L,
                1000000000000000201L,
                1000000000000000283L,
                1000000000000000381L,
                1000000000000000387L,
                1000000000000000507L,
                1000000000000000523L,
                1000000000000000583L,
                1000000000000000603L,
                1000000000000000619L,
                1000000000000000621L,
                1000000000000000799L,
                1000000000000000841L,
                1000000000000000861L,
                1000000000000000877L,
                1000000000000000913L,
                1000000000000000931L,
                1000000000000000997L};

        long startTime;
        long endTime;

        startTime = System.currentTimeMillis();
        System.out.print(streamCalculation(array, 4));
        endTime = System.currentTimeMillis();
        System.out.println("  ||  " + (endTime - startTime));

        startTime = System.currentTimeMillis();
        System.out.print(threadCalculation(array, 4));
        endTime = System.currentTimeMillis();
        System.out.println("  ||  " + (endTime - startTime));

        startTime = System.currentTimeMillis();
        System.out.print(sequentialCalculation(array));
        endTime = System.currentTimeMillis();
        System.out.println("  ||  " + (endTime - startTime));




//
//
//        startTime = System.currentTimeMillis();
//        for (long i = 1000000000000000000L; i < 1000000000000001000L; i++) {
//            if (isPrimeNumber(i)) {
//                System.out.println(i);
//            }
//        }
//        endTime = System.currentTimeMillis();
//        System.out.println("  ||  " + (endTime - startTime));


    }
}