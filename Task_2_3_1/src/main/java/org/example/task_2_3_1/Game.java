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
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Класс ответственен за запуск всей игры.
 */
public class Game extends Application {
    private final VBox root = new VBox();
    private final int edgeSize = 20;
    private final int levelWidth = 30;
    private final int levelHeight = 30;
    private final int canvasWidth = levelWidth * edgeSize;
    private final int canvasHeight = levelHeight * edgeSize;
    private final Canvas canvas = new Canvas(canvasWidth, canvasHeight);
    private final GraphicsContext gc = canvas.getGraphicsContext2D();
    private static Snake snake;
    private static Directions lastTickSnakeDir;

    private static boolean isGameOver = false;

    @Override
    public void start(Stage stage) throws Exception {
        this.root.getChildren().add(this.canvas);
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, canvasWidth, canvasHeight);

        snake = new Snake(Directions.UP, new ArrayList<>(), 35);
        lastTickSnakeDir = Directions.UP;
        snake.addTail(new Coordinates(5,5));

        new AnimationTimer() {
            long lastTick = 0;
            public void handle(long now) {
                if (lastTick == 0) {
                    lastTick = now;
                    tick(gc);
                    return;
                }
                if (now - lastTick > (1000 - snake.speed * 25) * 1000000) {
                    lastTick = now;
                    tick(gc);
                }
            }
        }.start();

        Scene scene = new Scene(root, canvasWidth, canvasHeight);

        

        scene.addEventFilter(KeyEvent.KEY_PRESSED, key -> {
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

    public void tick(GraphicsContext gc) {
        if (isGameOver) {
            gc.setFill(Color.RED);
            gc.setFont(new Font("", 50));
            gc.fillText("GAME OVER", 100, 250);
            return;
        }

        Coordinates head = snake.getHead();
        switch (snake.direction) {
            case UP -> {
                if (head.y() - 1 < 0) {
                    isGameOver = true;
                } else {
                    snake.setHead(new Coordinates(head.x(), head.y() - 1));
                }
                lastTickSnakeDir = Directions.UP;
            }
            case RIGHT ->  {
                if (head.x() + 1 > levelWidth) {
                    isGameOver = true;
                } else {
                    snake.setHead(new Coordinates(head.x() + 1, head.y()));
                }
                lastTickSnakeDir = Directions.RIGHT;
            }
            case DOWN ->  {
                if (head.y() + 1 > levelHeight) {
                    isGameOver = true;
                } else {
                    snake.setHead(new Coordinates(head.x(), head.y() + 1));
                }
                lastTickSnakeDir = Directions.DOWN;
            }
            case LEFT ->  {
                if (head.x() - 1 < 0) {
                    isGameOver = true;
                } else {
                    snake.setHead(new Coordinates(head.x() - 1, head.y()));
                }
                lastTickSnakeDir = Directions.LEFT;
            }

        }

        int widthEdgeSize = this.canvasWidth / this.levelWidth;
        int heightEdgeSize = this.canvasHeight / this.levelHeight;

        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, this.canvasWidth , this.canvasHeight);

        gc.setFill(Color.WHITE);
        gc.setFont(new Font("", 30));
        gc.fillText("Score: " + (snake.speed - 4), 10, 30);
        for (Coordinates currentField : snake.snakeCoordinates) {
            gc.setFill(Color.BISQUE);
            gc.fillRect(currentField.x() * edgeSize, currentField.y() * edgeSize, edgeSize - 2, edgeSize - 2);
            gc.setFill(Color.TOMATO);
            gc.fillRect(currentField.x() * edgeSize, currentField.y() * edgeSize, edgeSize - 3, edgeSize - 3);
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
