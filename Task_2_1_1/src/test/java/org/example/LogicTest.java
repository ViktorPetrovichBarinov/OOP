package org.example;

import static org.example.Logic.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;


/**
 * Some text.
 */
public class LogicTest {

    @ParameterizedTest
    @MethodSource("createIsPrimeTests")
    void checkThatNumberIsPrimeOrNot(long number, boolean isPrime) {
        assertEquals(isPrimeNumber(number), isPrime);
    }

    private static Stream<Arguments> createIsPrimeTests() {
        return Stream.of(
                Arguments.of(1, false),
                Arguments.of(2, true),
                Arguments.of(-1, false),
                Arguments.of(Integer.MIN_VALUE, false),
                Arguments.of(10000019, true),
                Arguments.of(10000079, true)
        );
    }


    @ParameterizedTest
    @MethodSource("createIsPrimeArraySequentialCalculationTests")
    void checkThatArrayIsPrimeOrNotForSequential(long[] numbers, boolean isPrime) {
        assertEquals(sequentialCalculation(numbers), isPrime);
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

    @ParameterizedTest
    @MethodSource("createIsPrimeArrayThreadCalculationTests")
    void checkThatArrayIsPrimeOrNotForThreads(long[] numbers, boolean isPrime) {
        assertEquals(threadCalculation(numbers, 1), isPrime);
    }

    private static Stream<Arguments> createIsPrimeArrayThreadCalculationTests() {
        return Stream.of(
                Arguments.of(new long[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, false),
                Arguments.of(new long[]{2, 3, 5, 7}, true),
                Arguments.of(new long[]{2, 3, 5, 7, -1}, false),
                Arguments.of(new long[]{2, 3, 5, 7, Integer.MIN_VALUE}, false),
                Arguments.of(new long[]{2, 3, 5, 7, 1000000000000000003L}, true)
        );
    }

    @ParameterizedTest
    @MethodSource("createIsPrimeArrayStreamCalculationTests")
    void checkThatArrayIsPrimeOrNotForStream(long[] numbers, boolean isPrime) {
        assertEquals(streamCalculation(numbers, 1), isPrime);
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
