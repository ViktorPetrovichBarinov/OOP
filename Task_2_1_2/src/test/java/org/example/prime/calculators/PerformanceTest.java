package org.example.prime.calculators;

/**
 * Класс для тестирования производительности классов.
 */
public class PerformanceTest {
    /**
     * Код тестирует производительность двух классов.
     *
     * @param args  - текст.
     * @throws NonNaturalNumberException    - текст.
     */
    public static void main(String[] args) throws NonNaturalNumberException {
        final int[] primes = {
            1000000007,
            1000000009,
            1000000021,
            1000000033,
            1000000087,
            1000000093,
            1000000097,
            1000000103,
            1000000123,
            1000000181, //10
            1000000207,
            1000000223,
            1000000241,
            1000000271,
            1000000289,
            1000000297,
            1000000321,
            1000000349,
            1000000363,
            1000000403, //20
            1000000409,
            1000000411,
            1000000427,
            1000000433,
            1000000439,
            1000000447,
            1000000453,
            1000000459,
            1000000483,
            1000000513, //30
            1000000531,
            1000000579,
            1000000607,
            1000000613,
            1000000637,
            1000000663,
            1000000711,
            1000000753,
            1000000787,
            1000000801, //40
            1000000829,
            1000000861,
            1000000871,
            1000000891,
            1000000901,
            1000000919,
            1000000931,
            1000000933,
            1000000993}; //49
        System.out.println("Prime numbers from 1_000_000_000 to 1_000_001_000");
        PrimeArrayCalculator calculator;
        int numberOfPrimes;
        long start;
        long end;

        calculator = new ShortCalculator();
        numberOfPrimes = 0;
        start = System.currentTimeMillis();
        for (Integer prime : primes) {
            if (calculator.calculate(prime)) {
                numberOfPrimes++;
            }
        }

        end = System.currentTimeMillis();
        System.out.println("Short calculator results in "
                + (end - start) + " ms | " + numberOfPrimes);

        calculator = new LongCalculator();
        numberOfPrimes = 0;
        start = System.currentTimeMillis();
        for (Integer prime : primes) {
            if (calculator.calculate(prime)) {
                numberOfPrimes++;
            }
        }
        end = System.currentTimeMillis();
        System.out.println("Long calculator results in "
                + (end - start) + " ms | " + numberOfPrimes);


        int maxPrimeInt = 2147483629;
        start = System.currentTimeMillis();
        calculator.calculate(maxPrimeInt);
        end = System.currentTimeMillis();
        System.out.println("Long Calculator max prime number performance:" + (end - start));
    }
}
