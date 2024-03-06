package org.example.deliviryLogic;

import org.example.BackerLogic.BakerThread;
import org.example.Queu.MyBlockingQueue;
import org.example.ordersLogic.Order;

import java.util.ArrayList;

import static org.example.ordersLogic.State.NULL;

public class DeliveryController extends Thread{
    private final MyBlockingQueue<Order> waitingToBeSentOrder;
    private final DeliveryMan[] deliverymanArray;
    private final Thread[] deliverymanThreads;

    public DeliveryController(MyBlockingQueue<Order> waitingToBeSentOrder, DeliveryMan[] deliverymanArray) {
        this.waitingToBeSentOrder = waitingToBeSentOrder;
        this.deliverymanArray = deliverymanArray;
        this.deliverymanThreads = new Thread[deliverymanArray.length];

        for (int i = 0; i < deliverymanArray.length; i++) {
            Order fakeOrder = new Order("", -1, NULL);
            deliverymanThreads[i] = new Thread(new DeliverymanThread(waitingToBeSentOrder, new ArrayList<>(), deliverymanArray[i].timeToOnePizza()));
        }
    }


    @Override
    public void run() {
        while(true) {
            if (Thread.currentThread().isInterrupted() && waitingToBeSentOrder.getNumberOfElements() == 0) {
                break;
            }
            boolean isFound = false;
            if (waitingToBeSentOrder.getNumberOfElements() != 0) {//проверяем есть ли пицца на складе, есл инету ожидаем
                for (int i = 0; i < deliverymanThreads.length && !isFound; i++) {//Если есть, то ищем доставщика
                    if (!deliverymanThreads[i].isAlive()) {//если нашли свободного доставщика
                        ArrayList<Order> ordersForDeliveryMan = new ArrayList<>();//начинаем заполнять егэ рюкзак
                        synchronized (waitingToBeSentOrder) {//синхронизируемся на очереди
                            while(ordersForDeliveryMan.size() != deliverymanArray[i].pizzasCapacity()
                                    && waitingToBeSentOrder.getNumberOfElements() != 0) {//пока не заполнился рюкзак и не закончились заказы
                                try {
                                    ordersForDeliveryMan.add(waitingToBeSentOrder.dequeue());//добавляем элемент из очереди
                                } catch (InterruptedException e) {

                                }
                            }
                        }
                        deliverymanThreads[i] = new Thread(new DeliverymanThread(waitingToBeSentOrder,
                                ordersForDeliveryMan, deliverymanArray[i].timeToOnePizza()));
                        deliverymanThreads[i].start();
                        isFound = true;
                    }
                }
            }
            if (!isFound) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {

                }
            }
        }
        System.out.println("Out of pizzas in the warehouse");

    }

}
