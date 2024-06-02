package org.example.PrimeCalculators;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class ShortCalculatorTest {
    PrimeArrayCalculator calculator = new ShortCalculator();

    @ParameterizedTest
    @MethodSource("nonNaturalData")
    void nonNaturalTest(int actual) throws NonNaturalNumberException {
        assertThrows(NonNaturalNumberException.class, () -> calculator.calculate(actual));
    }

    static Stream<Arguments> nonNaturalData() {
        Random random = new Random();
        return IntStream.generate(() -> Integer.MIN_VALUE + random.nextInt(Integer.MAX_VALUE))
                .limit(10000)
                .mapToObj(Arguments::arguments);
    }

    @ParameterizedTest
    @MethodSource("nonPrimeData")
    void nonPrimeTest(int actual) throws NonNaturalNumberException {
        assertFalse(calculator.calculate(actual));
    }

    static Stream<Arguments> nonPrimeData() {
        Random random = new Random();
        return Stream.of(
                Arguments.arguments(1),
                Arguments.arguments(4),
                Arguments.arguments(6),
                Arguments.arguments(8),
                Arguments.arguments(9),
                Arguments.arguments(10),
                Arguments.arguments(12),
                Arguments.arguments(14),
                Arguments.arguments(15),
                Arguments.arguments(16),
                Arguments.arguments(18),
                Arguments.arguments(20)
        );
    }


    @ParameterizedTest
    @MethodSource("primeData")
    void primeTest(int actual) throws NonNaturalNumberException {
        assertTrue(calculator.calculate(actual));
    }

    static Stream<Arguments> primeData() {
        Random random = new Random();
        return Stream.of(
                Arguments.arguments(2),
                Arguments.arguments(3),
                Arguments.arguments(5),
                Arguments.arguments(7),
                Arguments.arguments(11),
                Arguments.arguments(13),
                Arguments.arguments(17),
                Arguments.arguments(19),
                Arguments.arguments(23),
                Arguments.arguments(29),
                Arguments.arguments(31),
                Arguments.arguments(37)
        );
    }
}
