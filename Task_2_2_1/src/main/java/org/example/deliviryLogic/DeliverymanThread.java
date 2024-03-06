package org.example.deliviryLogic;

import org.example.Queu.MyBlockingQueue;
import org.example.ordersLogic.Order;

import java.util.ArrayList;

public class DeliverymanThread implements Runnable{
    private final MyBlockingQueue<Order> waitingToBeSentOrder;
    private final ArrayList<Order> ordersInTheBag;
    private final static Integer timeToOnePizza = 20;
    public DeliverymanThread(MyBlockingQueue<Order> waitingToBeSentOrder, ArrayList<Order> orderInTheBag) {
        this.waitingToBeSentOrder = waitingToBeSentOrder;
        this.ordersInTheBag = orderInTheBag;
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
            String pizzaName = ordersInTheBag.get(i).pizzaName();
            int id = ordersInTheBag.get(i).id();
            System.out.println("Order " + pizzaName + "{" + id + "} was delivered");
        }


    }

}
