package org.example.BackerLogic;

import org.example.ordersLogic.Order;

public class BakerThread implements Runnable {
    private int secondsToOnePizza;
    private Order order;
    public BakerThread(int secondsToOnePizza, Order order) {
        this.secondsToOnePizza = secondsToOnePizza;
        this.order = order;
    }

    @Override
    public void run() {
        try {
            System.out.println("Приступил к выполненю заказа:" + order.pizzaName());
            Thread.sleep(secondsToOnePizza * 1000L);
            System.out.println("Name: " + order.pizzaName());
            System.out.println("Id: " + order.id());
            System.out.println("was transferred to the warehouse");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
