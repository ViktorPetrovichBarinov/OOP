package org.example.primeCalculators;

public class LongCalculator implements PrimeArrayCalculator {
    @Override
    public boolean calculate(int number) throws NonNaturalNumberException {
        if (number <= 0) {
            throw new NonNaturalNumberException();
        }

        if (number == 1) {
            return false;
        }

        for (int i = 2; i < number; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}
