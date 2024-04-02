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

    public int getSnakeSize() {
        return snakeCoordinates.size();
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

    public void shiftBody() {

        for (int i = snakeCoordinates.size() - 1; i >= 1; i--) {
            int x = snakeCoordinates.get(i - 1).x();
            int y = snakeCoordinates.get(i - 1).y();
            snakeCoordinates.set(i, new Coordinates(x, y));
        }
    }

    public Coordinates getLastElem() {
        return snakeCoordinates.get(snakeCoordinates.size() - 1);
    }
}
