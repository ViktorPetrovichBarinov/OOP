package ru.nsu.chudinov;

public class NullReferenceError extends Exception {

    public NullReferenceError() {
        super("incorrect argument");
    }

    public NullReferenceError(String message) {
        super(message);
    }
}
