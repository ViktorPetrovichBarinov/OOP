package ru.nsu.chudinov;

public class NullReferenceError extends Exception {

    public NullReferenceError() {
        super("Передан некорректный аргумент");
    }

    public NullReferenceError(String message) {
        super(message);
    }
}
