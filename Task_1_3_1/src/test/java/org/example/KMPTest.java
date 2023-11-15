package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

import static org.example.KMP.KMPSearch;
import static org.example.KMP.computeLPSArray;
import static org.junit.jupiter.api.Assertions.*;

public class KMPTest {

    @ParameterizedTest
    @MethodSource("test1")
    void testArray(String pattern, int[] expected) {
        int[] inputArray = new int[pattern.length()];
        computeLPSArray(pattern, inputArray);

        assertArrayEquals(expected, inputArray);
    }
    static Stream<Arguments> test1() {
        return Stream.of(
                org.junit.jupiter.params.provider.Arguments.arguments(
                        "ABCDABCD", new int[]{0, 0, 0, 0, 1, 2, 3, 4}
                ),
                Arguments.arguments(
                        "AAAA", new int[]{0, 1, 2, 3}
                ),
                Arguments.arguments(
                        "ABCDE", new int[]{0, 0, 0, 0, 0}
                ),
                Arguments.arguments(
                        "AABAACAABAA", new int[]{0, 1, 0, 1, 2, 0, 1, 2, 3, 4, 5}
                ),
                Arguments.arguments(
                        "AAACAAAAAC", new int[]{0, 1, 2, 0, 1, 2, 3, 3, 3, 4}
                ),
                Arguments.arguments(
                        "AAABAAA", new int[]{0, 1, 2, 0, 1, 2, 3}
                )
        );
    }

    @Test
    @DisplayName("Default test")
    void test2() {
        String fileName = "src/test/resources/test1.txt";
        String pattern = new String("бра".getBytes(), StandardCharsets.UTF_8);
        ArrayList<Integer> answer = new ArrayList<>();
        answer.add(1);
        answer.add(8);
        assertEquals(answer, KMPSearch(fileName, pattern));
    }

    @Test
    @DisplayName("Long string")
    void test3() {
        String fileName = "src/test/resources/test2.txt";
        String pattern = new String("aaa".getBytes(), StandardCharsets.UTF_8);
        ArrayList<Integer> answer = new ArrayList<>();
        answer.add(6);
        answer.add(7);
        answer.add(8);
        answer.add(9);
        answer.add(27);
        answer.add(28);
        answer.add(29);
        answer.add(30);


        assertEquals(answer, KMPSearch(fileName, pattern));
    }

    @Test
    @DisplayName("Strange symbol")
    void test4() {
        String fileName = "src/test/resources/test2.txt";
        String pattern = new String("ぁ".getBytes(), StandardCharsets.UTF_8);
        ArrayList<Integer> answer = new ArrayList<>();
        answer.add(42);

        assertEquals(answer, KMPSearch(fileName, pattern));
    }

    @Test
    @DisplayName("more tests")
    void test5() {
        String fileName = "src/test/resources/test3.txt";
        String pattern = new String("ab".getBytes(), StandardCharsets.UTF_8);
        ArrayList<Integer> answer = new ArrayList<>();
        answer.add(102);
        answer.add(104);
        answer.add(106);

        assertEquals(answer, KMPSearch(fileName, pattern));
    }
}
