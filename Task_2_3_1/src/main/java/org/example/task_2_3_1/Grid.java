package org.example.task_2_3_1;

import static org.example.task_2_3_1.GridState.EMPTY;

public class Grid {
    private Coordinates coordinates;
    private GridState state;
    private Food food;

    public Grid(int x, int y, GridState state) {
        coordinates = new Coordinates(x, y);
        this.state = state;
    }
}
