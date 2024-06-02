package org.example.prime.calculators;

/**
 * Контракт содержит один метод вычисления простых чисел.
 * Если простое - true.
 * Если не простое - false.
 */
public interface PrimeArrayCalculator {
    public boolean calculate(int number) throws NonNaturalNumberException;
}
