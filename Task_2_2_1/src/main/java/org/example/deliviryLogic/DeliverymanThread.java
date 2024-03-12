package org.example.deliviryLogic;

import org.example.queue.MyBlockingQueue;
import org.example.ordersLogic.Order;
import org.example.ordersLogic.State;

import java.util.ArrayList;

public class DeliverymanThread implements Runnable{
    private final MyBlockingQueue<Order> waitingToBeSentOrder;
    private final ArrayList<Order> ordersInTheBag;
    private final Integer timeToOnePizza;
    public DeliverymanThread(MyBlockingQueue<Order> waitingToBeSentOrder, ArrayList<Order> orderInTheBag, int timeToOnePizza) {
        this.waitingToBeSentOrder = waitingToBeSentOrder;
        this.ordersInTheBag = orderInTheBag;
        this.timeToOnePizza = timeToOnePizza;
    }

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
