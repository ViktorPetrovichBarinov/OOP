package org.example;

public abstract class Calculation {

    abstract public boolean calculate(long[] numbers);

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
