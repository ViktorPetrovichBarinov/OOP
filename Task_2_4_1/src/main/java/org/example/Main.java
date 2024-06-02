package org.example;

/**
 * Запуск программы.
 */
public class Main {
    /**
     *  Запуск программы.
     *
     * @param args - аргументы пользователя.
     */
    public static void main(String[] args) {
        try {
            Parser.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}