package org.example.Queu;

public interface CustomBlockingQueue<Type> {

    void enqueue(Type item) throws InterruptedException;

    Type dequeue() throws InterruptedException;
}
