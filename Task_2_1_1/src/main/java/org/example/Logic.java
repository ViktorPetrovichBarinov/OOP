package org.example;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class Logic {

    static boolean sequentialCalculation(long[] number) {
        for (long arrayIndex : number) {
            if (!isPrimeNumber(arrayIndex)) {
                return false;
            }
        }
        return true;
    }



    private static AtomicBoolean threadCalculationResult = new AtomicBoolean();//хранит результат,
    // чтобы каждый поток мог убится, если мы нашли не простое число
    private static AtomicInteger threadCalculationIndex = new AtomicInteger();//текущий индекс, который обрабатывают потоки
    static boolean threadCalculation(long[] number, int numberOfThreads) {
        threadCalculationResult.set(true);
        threadCalculationIndex.set(0);
        Thread[] threads = new Thread[numberOfThreads];//инициализируем

        for (int i = 0; i < numberOfThreads; i++) {//создаём потоки
            threads[i] = new Thread(() -> {
                while(threadCalculationIndex.get() < number.length
                        && threadCalculationResult.get()) {//пока мы не обработали все индексы
                    //и не нашли хотя бы одно не простое число, то продолжаем обработку массива
                    int index = threadCalculationIndex.getAndIncrement();
                    if (!isPrimeNumber(number[index])) {//если число не простое, завершаем работу потока
                        threadCalculationResult.set(false);
                        break;
                    }
                }
            });
        }

        for (int i = 0; i < numberOfThreads; i++) {//стартуем все потоки
            threads[i].start();
        }

        for (int i = 0; i < numberOfThreads; i++){//ожидаем завершения всех потоков
            try {
                threads[i].join();
            } catch (InterruptedException e) {

            }
        }

        return threadCalculationResult.get();
    }


    public static boolean streamCalculation(long[] numbers, int numberOfThreads) {

        //изменяем на нужное
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", String.valueOf(numberOfThreads));

        return Arrays.stream(numbers)
                .parallel()
                .allMatch(Logic::isPrimeNumber);
    }

    /**
     * Решил делать поиск простого числа перебором всех числе до корня, т.к.
     * решето Эратосфена потенциально тяжело распараллелить, а задачи точно не на алгоритмы,
     * а на потыкаться в многопоточность.
     *
     * @param number - Some text.
     * @return - Some text.
     */
    static boolean isPrimeNumber(long number) {
        if (number <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}
