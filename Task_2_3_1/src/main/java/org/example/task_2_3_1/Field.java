package org.example.task_2_3_1;

import java.util.ArrayList;

import static org.example.task_2_3_1.GridState.EMPTY;

/**
 * Класс ответственнен за работу с полем.
 */
public class Field {
    private int width;
    private int height;
    private ArrayList<ArrayList<Grid>> field;

    public Field(int width, int height) {
        this.width = width;
        this.height = height;
        field = new ArrayList<>();
        for (int i = 0; i < height; i++) { //по y
            ArrayList<Grid> currentList = new ArrayList<>();
            for (int j = 0; j < width; j++) { //по x
                Grid currentGrid = new Grid(j, i, EMPTY);
                currentList.add(currentGrid);
            }
            field.add(currentList);
        }
    }
}
