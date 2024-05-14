package org.example;

/**
 * Запуск программы.
 */
public class Main {
    public static void main(String[] args) {
        try {
            Parser.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}