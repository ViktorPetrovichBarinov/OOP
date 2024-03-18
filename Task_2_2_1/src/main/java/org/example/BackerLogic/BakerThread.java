package org.example.BackerLogic;

import org.example.orders_logic.Order;
import org.example.orders_logic.State;
import org.example.queue.MyBlockingQueue;

/**
 * Класс ответственен за выполнение работы пекаря в другом потоке.
 */
public class BakerThread implements Runnable {
    private final MyBlockingQueue<Order> waitingToBeSent;
    private final int secondsToOnePizza;
    private final Order order;

    /**
     * Конструктор.
     *
     * @param waitingToBeSent - очередь приготовленных, ожидающих отправку заказов.
     * @param secondsToOnePizza - время на приготовление одной пиццы.
     * @param order - сам заказ, который надо приготовить.
     */
    public BakerThread(MyBlockingQueue<Order> waitingToBeSent, int secondsToOnePizza, Order order) {
        this.waitingToBeSent = waitingToBeSent;
        this.secondsToOnePizza = secondsToOnePizza;
        this.order = order;
    }

    @Override
    public void run() {
        System.out.println("Start cooking order {"
                + order.id()
                + "}:"
                + order.pizzaName());
        order.setState(State.COOKING);
        try {
            Thread.sleep(secondsToOnePizza * 1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        order.setState(State.WAITING_TO_BE_SENT);
        synchronized (waitingToBeSent) {
            try {
                waitingToBeSent.enqueue(order);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Pizza: "
                + order.pizzaName()
                + " {" + order.id()
                + "} was transferred to the warehouse");
    }
}
