package org.example;

import java.util.ArrayList;
import java.util.Random;
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
import org.example.enums.Directions;
import org.example.enums.FieldState;

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
    private Field field;

    private final static int WINNING_SCORE = 30;

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

        Scene mainScene = new Scene(root, canvasWidth, canvasHeight);

        mainScene.addEventFilter(KeyEvent.KEY_PRESSED, key -> {
            if ((isGameOver || isGameWin) && key.getCode() == KeyCode.R) {
                initialization();
            }
            if (key.getCode() == KeyCode.W && snake.lastTickDirection != Directions.DOWN) {
                snake.direction = Directions.UP;
            }
            if (key.getCode() == KeyCode.D && snake.lastTickDirection != Directions.LEFT) {
                snake.direction = Directions.RIGHT;
            }
            if (key.getCode() == KeyCode.S && snake.lastTickDirection != Directions.UP) {
                snake.direction = Directions.DOWN;
            }
            if (key.getCode() == KeyCode.A && snake.lastTickDirection != Directions.RIGHT) {
                snake.direction = Directions.LEFT;
            }
        });

        stage.setScene(mainScene);
        stage.show();
    }

    public void initialization() {
        field = new Field(this.levelWidth, this.levelHeight, new Random());
        isGameOver = false;
        isGameWin = false;

        gc.setFill(Color.LIGHTGREEN);
        gc.fillRect(0, 0, canvasWidth, canvasHeight);

        snake = new Snake(Directions.RIGHT, new ArrayList<>(), 1, Directions.RIGHT);
        snake.addTail(new Coordinates(5,5));
        field.setField(5, 5, FieldState.SNAKE);
        createLevel();
    }

    public void createLevel() {
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
        if (snake.speed > WINNING_SCORE) {
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

        snake.move(levelWidth, levelHeight);

        Coordinates head = snake.getHead();
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
        drawField();
    }

    private void drawField() {
        gc.setFill(Color.LIGHTGREEN);
        gc.fillRect(0, 0, this.canvasWidth , this.canvasHeight);

        //Рисуем стены.
        Coordinates currentWall;
        for (int i = 0; i < field.getWallList().size(); i++) {
            currentWall = field.getWallList().get(i);
            gc.setFill(Color.GRAY);
            gc.fillRect(currentWall.x() * edgeSize, currentWall.y() * edgeSize, edgeSize, edgeSize);
        }

        //Рисуем скор и условие победы.
        gc.setFill(Color.WHITE);
        gc.setFont(new Font("", 14));
        gc.fillText("Score: " + (snake.speed - 1), 5, 15);
        gc.setFont(new Font("", 14));
        gc.fillText("Goal: " + WINNING_SCORE, 60, 15);

        //Рисуем еду.
        for (Coordinates coord : field.getFoodList()) {
            gc.setFill(Color.WHITE);
            gc.fillOval(coord.x() * edgeSize, coord.y() * edgeSize, edgeSize, edgeSize);

            gc.setFill(Color.ORANGE);
            gc.fillOval(coord.x() * edgeSize + 1, coord.y() * edgeSize + 1, edgeSize - 2, edgeSize - 2);

        }

        //Рисуем тело змейки.
        Coordinates currentBodyPart;
        for (int i = 1; i < snake.snakeCoordinates.size(); i++) {
            currentBodyPart = snake.snakeCoordinates.get(i);
            gc.setFill(Color.TOMATO);
            gc.fillOval(currentBodyPart.x() * edgeSize + 4, currentBodyPart.y() * edgeSize + 4, edgeSize - 8, edgeSize - 8);
        }

        //Рисуем голову змеи,
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
