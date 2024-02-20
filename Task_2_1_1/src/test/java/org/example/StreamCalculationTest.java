package org.example;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for StreamCalculation
 */
public class StreamCalculationTest {

    @ParameterizedTest
    @MethodSource("createIsPrimeArrayStreamCalculationTests")
    void checkThatArrayIsPrimeOrNotForStream(long[] numbers, boolean isPrime) {
        StreamCalculation streamCalculation = new StreamCalculation(6);
        assertEquals(streamCalculation.calculate(numbers), isPrime);

        streamCalculation.setNumberOfThreads(4);
        assertEquals(streamCalculation.calculate(numbers), isPrime);
    }

    private static Stream<Arguments> createIsPrimeArrayStreamCalculationTests() {
        return Stream.of(
                Arguments.of(new long[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, false),
                Arguments.of(new long[]{2, 3, 5, 7}, true),
                Arguments.of(new long[]{2, 3, 5, 7, -1}, false),
                Arguments.of(new long[]{2, 3, 5, 7, Integer.MIN_VALUE}, false),
                Arguments.of(new long[]{2, 3, 5, 7, 1000000000000000003L}, true)
        );
    }
}
