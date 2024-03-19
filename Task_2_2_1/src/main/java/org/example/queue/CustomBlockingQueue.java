package org.example.queue;

/**
 * Интерфейс ответственнен за кастомные реализации BlockingQueue.
 * Два метода, положить айтем в конец очереди и достать айтем из начала.
 *
 * @param <T> - тип айтемов в очереди.
 */
public interface CustomBlockingQueue<T> {

    /**
     * Положить элемент в очередь.
     *
     * @param item - S.
     * @throws InterruptedException - S.
     */
    void enqueue(T item) throws InterruptedException;

    /**
     * Достать элемент из очереди.
     *
     * @return - S.
     * @throws InterruptedException - S.
     */
    T dequeue() throws InterruptedException;

    int getMaxCapacity();

    int getNumberOfElements();
}
