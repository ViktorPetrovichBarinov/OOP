package org.example.task_2_3_1;

import java.util.ArrayList;

public class Snake {
    Directions direction;
    ArrayList<Coordinates> snakeCoordinates;
    long speed;
    public Snake(Directions dir, ArrayList<Coordinates> snakeCoordinates, int speed) {
        this.direction = dir;
        this.snakeCoordinates = snakeCoordinates;
        this.speed = speed;
    }

    public void addTail(Coordinates tailCoords) {
        snakeCoordinates.add(tailCoords);
    }

    public Coordinates getHead() {
        return snakeCoordinates.get(0);
    }

    public void setHead(Coordinates head) {
        snakeCoordinates.set(0, head);
    }
}
