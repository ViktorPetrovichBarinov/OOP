package org.example.task_2_3_1;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Random random = new Random();
        for (int i = 0; i < 1000; i ++) {
            System.out.println(random.nextInt(10));
        }
    }
}
