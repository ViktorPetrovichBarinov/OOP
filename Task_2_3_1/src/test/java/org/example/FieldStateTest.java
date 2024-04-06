package org.example;


import org.example.enums.FieldState;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Тесты для состояния поля.
 */
public class FieldStateTest {
    @Test
    public void testEnumValues() {
        Assertions.assertEquals("EMPTY", FieldState.EMPTY.name());
        assertEquals("SNAKE", FieldState.SNAKE.name());
        assertEquals("WALL", FieldState.WALL.name());
        assertEquals("FOOD", FieldState.FOOD.name());
    }

    @Test
    public void testEnumOrdinal() {
        assertEquals(0, FieldState.EMPTY.ordinal());
        assertEquals(1, FieldState.SNAKE.ordinal());
        assertEquals(2, FieldState.WALL.ordinal());
        assertEquals(3, FieldState.FOOD.ordinal());
    }
}
