package org.example;

import java.util.Arrays;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class SequentialCalculation extends Calculation{

    @Override
    public boolean calculate(long[] numbers) {
        LongStream numberStream = Arrays.stream(numbers);
        return numberStream
                .allMatch(Calculation::isPrimeNumber);
    }
}
