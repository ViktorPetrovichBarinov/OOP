package org.example.backer_logic;

import static org.example.orders_logic.State.NULL;

import org.example.Interrupt;
import org.example.orders_logic.Order;
import org.example.queue.MyBlockingQueue;

/**
 * Класс ответственен за контроль поваров.
 */
public class BakerController extends Thread {
    private final MyBlockingQueue<Order> waitingForCookingOrder;
    private final MyBlockingQueue<Order> waitingToBeSentOrder;
    private final int[] bakersArray;
    private final Thread[] bakerThreads;

    public Interrupt interrupt = Interrupt.NOT_INTERRUPT;

    /**
     * Конструктор.
     *
     * @param waitingForCookingOrder - очередь заказов, которые ожидают повара.
     * @param waitingToBeSentOrder - очередь заказов, ожидающих доставщика.
     * @param bakersArray - массив пекарей.
     */
    public BakerController(
            MyBlockingQueue<Order> waitingForCookingOrder,
            MyBlockingQueue<Order> waitingToBeSentOrder,
            int[] bakersArray) {
        this.waitingForCookingOrder = waitingForCookingOrder;
        this.waitingToBeSentOrder = waitingToBeSentOrder;
        this.bakersArray = bakersArray;
        bakerThreads = new Thread[bakersArray.length];

        for (int i = 0; i < bakersArray.length; i++) {
            Order fakeOrder = new Order("", -1, NULL);
            bakerThreads[i] = new Thread(
                    new BakerThread(
                            waitingToBeSentOrder,
                            bakersArray[i],
                            fakeOrder));
        }
    }

    @Override
    public void run() {
        while (true) {
            if (interrupt == Interrupt.INTERRUPT
                    && waitingForCookingOrder.getNumberOfElements() == 0) {
                while (isBakerAlive()) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                break;
            }
            boolean isFound = false;
            Order currentOrder = null;
            for (int i = 0; i < bakerThreads.length && !isFound; i++) {
                if (!bakerThreads[i].isAlive()) {
                    synchronized (waitingForCookingOrder) {

                        try {
                            if (!Thread.currentThread().isInterrupted()) {
                                currentOrder = waitingForCookingOrder.dequeue();
                            }
                        } catch (InterruptedException e) {
                            break;
                        }
                    }
                    if (currentOrder != null) {
                        bakerThreads[i] = new Thread(
                                new BakerThread(
                                        waitingToBeSentOrder,
                                        bakersArray[i],
                                        currentOrder));
                                        bakerThreads[i].start();
                        isFound = true;
                    }
                }
            }
            if (!isFound) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ignored) {

                }
            } else {
                System.out.println("Baker was found for order {"
                        + currentOrder.id()
                        + "} \""
                        + currentOrder.pizzaName()
                        + "\"");
            }
        }
        System.out.println("The bakery has fulfilled all orders");

    }

    private boolean isBakerAlive() {
        for (Thread bakerThread : bakerThreads) {
            if (bakerThread.isAlive()) {
                return true;
            }
        }
        return false;
    }
}
