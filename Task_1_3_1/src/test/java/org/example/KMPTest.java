package org.example;

import static org.example.KMP.KMPSearch;
import static org.example.KMP.computeLPSArray;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;





/**
 * Some text.
 */
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
        ArrayList<Integer> answer = new ArrayList<>();
        answer.add(1);
        answer.add(8);
        String fileName = "test1.txt";
        String pattern = new String("бра".getBytes(), StandardCharsets.UTF_8);
        assertEquals(answer, KMPSearch(fileName, pattern));
    }

    @Test
    @DisplayName("Long string")
    void test3() {
        ArrayList<Integer> answer = new ArrayList<>();
        answer.add(6);
        answer.add(7);
        answer.add(8);
        answer.add(9);
        answer.add(27);
        answer.add(28);
        answer.add(29);
        answer.add(30);
        String fileName = "test2.txt";
        String pattern = new String("aaa".getBytes(), StandardCharsets.UTF_8);
        assertEquals(answer, KMPSearch(fileName, pattern));
    }

    @Test
    @DisplayName("Strange symbol")
    void test4() {
        ArrayList<Integer> answer = new ArrayList<>();
        answer.add(42);
        String fileName = "test2.txt";
        String pattern = new String("ぁ".getBytes(), StandardCharsets.UTF_8);
        assertEquals(answer, KMPSearch(fileName, pattern));
    }

    @Test
    @DisplayName("more tests")
    void test5() {
        ArrayList<Integer> answer = new ArrayList<>();
        answer.add(101);
        answer.add(103);
        answer.add(105);
        String fileName = "test3.txt";
        String pattern = new String("ab".getBytes(), StandardCharsets.UTF_8);
        assertEquals(answer, KMPSearch(fileName, pattern));
    }
}
