package org.example.Queu;

/**
 * Интерфейс ответственнен за кастомные реализации BlockingQueue
 * Два метода, положить айтем в конец очереди и достать айтем из начала
 *
 * @param <Type> - тип айтемов в очереди
 */
public interface CustomBlockingQueue<Type> {

    void enqueue(Type item) throws InterruptedException;

    Type dequeue() throws InterruptedException;
}
