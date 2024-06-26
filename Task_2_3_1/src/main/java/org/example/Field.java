package org.example;

import java.util.ArrayList;
import java.util.Random;
import org.example.enums.FieldState;

/**
 * Класс ответственнен за работу с полем.
 */
public class Field {
    private final int width;
    private final int height;
    private final ArrayList<ArrayList<FieldState>> field;
    private final ArrayList<Coordinates> foodList = new ArrayList<>();
    private final ArrayList<Coordinates> wallList = new ArrayList<>();
    private final Random random;

    /**
     * Конструктор поля.
     *
     * @param width - ширина.
     * @param height - высота.
     * @param random - сид.
     */
    public Field(int width, int height, Random random) {
        this.width = width;
        this.height = height;
        this.random = random;
        field = new ArrayList<>();
        for (int i = 0; i < width; i++) { //по x
            ArrayList<FieldState> currentList = new ArrayList<>();
            for (int j = 0; j < height; j++) { //по y
                currentList.add(FieldState.EMPTY);
            }
            field.add(currentList);
        }
    }

    public FieldState getField(int x, int y) {
        return field.get(x).get(y);
    }

    public void setField(int x, int y, FieldState grid) {
        field.get(x).set(y, grid);
    }

    public ArrayList<Coordinates> getFoodList() {
        return foodList;
    }

    /**
     * Some text.
     *
     * @param x - Some text.
     * @param y - Some text.
     */
    public void removeFood(int x, int y) {
        setField(x, y, FieldState.EMPTY);
        for (Coordinates coord : foodList) {
            if (coord.x() == x && coord.y() == y) {
                foodList.remove(coord);
                break;
            }
        }
    }

    /**
     * Some text.
     */
    public void generateFood() {
        if (!isFoodGenerationPossible()) {
            return;
        }
        while (true) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            if (getField(x, y) == FieldState.EMPTY) {
                setField(x, y, FieldState.FOOD);
                foodList.add(new Coordinates(x, y));
                break;
            }
        }
    }

    private boolean isFoodGenerationPossible() {
        return field.stream()
                .flatMap(ArrayList::stream)
                .anyMatch(state -> state == FieldState.EMPTY);
    }

    /**
     * Some text.
     *
     * @param x - Some text.
     * @param y - Some text.
     */
    public void setOneWall(int x, int y) {
        if (x >= width || y >= height || x < 0 || y < 0) {
            throw new RuntimeException("Out of bounds");
        }
        field.get(x).set(y, FieldState.WALL);
        wallList.add(new Coordinates(x, y));
    }

    public ArrayList<Coordinates> getWallList() {
        return wallList;
    }

    /**
     * Some text.
     *
     * @param x1 - Some text.
     * @param y1 - Some text.
     * @param x2 - Some text.
     * @param y2 - Some text.
     */
    public void setLineOfWalls(int x1, int y1, int x2, int y2) {
        if (x1 == x2) {
            for (int i = Math.min(y1, y2); i <= Math.max(y1, y2); i++) {
                setOneWall(x1, i);
            }
        } else if (y1 == y2) {
            for (int i = Math.min(x1, x2); i <= Math.max(x1, x2); i++) {
                setOneWall(i, y1);
            }
        } else {
            throw new RuntimeException("Incorrect line coordinates");
        }
    }
}
