package org.example;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class ParallelCalculation extends Calculation {


    ParallelCalculation(int numberOfThreads) {
        if (numberOfThreads <= 0) {
            throw new IllegalArgumentException("Number of threads cant' be negative.");
        }
        this.numberOfThreads = numberOfThreads;
        threadCalculationResult = new AtomicBoolean();
        threadCalculationIndex = new AtomicInteger();
    }

    private int numberOfThreads;

    // Хранит результат,
    // чтобы каждый поток мог убиться, если мы нашли не простое число
    private final AtomicBoolean threadCalculationResult;

    //текущий индекс, который обрабатывают потоки
    private final AtomicInteger threadCalculationIndex;

    public void setNumberOfThreads(int numberOfThreads) {
        this.numberOfThreads = numberOfThreads;
    }

    @Override
    public boolean calculate(long[] numbers) {
        threadCalculationResult.set(true);
        threadCalculationIndex.set(0);
        Thread[] threads = new Thread[numberOfThreads]; //инициализируем

        createCalculationThread(threads, numbers);

        startAndJoin(threads);

        return threadCalculationResult.get();
    }

    private void createCalculationThread(Thread[] threads, long[] numbers) {
        for (int i = 0; i < numberOfThreads; i++) { //создаём потоки
            threads[i] = new Thread(() -> {
                while (threadCalculationIndex.get() < numbers.length
                        && threadCalculationResult.get()) { //пока мы не обработали все индексы
                    //и не нашли хотя бы одно не простое число, то продолжаем обработку массива
                    int index = threadCalculationIndex.getAndIncrement();
                    if (!isPrimeNumber(numbers[index])) {
                        //если число не простое, завершаем работу потока
                        threadCalculationResult.set(false);
                        break;
                    }
                }
            });
        }
    }

    private void startAndJoin(Thread[] threads) {
        for (int i = 0; i < numberOfThreads; i++) { //стартуем все потоки
            threads[i].start();
        }

        for (int i = 0; i < numberOfThreads; i++) { //ожидаем завершения всех потоков
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                System.exit(-1);
            }
        }
    }

}
