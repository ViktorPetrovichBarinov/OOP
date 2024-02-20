package org.example;

/**
 * Results for table
 */
public class Main {
    /**
     * Some text.
     *
     * @param args - Some text.
     */
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

        SequentialCalculation sequentialCalculation = new SequentialCalculation();
        startTime = System.currentTimeMillis();
        System.out.print("sequential calculus: " + sequentialCalculation.calculate(array));
        endTime = System.currentTimeMillis();
        System.out.println("  ||  " + (endTime - startTime));

        ParallelCalculation parallelCalculation = new ParallelCalculation(1);
        for (int i = 1; i <= 4; i++) {
            parallelCalculation.setNumberOfThreads(i);
            startTime = System.currentTimeMillis();
            System.out.print("thread calculus(" + i + "): " + parallelCalculation.calculate(array));
            endTime = System.currentTimeMillis();
            System.out.println("  ||  " + (endTime - startTime));
        }

        StreamCalculation streamCalculation = new StreamCalculation(1);
        streamCalculation.setNumberOfThreads(4);
        startTime = System.currentTimeMillis();
        System.out.print("stream calculus: " + streamCalculation.calculate(array));
        endTime = System.currentTimeMillis();
        System.out.println("  ||  " + (endTime - startTime));
    }
}