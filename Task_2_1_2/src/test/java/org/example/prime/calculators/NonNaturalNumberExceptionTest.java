package org.example.prime.calculators;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Класс для тестирования корректности работы класса.
 */
public class NonNaturalNumberExceptionTest {

    @Test
    @DisplayName("Конструкторы")
    void test1() {
        NonNaturalNumberException ex;

        ex = new NonNaturalNumberException();
        assertEquals(ex.getMessage(), "Number is not a natural number");

        ex = new NonNaturalNumberException("Test1");
        assertEquals(ex.getMessage(), "Test1");

        ex = new NonNaturalNumberException("Test2");
        assertEquals(ex.getMessage(), "Test2");
    }
}
