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
import java.util.List;
import java.util.Random;

public class Main extends Application {
    private static Integer speed = 5;
    private static Integer foodcolor = 0;
    private static Integer width = 20;
    private static Integer height = 20;
    private static int foodX = 0;
    private static int foodY = 0;
    private static int cornerSize = 25;
    private static List<Corner> snake = new ArrayList<>();
    private static Direction currentDirection = Direction.LEFT;
    private static boolean isGameOver = false;
    private static Random rand = new Random();

    @Override
    public void start(Stage stage) {
        try {
            newFood();

            VBox root = new VBox();
            Canvas canvas = new Canvas(width * cornerSize, height * cornerSize);
            GraphicsContext graphContext = canvas.getGraphicsContext2D();
            root.getChildren().add(canvas);
            new AnimationTimer() {
                long lastTick = 0;
                public void handle(long now) {
                    if (lastTick == 0) {
                        lastTick = now;
                        tick(graphContext);
                        return;
                    }
                    if (now - lastTick > 1000000000 / speed) {
                        lastTick = now;
                        tick(graphContext);
                    }
                }
            }.start();

            Scene scene = new Scene(root, width * cornerSize, height * cornerSize);

            scene.addEventFilter(KeyEvent.KEY_PRESSED, key -> {
                if (key.getCode() == KeyCode.W) {
                    currentDirection = Direction.UP;
                }
                if (key.getCode() == KeyCode.D) {
                    currentDirection = Direction.RIGHT;
                }
                if (key.getCode() == KeyCode.S) {
                    currentDirection = Direction.DOWN;
                }
                if (key.getCode() == KeyCode.A) {
                    currentDirection = Direction.LEFT;
                }
            });

            snake.add(new Corner(width/2, height/2));
            snake.add(new Corner(width/2, height/2));
            snake.add(new Corner(width/2, height/2));


            //scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            stage.setScene(scene);
            stage.setTitle("SNAKE GAME");
            stage.show();
        } catch (Exception e) {

        }
    }


    public static void tick(GraphicsContext gc) {
        if (isGameOver) {
            gc.setFill(Color.RED);
            gc.setFont(new Font("", 50));
            gc.fillText("GAME OVER", 100, 250);
            return;
        }

        for (int i = snake.size() - 1; i >= 1; i--) {
            snake.get(i).x = snake.get(i - 1).x;
            snake.get(i).y = snake.get(i - 1).y;
        }

        switch (currentDirection) {
            case UP:
                snake.get(0).y--;
                if (snake.get(0).y < 0) {
                    isGameOver = true;
                }
                break;
            case RIGHT:
                snake.get(0).x++;
                if(snake.get(0).x > width) {
                    isGameOver = true;
                }
                break;
            case DOWN:
                snake.get(0).y++;
                if(snake.get(0).y > height) {
                    isGameOver = true;
                }
                break;
            case LEFT:
                snake.get(0).x--;
                if(snake.get(0).x < 0) {
                    isGameOver = true;
                }
                break;
        }

        if (foodX == snake.get(0).x && foodY == snake.get(0).y) {
            snake.add(new Corner(-1, -1));
            newFood();
        }

        for (int i = 1; i < snake.size(); i++) {
            if (snake.get(0).x == snake.get(i).x && snake.get(0).y == snake.get(i).y) {
                isGameOver = true;
            }
        }

        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, width * cornerSize, height * cornerSize);

        gc.setFill(Color.WHITE);
        gc.setFont(new Font("", 30));
        gc.fillText("Score: " + (speed - 6), 10, 30);


        Color cc = Color.WHITE;

        switch(foodcolor) {
            case 0:
                cc = Color.PURPLE;
                break;
            case 1:
                cc = Color.LIGHTBLUE;
                break;
            case 2:
                cc = Color.YELLOW;
                break;
            case 3:
                cc = Color.PINK;
                break;
            case 4:
                cc = Color.ORANGE;
                break;

        }

        gc.setFill(cc);
        gc.fillOval(foodX * cornerSize, foodY * cornerSize, cornerSize, cornerSize);


        for (Corner c : snake) {
            gc.setFill(Color.LIGHTGREEN);
            gc.fillRect(c.x * cornerSize, c.y * cornerSize, cornerSize - 1, cornerSize - 1);
            gc.setFill(Color.GREEN);
            gc.fillRect(c.x * cornerSize, c.y * cornerSize, cornerSize - 2, cornerSize - 2);
        }


    }
    public static void newFood() {
        start:while(true) {
            foodX = rand.nextInt(width);
            foodY = rand.nextInt(height);

            for (Corner corner : snake) {
                if (corner.x == foodX && corner.y == foodY) {
                    continue start;
                }
            }

            foodcolor = rand.nextInt(5);
            speed++;
            break;
        }
    }

    public static void main (String[] args) {
        launch(args);
    }
}
