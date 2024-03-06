package org.example.BackerLogic;

import org.example.ordersLogic.Order;

import java.util.LinkedList;
import java.util.Queue;

import static org.example.ordersLogic.State.NULL;

public class BackerController extends Thread{
    private final int maxSizeOfWaitingForCookingOrder;
    private final Queue<Order> waitingForCookingOrder;
    private final int[] bakersArray;
    private final Thread[] bakerThreads;

    public BackerController (Queue<Order> waitingForCookingOrder, int maxSizeOfWaitingForCookingOrder, int[] bakersArray) {
        this.waitingForCookingOrder = waitingForCookingOrder;
        this.maxSizeOfWaitingForCookingOrder = maxSizeOfWaitingForCookingOrder;
        this.bakersArray = bakersArray;
        bakerThreads = new Thread[bakersArray.length];

        for (int i = 0; i < bakersArray.length; i++) {
            Order fakeOrder = new Order("", -1, NULL);
            bakerThreads[i] = new Thread(new BakerThread(bakersArray[i], fakeOrder));
        }
    }

    @Override
    public void run() {
        while(true) {
            synchronized (waitingForCookingOrder) {
                if (waitingForCookingOrder.size() == 0) {
                    try {
                        System.out.println("Ожидаю поступления заказов");
                        waitingForCookingOrder.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("Пытаюсь найти повара");
                    boolean isFound = false;
                    for (int i = 0; i < bakerThreads.length && !isFound; i++) {
                        if (!bakerThreads[i].isAlive()) {
                            Order currentOrder = waitingForCookingOrder.poll();
                            bakerThreads[i] = new Thread(new BakerThread(bakersArray[i], currentOrder));
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
                        System.out.println("Нашел повара");
                    }
                }
            }
        }
    }
    
}
