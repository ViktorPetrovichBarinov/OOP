package org.example;

import java.util.Arrays;
import java.util.stream.LongStream;

/**
 * Class for my realisation of calculation.
 */
public class StreamCalculation extends Calculation{

    StreamCalculation(int numberOfThreads) {
        if (numberOfThreads <= 0) {
            throw new IllegalArgumentException("Number of threads can't be negative.");
        }
        this.numberOfThreads = numberOfThreads;
    }

    private int numberOfThreads;

    /**
     * Method sets number of threads for calculation.
     *
     * @param numberOfThreads - Some text.
     */
    public void setNumberOfThreads(int numberOfThreads) {
        if (numberOfThreads <= 0) {
            throw new IllegalArgumentException("Number of threads can't be negative.");
        }
        this.numberOfThreads = numberOfThreads;
    }

    @Override
    public boolean calculate(long[] numbers) {
        //изменяем на нужное
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism",
                String.valueOf(numberOfThreads));

        LongStream numberStream = Arrays.stream(numbers);

        boolean result =  numberStream
                .parallel()
                .allMatch(Calculation::isPrimeNumber);
        //выставляем дефолтное значение потоков
        System.clearProperty("java.util.concurrent.ForkJoinPool.common.parallelism");

        return result;
    }

}
