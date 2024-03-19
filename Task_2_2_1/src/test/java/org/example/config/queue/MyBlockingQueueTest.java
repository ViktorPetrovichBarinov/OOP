package org.example.config.queue;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.example.queue.MyBlockingQueue;
import org.junit.jupiter.api.Test;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Тесты для класса MyBlockingQueue.
 */
public class MyBlockingQueueTest {

    @Test
    void test1() throws InterruptedException {
        MyBlockingQueue<Integer> blockingQueue = new MyBlockingQueue<>(2);
        AtomicInteger integer = new AtomicInteger(-1);
        Thread first = new Thread(() -> {
            synchronized (blockingQueue) {
                try {
                    Integer value = blockingQueue.dequeue();
                    integer.set(value);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

        });
        first.start();
        Thread.sleep(1000);
        synchronized (blockingQueue) {
            blockingQueue.enqueue(5);
            blockingQueue.enqueue(6);
        }
        first.join();
        assertEquals(integer.get(), 5);

        synchronized (blockingQueue) {
            Integer val = blockingQueue.dequeue();
            integer.set(val);
        }
        assertEquals(integer.get(), 6);
    }

    @Test
    void test2() throws InterruptedException {
        MyBlockingQueue<Integer> blockingQueue = new MyBlockingQueue<>(2);
        AtomicInteger integer = new AtomicInteger(-1);
        Thread first = new Thread(() -> {
            synchronized (blockingQueue) {
                try {
                    blockingQueue.enqueue(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

        });

        synchronized (blockingQueue) {
            blockingQueue.enqueue(5);
            blockingQueue.enqueue(6);
        }
        first.start();
        Thread.sleep(1000);
        synchronized (blockingQueue) {
            Integer val = blockingQueue.dequeue();
            integer.set(val);
        }
        first.join();

        synchronized (blockingQueue) {
            Integer val = blockingQueue.dequeue();
            val = blockingQueue.dequeue();
            integer.set(val);
        }

        assertEquals(integer.get(), 10);

    }

    @Test
    void test3() throws InterruptedException {
        MyBlockingQueue<Integer> blockingQueue = new MyBlockingQueue<>(5);
        assertEquals(blockingQueue.getMaxCapacity(), 5);
        assertEquals(blockingQueue.getNumberOfElements(), 0);
        synchronized (blockingQueue) {
            blockingQueue.enqueue(5);
            blockingQueue.enqueue(5);
            blockingQueue.enqueue(5);
        }
        assertEquals(blockingQueue.getNumberOfElements(), 3);
    }
}
