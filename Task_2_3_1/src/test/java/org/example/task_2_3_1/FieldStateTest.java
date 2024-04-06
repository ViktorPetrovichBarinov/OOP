package org.example.task_2_3_1;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.example.task_2_3_1.enums.FieldState;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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
