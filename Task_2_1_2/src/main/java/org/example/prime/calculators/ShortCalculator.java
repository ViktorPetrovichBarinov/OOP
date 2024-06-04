package org.example.prime.calculators;

/**
 * Реализация вычисления простых чисел до корня.
 */
public class ShortCalculator implements PrimeArrayCalculator {
    @Override
    public boolean calculate(int number) throws NonNaturalNumberException {
        if (number <= 0) {
            throw new NonNaturalNumberException();
        }
        if (number == 1) {
            return false;
        }
        for (int i = 2; i * i <= number; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}
