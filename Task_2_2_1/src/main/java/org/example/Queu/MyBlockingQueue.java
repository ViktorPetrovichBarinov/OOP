package org.example.Queu;

import javax.swing.text.DefaultEditorKit;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Моя реализация кастомной очереди
 * @param <T>
 */
public class MyBlockingQueue<T> implements CustomBlockingQueue<T> {

    private final int size;
    private Queue<T> queue = new LinkedList<>();
    public MyBlockingQueue (int size) {
        this.size = size;
    }


    /**
     * Ложит элемент в конец очереди, если места закончились, то падает в ожидание,
     * если очередь пустая, то пробуждает потоки, которые хотят достать что-либо
     * @param item
     * @throws InterruptedException
     */
    @Override
    public void enqueue(T item) throws InterruptedException {
        while(queue.size() == size) {
            wait();
        }
        if (queue.isEmpty()) {
            notifyAll();
        }
        queue.offer(item);
    }

    
    @Override
    public T dequeue() throws InterruptedException {
        while(queue.isEmpty()) {
            wait();
        }
        if (queue.size() == size) {
            notifyAll();
        }
        return queue.poll();
    }

    public int getMaxCapacity() {
        return size;
    }

    public int getNumberOfElements() {
        return queue.size();
    }
}
