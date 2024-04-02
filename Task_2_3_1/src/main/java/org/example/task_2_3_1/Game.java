package org.example.task_2_3_1;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Random;

/**
 * Класс ответственен за запуск всей игры.
 */
public class Game extends Application {
    private final VBox root = new VBox();
    private final int edgeSize = 20;
    private final int levelWidth = 20;
    private final int levelHeight = 20;
    private final int canvasWidth = levelWidth * edgeSize;
    private final int canvasHeight = levelHeight * edgeSize;
    private final Canvas canvas = new Canvas(canvasWidth, canvasHeight);
    private final GraphicsContext gc = canvas.getGraphicsContext2D();
    private static Snake snake;
    private static Directions lastTickSnakeDir;
    private Field field;

    private static boolean isGameOver = false;
    private static boolean isGameWin = false;

    @Override
    public void start(Stage stage) throws Exception {
        this.root.getChildren().add(this.canvas);
        initialization();

        new AnimationTimer() {
            long lastTick = 0;
            public void handle(long now) {
                if (lastTick == 0) {
                    lastTick = now;
                    tick(gc);
                    return;
                }
                if (now - lastTick > (200 - snake.speed * 3) * 1000000) {
                    lastTick = now;
                    tick(gc);
                }
            }
        }.start();

        Scene scene = new Scene(root, canvasWidth, canvasHeight);



        scene.addEventFilter(KeyEvent.KEY_PRESSED, key -> {
            if ((isGameOver || isGameWin) && key.getCode() == KeyCode.R) {
                initialization();
            }
            if (key.getCode() == KeyCode.W && lastTickSnakeDir != Directions.DOWN) {
                snake.direction = Directions.UP;
            }
            if (key.getCode() == KeyCode.D && lastTickSnakeDir != Directions.LEFT) {
                snake.direction = Directions.RIGHT;
            }
            if (key.getCode() == KeyCode.S && lastTickSnakeDir != Directions.UP) {
                snake.direction = Directions.DOWN;
            }
            if (key.getCode() == KeyCode.A && lastTickSnakeDir != Directions.RIGHT) {
                snake.direction = Directions.LEFT;
            }

        });

        stage.setScene(scene);
        stage.show();
    }

    public void initialization() {
        field = new Field(this.levelWidth, this.levelHeight, new Random());
        isGameOver = false;
        isGameWin = false;

        gc.setFill(Color.LIGHTGREEN);
        gc.fillRect(0, 0, canvasWidth, canvasHeight);

        snake = new Snake(Directions.RIGHT, new ArrayList<>(), 1);
        lastTickSnakeDir = Directions.RIGHT;
        snake.addTail(new Coordinates(5,5));
        field.setField(5, 5, FieldState.SNAKE);


        field.setLineOfWalls(0, 0, 0, 5);
        field.setLineOfWalls(0, levelHeight - 6, 0, levelHeight - 1);
        field.setLineOfWalls(levelWidth - 1, 0, levelWidth - 1, 5);
        field.setLineOfWalls(levelWidth - 1, levelHeight - 6,  levelWidth - 1, levelHeight - 1);

        field.setLineOfWalls(0, 0, 5, 0);
        field.setLineOfWalls(levelWidth - 1, 0, levelWidth - 6, 0);
        field.setLineOfWalls(0, levelHeight - 1, 5, levelHeight - 1);
        field.setLineOfWalls(levelWidth - 1, levelHeight - 1, levelWidth - 6, levelHeight - 1);

        field.setLineOfWalls(6, 6, 14, 6);
        field.setLineOfWalls(6, 13, 14, 13);

        field.generateFood();
        field.generateFood();
        field.generateFood();
        field.generateFood();
        field.generateFood();

    }

