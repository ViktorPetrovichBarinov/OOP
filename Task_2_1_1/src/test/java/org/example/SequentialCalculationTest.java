package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Tests for SequentialCalculation.
 */
public class SequentialCalculationTest {

    @ParameterizedTest
    @MethodSource("createIsPrimeArraySequentialCalculationTests")
    void checkThatArrayIsPrimeOrNotForSequential(long[] numbers, boolean isPrime) {
        SequentialCalculation sequentialCalculation = new SequentialCalculation();
        assertEquals(sequentialCalculation.calculate(numbers), isPrime);
    }

    private static Stream<Arguments> createIsPrimeArraySequentialCalculationTests() {
        return Stream.of(
                Arguments.of(new long[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, false),
                Arguments.of(new long[]{2, 3, 5, 7}, true),
                Arguments.of(new long[]{2, 3, 5, 7, -1}, false),
                Arguments.of(new long[]{2, 3, 5, 7, Integer.MIN_VALUE}, false),
                Arguments.of(new long[]{2, 3, 5, 7, 1000000000000000003L}, true)
        );
    }
}
