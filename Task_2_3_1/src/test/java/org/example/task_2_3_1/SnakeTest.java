package org.example.task_2_3_1;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import org.example.task_2_3_1.enums.Directions;
import org.junit.jupiter.api.Test;

/**
 * Тесты для класса змеи.
 */
public class SnakeTest {

    @Test
    public void testAddTail() {
        ArrayList<Coordinates> initialCoords = new ArrayList<>();
        initialCoords.add(new Coordinates(1, 1));
        Snake snake = new Snake(Directions.UP, initialCoords, 1, Directions.UP);

        assertEquals(1, snake.snakeCoordinates.size());

        snake.addTail(new Coordinates(2, 1));

        assertEquals(2, snake.snakeCoordinates.size());
    }

    @Test
    public void testShiftBody() {
        ArrayList<Coordinates> initialCoords = new ArrayList<>();
        initialCoords.add(new Coordinates(1, 1));
        initialCoords.add(new Coordinates(2, 1));
        initialCoords.add(new Coordinates(3, 1));
        Snake snake = new Snake(Directions.UP, initialCoords, 1, Directions.UP);

        snake.shiftBody();

        assertEquals(new Coordinates(1, 1), snake.snakeCoordinates.get(1));
        assertEquals(new Coordinates(2, 1), snake.snakeCoordinates.get(2));
    }

    @Test
    public void testGetHead() {
        ArrayList<Coordinates> initialCoords = new ArrayList<>();
        initialCoords.add(new Coordinates(1, 1));
        initialCoords.add(new Coordinates(2, 1));
        initialCoords.add(new Coordinates(3, 1));
        Snake snake = new Snake(Directions.UP, initialCoords, 1, Directions.UP);

        assertEquals(new Coordinates(1, 1), snake.getHead());
    }

    @Test
    public void testSetHead() {
        ArrayList<Coordinates> initialCoords = new ArrayList<>();
        initialCoords.add(new Coordinates(1, 1));
        initialCoords.add(new Coordinates(2, 1));
        initialCoords.add(new Coordinates(3, 1));
        Snake snake = new Snake(Directions.UP, initialCoords, 1, Directions.UP);

        snake.setHead(new Coordinates(0, 1));

        assertEquals(new Coordinates(0, 1), snake.getHead());
    }

    @Test
    public void testGetLastElem() {
        ArrayList<Coordinates> initialCoords = new ArrayList<>();
        initialCoords.add(new Coordinates(1, 1));
        initialCoords.add(new Coordinates(2, 1));
        initialCoords.add(new Coordinates(3, 1));
        Snake snake = new Snake(Directions.UP, initialCoords, 1, Directions.UP);

        assertEquals(new Coordinates(3, 1), snake.getLastElem());
    }


    @Test
    public void testMoveUp() {
        ArrayList<Coordinates> initialCoords = new ArrayList<>();
        initialCoords.add(new Coordinates(2, 2)); // Голова змеи
        initialCoords.add(new Coordinates(2, 3)); // Тело змеи
        Snake snake = new Snake(Directions.UP, initialCoords, 1, Directions.UP);

        // Передвигаем змею вверх
        snake.move(5, 5);

        // Проверяем, что голова змеи переместилась на одну клетку вверх
        assertEquals(new Coordinates(2, 1), snake.getHead());
    }

    @Test
    public void testMoveRight() {
        ArrayList<Coordinates> initialCoords = new ArrayList<>();
        initialCoords.add(new Coordinates(2, 2)); // Голова змеи
        initialCoords.add(new Coordinates(2, 3)); // Тело змеи
        Snake snake = new Snake(Directions.RIGHT, initialCoords, 1, Directions.RIGHT);

        // Передвигаем змею вправо
        snake.move(5, 5);

        // Проверяем, что голова змеи переместилась на одну клетку вправо
        assertEquals(new Coordinates(3, 2), snake.getHead());
    }

    @Test
    public void testMoveDown() {
        ArrayList<Coordinates> initialCoords = new ArrayList<>();
        initialCoords.add(new Coordinates(2, 3)); // Голова змеи
        initialCoords.add(new Coordinates(2, 2)); // Тело змеи
        Snake snake = new Snake(Directions.DOWN, initialCoords, 1, Directions.DOWN);
        int levelWidth = 5;
        int levelHeight = 5;

        // Передвигаем змею вниз
        snake.move(levelWidth, levelHeight);

        // Проверяем, что голова змеи переместилась на одну клетку вниз
        assertEquals(new Coordinates(2, 4), snake.getHead());
    }

    @Test
    public void testMoveLeft() {
        ArrayList<Coordinates> initialCoords = new ArrayList<>();
        initialCoords.add(new Coordinates(2, 2)); // Голова змеи
        initialCoords.add(new Coordinates(3, 2)); // Тело змеи
        Snake snake = new Snake(Directions.LEFT, initialCoords, 1, Directions.LEFT);
        int levelWidth = 5;
        int levelHeight = 5;

        // Передвигаем змею влево
        snake.move(levelWidth, levelHeight);

        // Проверяем, что голова змеи переместилась на одну клетку влево
        assertEquals(new Coordinates(1, 2), snake.getHead());
    }

    @Test
    public void testMoveThroughBounds() {
        ArrayList<Coordinates> initialCoords = new ArrayList<>();
        initialCoords.add(new Coordinates(0, 0));
        Snake snake = new Snake(Directions.LEFT, initialCoords, 1, Directions.LEFT);
        int levelWidth = 5;
        int levelHeight = 5;

        // Передвигаем змею влево
        snake.move(levelWidth, levelHeight);

        // Проверяем, что голова змеи переместилась в правый верхний угол поля
        assertEquals(new Coordinates(4, 0), snake.getHead());

        snake.direction = Directions.RIGHT;
        // Передвигаем змею вправо
        snake.move(levelWidth, levelHeight);
        // Проверяем, что голова змеи переместилась в стартовую точку
        assertEquals(new Coordinates(0, 0), snake.getHead());

        snake.direction = Directions.UP;
        // Передвигаем змею вверх
        snake.move(levelWidth, levelHeight);
        // Проверяем, что голова змеи переместилась в левый нижний угол поля
        assertEquals(new Coordinates(0, 4), snake.getHead());


        snake.direction = Directions.DOWN;
        // Передвигаем змею вниз через границу поля
        snake.move(levelWidth, levelHeight);
        // Проверяем, что голова змеи переместилась в левый верхний угол поля
        assertEquals(new Coordinates(0, 0), snake.getHead());
    }
}
