package org.example;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;

import static org.example.Calculation.isPrimeNumber;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for Calculation
 */
public class CalculationTest {
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
}