    public void tick(GraphicsContext gc) {
        if (isGameOver) {
            gc.setFill(Color.RED);
            gc.setFont(new Font("", 50));
            gc.fillText("GAME OVER", 70, 200);
            gc.fillText("R - restart.", 100, 250);
            return;
        }
        if (snake.speed > 30) {
            isGameWin = true;
            gc.setFill(Color.ANTIQUEWHITE);
            gc.setFont(new Font("", 50));
            gc.fillText("!YOU WIN!", 90, 200);
            gc.fillText("R - restart.", 100, 250);
            return;
        }



        Coordinates lastSnakeElem = snake.getLastElem();
        if (lastSnakeElem.x() != -1 && lastSnakeElem.y() != -1) {
            field.setField(lastSnakeElem.x(), lastSnakeElem.y(), FieldState.EMPTY);
        }

        snake.shiftBody();
        Coordinates head = snake.getHead();
        switch (snake.direction) {
            case UP -> {
                if (head.y() - 1 < 0) {
                    snake.setHead(new Coordinates(head.x(), levelHeight - 1));
                } else {
                    snake.setHead(new Coordinates(head.x(), head.y() - 1));
                }
                lastTickSnakeDir = Directions.UP;
            }
            case RIGHT ->  {
                if (head.x() + 1 >= levelWidth) {
                    snake.setHead(new Coordinates(0, head.y()));
                } else {
                    snake.setHead(new Coordinates(head.x() + 1, head.y()));
                }
                lastTickSnakeDir = Directions.RIGHT;
            }
            case DOWN ->  {
                if (head.y() + 1 >= levelHeight) {
                    snake.setHead(new Coordinates(head.x(), 0));
                } else {
                    snake.setHead(new Coordinates(head.x(), head.y() + 1));
                }
                lastTickSnakeDir = Directions.DOWN;
            }
            case LEFT ->  {
                if (head.x() - 1 < 0) {
                    snake.setHead(new Coordinates(levelWidth - 1, head.y()));
                } else {
                    snake.setHead(new Coordinates(head.x() - 1, head.y()));
                }
                lastTickSnakeDir = Directions.LEFT;
            }
        }

        head = snake.getHead();
        if (field.getField(head.x(), head.y()) == FieldState.FOOD) {
            snake.addTail(new Coordinates(-1, -1));
            field.removeFood(head.x(), head.y());
            field.generateFood();
            snake.speed++;
        }

        if (field.getField(head.x(), head.y()) == FieldState.WALL
                || field.getField(head.x(), head.y()) == FieldState.SNAKE) {
            isGameOver = true;
        }

        field.setField(head.x(), head.y(), FieldState.SNAKE);



        gc.setFill(Color.LIGHTGREEN);
        gc.fillRect(0, 0, this.canvasWidth , this.canvasHeight);

        Coordinates currentWall;
        for (int i = 0; i < field.getWallList().size(); i++) {
            currentWall = field.getWallList().get(i);
            gc.setFill(Color.GRAY);
            gc.fillRect(currentWall.x() * edgeSize, currentWall.y() * edgeSize, edgeSize, edgeSize);
        }


        gc.setFill(Color.WHITE);
        gc.setFont(new Font("", 14));
        gc.fillText("Score: " + (snake.speed - 1), 5, 15);
        gc.setFont(new Font("", 14));
        gc.fillText("Goal: " + 30, 60, 15);


        for (Coordinates coord : field.getFoodList()) {
            gc.setFill(Color.WHITE);
            gc.fillOval(coord.x() * edgeSize, coord.y() * edgeSize, edgeSize, edgeSize);

            gc.setFill(Color.ORANGE);
            gc.fillOval(coord.x() * edgeSize + 1, coord.y() * edgeSize + 1, edgeSize - 2, edgeSize - 2);

        }





        Coordinates currentBodyPart;
        for (int i = 1; i < snake.snakeCoordinates.size(); i++) {
            currentBodyPart = snake.snakeCoordinates.get(i);
            gc.setFill(Color.TOMATO);
            gc.fillOval(currentBodyPart.x() * edgeSize + 4, currentBodyPart.y() * edgeSize + 4, edgeSize - 8, edgeSize - 8);
        }

        currentBodyPart = snake.snakeCoordinates.get(0);
        gc.setFill(Color.BISQUE);
        gc.fillOval(currentBodyPart.x() * edgeSize + 1, currentBodyPart.y() * edgeSize + 1, edgeSize - 2, edgeSize - 2);
        if(snake.direction == Directions.UP) {
            gc.setFill(Color.WHITE);
            gc.fillOval(currentBodyPart.x() * edgeSize + edgeSize / 2, currentBodyPart.y() * edgeSize + edgeSize / 2 - 6,
                    5, 5);
            gc.fillOval(currentBodyPart.x() * edgeSize + edgeSize / 2 - 6, currentBodyPart.y() * edgeSize + edgeSize / 2 - 6,
                    5, 5);
            gc.setFill(Color.BLACK);
            gc.fillOval(currentBodyPart.x() * edgeSize + edgeSize / 2 + 1, currentBodyPart.y() * edgeSize + edgeSize / 2 - 5,
                    2, 2);
            gc.fillOval(currentBodyPart.x() * edgeSize + edgeSize / 2 - 5, currentBodyPart.y() * edgeSize + edgeSize / 2 - 5,
                    2, 2);
        }

        if(snake.direction == Directions.RIGHT) {
            gc.setFill(Color.WHITE);
            gc.fillOval(currentBodyPart.x() * edgeSize + edgeSize / 2, currentBodyPart.y() * edgeSize + edgeSize / 2 - 6,
                    5, 5);
            gc.fillOval(currentBodyPart.x() * edgeSize + edgeSize / 2, currentBodyPart.y() * edgeSize + edgeSize / 2 + 1,
                    5, 5);
            gc.setFill(Color.BLACK);
            gc.fillOval(currentBodyPart.x() * edgeSize + edgeSize / 2 + 2, currentBodyPart.y() * edgeSize + edgeSize / 2 - 5,
                    2, 2);
            gc.fillOval(currentBodyPart.x() * edgeSize + edgeSize / 2 + 2, currentBodyPart.y() * edgeSize + edgeSize / 2 + 2,
                    2, 2);
        }

        if(snake.direction == Directions.DOWN) {
            gc.setFill(Color.WHITE);
            gc.fillOval(currentBodyPart.x() * edgeSize + edgeSize / 2, currentBodyPart.y() * edgeSize + edgeSize / 2 + 2,
                    5, 5);
            gc.fillOval(currentBodyPart.x() * edgeSize + edgeSize / 2 - 6, currentBodyPart.y() * edgeSize + edgeSize / 2 + 2,
                    5, 5);
            gc.setFill(Color.BLACK);
            gc.fillOval(currentBodyPart.x() * edgeSize + edgeSize / 2 + 1, currentBodyPart.y() * edgeSize + edgeSize / 2 + 4,
                    2, 2);
            gc.fillOval(currentBodyPart.x() * edgeSize + edgeSize / 2 - 5, currentBodyPart.y() * edgeSize + edgeSize / 2 + 4,
                    2, 2);
        }

        if(snake.direction == Directions.LEFT) {
            gc.setFill(Color.WHITE);
            gc.fillOval(currentBodyPart.x() * edgeSize + edgeSize / 2 - 6, currentBodyPart.y() * edgeSize + edgeSize / 2 - 6,
                    5, 5);
            gc.fillOval(currentBodyPart.x() * edgeSize + edgeSize / 2 - 6, currentBodyPart.y() * edgeSize + edgeSize / 2 + 1,
                    5, 5);
            gc.setFill(Color.BLACK);
            gc.fillOval(currentBodyPart.x() * edgeSize + edgeSize / 2 - 5, currentBodyPart.y() * edgeSize + edgeSize / 2 - 5,
                    2, 2);
            gc.fillOval(currentBodyPart.x() * edgeSize + edgeSize / 2 - 5, currentBodyPart.y() * edgeSize + edgeSize / 2 + 2,
                    2, 2);
        }





    }

    public static void main(String[] args) {
        launch();
    }
}
