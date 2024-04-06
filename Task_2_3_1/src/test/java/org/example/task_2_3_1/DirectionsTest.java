package org.example.task_2_3_1;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.example.task_2_3_1.enums.Directions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Тесты для енама направления.
 */
public class DirectionsTest {
    @Test
    public void testEnumValues() {
        Assertions.assertEquals("UP", Directions.UP.name());
        assertEquals("RIGHT", Directions.RIGHT.name());
        assertEquals("DOWN", Directions.DOWN.name());
        assertEquals("LEFT", Directions.LEFT.name());
    }

    @Test
    public void testEnumOrdinal() {
        assertEquals(0, Directions.UP.ordinal());
        assertEquals(1, Directions.RIGHT.ordinal());
        assertEquals(2, Directions.DOWN.ordinal());
        assertEquals(3, Directions.LEFT.ordinal());
    }
}
