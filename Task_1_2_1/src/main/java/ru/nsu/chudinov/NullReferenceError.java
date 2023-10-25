package ru.nsu.chudinov;

/**
 * Класс для ошибок
 */
public class NullReferenceError extends Exception {

    public NullReferenceError() {
        super("incorrect argument");
    }

    public NullReferenceError(String message) {
        super(message);
    }
}
