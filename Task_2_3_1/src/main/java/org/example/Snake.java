package org.example;

import java.util.ArrayList;

import org.example.enums.Directions;

/**
 * Класс змеи.
 */
public class Snake {
    Directions direction;
    Directions lastTickDirection;
    ArrayList<Coordinates> snakeCoordinates;
    long speed;

    /**
     * Some text.
     *
     * @param dir - Some text.
     * @param snakeCoordinates - Some text.
     * @param speed - Some text.
     * @param lastTickDirection - Some text.
     */
    public Snake(Directions dir, ArrayList<Coordinates> snakeCoordinates, int speed, Directions lastTickDirection) {
        this.direction = dir;
        this.snakeCoordinates = snakeCoordinates;
        this.speed = speed;
        this.lastTickDirection = lastTickDirection;
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

    /**
     * Some text.
     */
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

    /**
     * Some text.
     *
     * @param levelWidth - Some text.
     * @param levelHeight - Some text.
     */
    public void move(int levelWidth, int levelHeight) {
        this.shiftBody();
        Coordinates head = this.getHead();
        switch (this.direction) {
            case UP -> {
                if (head.y() - 1 < 0) {
                    this.setHead(new Coordinates(head.x(), levelHeight - 1));
                } else {
                    this.setHead(new Coordinates(head.x(), head.y() - 1));
                }
                this.lastTickDirection = Directions.UP;
            }
            case RIGHT ->  {
                if (head.x() + 1 >= levelWidth) {
                    this.setHead(new Coordinates(0, head.y()));
                } else {
                    this.setHead(new Coordinates(head.x() + 1, head.y()));
                }
                this.lastTickDirection = Directions.RIGHT;
            }
            case DOWN ->  {
                if (head.y() + 1 >= levelHeight) {
                    this.setHead(new Coordinates(head.x(), 0));
                } else {
                    this.setHead(new Coordinates(head.x(), head.y() + 1));
                }
                this.lastTickDirection = Directions.DOWN;
            }
            case LEFT ->  {
                if (head.x() - 1 < 0) {
                    this.setHead(new Coordinates(levelWidth - 1, head.y()));
                } else {
                    this.setHead(new Coordinates(head.x() - 1, head.y()));
                }
                this.lastTickDirection = Directions.LEFT;
            }
            default -> {

            }
        }
    }
}
