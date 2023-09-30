package ru.nsu.chudinov;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.params.ParameterizedTest;
import java.util.Random;
import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * some text.
 */
public class HeapSortTest {


    @ParameterizedTest
    @MethodSource("generateData")
    void testArray(int[] input, int[] expected) {
        assertArrayEquals(expected, HeapSort.sort(input));
    }

    static int[] generateExpectedArray(int length) {
        int[] tmp = new int[length];
        for (int i = 0; i < length; ++i) {
            tmp[i] = length / 2 + i;
        }
        return  tmp;
    }

    static int[] generateInputArray(int length) {
        int[] tmp = generateExpectedArray(length);
        for (int i = 0; i < length * 5; ++i) {
            int fstRandIndex = Math.abs(new Random().nextInt() % length);
            int scdRandIndex = Math.abs(new Random().nextInt() % length);
            int temporaryInt = tmp[fstRandIndex];
            tmp[fstRandIndex] = tmp[scdRandIndex];
            tmp[scdRandIndex] = temporaryInt;
        }

        return tmp;
    }

    static Stream<Arguments> generateData() {

        return Stream.of(
                org.junit.jupiter.params.provider.Arguments.arguments(
                        new int[]{5, 4, 3, 2, 1}, new int[]{1, 2, 3, 4, 5}),
                org.junit.jupiter.params.provider.Arguments.arguments(
                        new int[]{3, 1, 4, 1, 5, 9, 2, 6}, new int[]{1, 1, 2, 3, 4, 5, 6, 9}),
                org.junit.jupiter.params.provider.Arguments.arguments(
                        generateInputArray(5), generateExpectedArray(5)),
                org.junit.jupiter.params.provider.Arguments.arguments(
                        generateInputArray(10), generateExpectedArray(10)),
                org.junit.jupiter.params.provider.Arguments.arguments(
                        generateInputArray(50), generateExpectedArray(50)),
                org.junit.jupiter.params.provider.Arguments.arguments(
                        generateInputArray(100), generateExpectedArray(100)),
                org.junit.jupiter.params.provider.Arguments.arguments(
                        generateInputArray(1000), generateExpectedArray(1000)),
                org.junit.jupiter.params.provider.Arguments.arguments(
                        generateInputArray(10000), generateExpectedArray(10000)),
                org.junit.jupiter.params.provider.Arguments.arguments(
                        generateInputArray(1000000), generateExpectedArray(1000000))
        );
    }
}