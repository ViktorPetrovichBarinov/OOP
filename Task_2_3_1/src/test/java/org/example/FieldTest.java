package org.example;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Random;
import org.example.enums.FieldState;
import org.junit.jupiter.api.Test;

/**
 * Тесты для класса поля.
 */
public class FieldTest {

    @Test
    public void testGetFieldAndSetField() {
        Random random = new Random();
        Field field = new Field(5, 5, random);
        assertEquals(FieldState.EMPTY, field.getField(0, 0));
        field.setField(0, 0, FieldState.SNAKE);
        assertEquals(FieldState.SNAKE, field.getField(0, 0));
    }

    @Test
    public void testGetFoodList() {
        Random random = new Random();
        Field field = new Field(5, 5, random);
        assertTrue(field.getFoodList().isEmpty());
        field.getFoodList().add(new Coordinates(2, 2));
        assertEquals(1, field.getFoodList().size());
    }

    @Test
    public void testRemoveFood() {
        Random random = new Random();
        Field field = new Field(5, 5, random);
        field.setField(2, 2, FieldState.FOOD);
        field.removeFood(2, 2);
        assertEquals(FieldState.EMPTY, field.getField(2, 2));
        assertTrue(field.getFoodList().isEmpty());
    }

    @Test
    public void testGenerateFood() {
        Random random = new Random();
        Field field = new Field(5, 5, random);
        field.generateFood();
        assertFalse(field.getFoodList().isEmpty());
        for (int i = 0; i < 5; i++) {
             for (int j = 0; j < 5; j++) {
                 field.removeFood(i, j);
             }
        }
        assertTrue(field.getFoodList().isEmpty());
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                assertEquals(FieldState.EMPTY, field.getField(i, j));
            }
        }
    }

    @Test
    public void testSetOneWall() {
        Field field = new Field(5, 5, new Random());
        field.setOneWall(2, 2);
        assertEquals(FieldState.WALL, field.getField(2, 2));
        List<Coordinates> wallList = field.getWallList();
        assertFalse(wallList.isEmpty());
        assertEquals(1, wallList.size());
        Coordinates wallCoords = wallList.get(0);
        assertEquals(2, wallCoords.x());
        assertEquals(2, wallCoords.y());
    }

    @Test
    public void testSetLineOfWallsVertical() {
        Field field = new Field(5, 5, new Random());
        field.setLineOfWalls(2, 1, 2, 3);
        assertEquals(FieldState.WALL, field.getField(2, 1));
        assertEquals(FieldState.WALL, field.getField(2, 2));
        assertEquals(FieldState.WALL, field.getField(2, 3));
        List<Coordinates> wallList = field.getWallList();
        assertFalse(wallList.isEmpty());
        assertEquals(3, wallList.size());
    }

    @Test
    public void testSetLineOfWallsHorizontal() {
        Field field = new Field(5, 5, new Random());
        field.setLineOfWalls(1, 2, 3, 2);
        assertEquals(FieldState.WALL, field.getField(1, 2));
        assertEquals(FieldState.WALL, field.getField(2, 2));
        assertEquals(FieldState.WALL, field.getField(3, 2));
        List<Coordinates> wallList = field.getWallList();
        assertFalse(wallList.isEmpty());
        assertEquals(3, wallList.size());
    }

    @Test
    public void testSetOneWallOutOfBounds() {
        Field field = new Field(5, 5, new Random());

        try {
            field.setOneWall(6, 6);
        } catch (RuntimeException e) {
            assertEquals(e.getMessage(), "Out of bounds");
        }
    }

    @Test
    public void testSetLineWallIncorrectCoordinates() {
        Field field = new Field(5, 5, new Random());

        try {
            field.setLineOfWalls(0, 0, 1, 2);
        } catch (RuntimeException e) {
            assertEquals(e.getMessage(), "Incorrect line coordinates");
        }
    }
}
