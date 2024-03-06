package org.example.BackerLogic;

import org.example.Queu.MyBlockingQueue;
import org.example.ordersLogic.Order;

import static org.example.ordersLogic.State.NULL;

public class BakerController extends Thread{
    private final MyBlockingQueue<Order> waitingForCookingOrder;
    private final MyBlockingQueue<Order> waitingToBeSentOrder;
    private final int[] bakersArray;
    private final Thread[] bakerThreads;

    public BakerController(MyBlockingQueue<Order> waitingForCookingOrder, MyBlockingQueue<Order> waitingToBeSentOrder, int[] bakersArray) {
        this.waitingForCookingOrder = waitingForCookingOrder;
        this.waitingToBeSentOrder = waitingToBeSentOrder;
        this.bakersArray = bakersArray;
        bakerThreads = new Thread[bakersArray.length];

        for (int i = 0; i < bakersArray.length; i++) {
            Order fakeOrder = new Order("", -1, NULL);
            bakerThreads[i] = new Thread(new BakerThread(waitingToBeSentOrder, bakersArray[i], fakeOrder));
        }
    }

    @Override
    public void run() {
        while(true) {
            boolean isFound = false;
            Order currentOrder = null;
            for (int i = 0; i < bakerThreads.length && !isFound; i++) {
                if (!bakerThreads[i].isAlive()) {
                    synchronized (waitingForCookingOrder) {
                        try {
                            currentOrder = waitingForCookingOrder.dequeue();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    bakerThreads[i] = new Thread(new BakerThread(waitingToBeSentOrder, bakersArray[i], currentOrder));
                    bakerThreads[i].start();
                    isFound = true;
                }
            }
            if (!isFound) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            } else {
                System.out.println("Baker was found for order {" + currentOrder.id() + "}  \"" + currentOrder.pizzaName() + "\"");
            }
        }
    }
    
}
