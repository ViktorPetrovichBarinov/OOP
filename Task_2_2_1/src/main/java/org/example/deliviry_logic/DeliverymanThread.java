package org.example.deliviry_logic;

import org.example.orders_logic.Order;
import org.example.orders_logic.State;
import java.util.ArrayList;

/**
 * Класс, ответственен за запуск потока доставщика.
 */
public class DeliverymanThread implements Runnable {
    private final ArrayList<Order> ordersInTheBag;
    private final Integer timeToOnePizza;

    public DeliverymanThread(ArrayList<Order> orderInTheBag, int timeToOnePizza) {
        this.ordersInTheBag = orderInTheBag;
        this.timeToOnePizza = timeToOnePizza;
    }

    /**
     * Метод запускает работу сервиса доставки.
     */
    @Override
    public void run() {
        System.out.println("Deliveryman starting delivery orders:");
        for (int i = 0; i < ordersInTheBag.size(); i++) {
            String pizzaName = ordersInTheBag.get(i).pizzaName();
            int id = ordersInTheBag.get(i).id();
            System.out.println("  " + (i + 1) + ") " + pizzaName + "{" + id + "}");
        }

        for (int i = 0; i < ordersInTheBag.size(); i++) {
            try {
                Thread.sleep(timeToOnePizza / (i + 1) * 1000L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            ordersInTheBag.get(i).setState(State.DELIVERED);
            String pizzaName = ordersInTheBag.get(i).pizzaName();
            int id = ordersInTheBag.get(i).id();
            System.out.println("Order " + pizzaName + "{" + id + "} was delivered");
        }
    }
}
