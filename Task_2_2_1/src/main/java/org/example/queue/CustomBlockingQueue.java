package org.example.queue;

/**
 * Интерфейс ответственнен за кастомные реализации BlockingQueue.
 * Два метода, положить айтем в конец очереди и достать айтем из начала.
 *
 * @param <Type> - тип айтемов в очереди.
 */
public interface CustomBlockingQueue<Type> {

    /**
     * Положить элемент в очередь.
     *
     * @param item - S.
     * @throws InterruptedException - S.
     */
    void enqueue(Type item) throws InterruptedException;

    /**
     * Достать элемент из очереди.
     *
     * @return - S.
     * @throws InterruptedException - S.
     */
    Type dequeue() throws InterruptedException;
}
