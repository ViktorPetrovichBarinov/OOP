package org.example.deliviryLogic;

import org.example.Interrupt;
import org.example.queue.MyBlockingQueue;
import org.example.ordersLogic.Order;

import java.util.ArrayList;

import static org.example.ordersLogic.State.NULL;

public class DeliveryController extends Thread{
    private final MyBlockingQueue<Order> waitingToBeSentOrder;
    private final DeliveryMan[] deliverymanArray;
    private final Thread[] deliverymanThreads;

    public Interrupt interrupt = Interrupt.NOT_INTERRUPT;

    public DeliveryController(MyBlockingQueue<Order> waitingToBeSentOrder, DeliveryMan[] deliverymanArray) {
        this.waitingToBeSentOrder = waitingToBeSentOrder;
        this.deliverymanArray = deliverymanArray;
        this.deliverymanThreads = new Thread[deliverymanArray.length];

        for (int i = 0; i < deliverymanArray.length; i++) {
            Order fakeOrder = new Order("", -1, NULL);
            deliverymanThreads[i] = new Thread(new DeliverymanThread(new ArrayList<>(), deliverymanArray[i].timeToOnePizza()));
        }
    }


    @Override
    public void run() {
        while(true) {
            if (interrupt == Interrupt.INTERRUPT && waitingToBeSentOrder.getNumberOfElements() == 0) {
                while(isDeliverymanAlive()) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                break;
            }
            boolean isFound = false;
            if (waitingToBeSentOrder.getNumberOfElements() != 0) {//проверяем есть ли пицца на складе, есл инету ожидаем
                for (int i = 0; i < deliverymanThreads.length && !isFound; i++) {//Если есть, то ищем доставщика
                    if (!deliverymanThreads[i].isAlive()) {//если нашли свободного доставщика
                        ArrayList<Order> ordersForDeliveryMan = new ArrayList<>();//начинаем заполнять егэ рюкзак
                        synchronized (waitingToBeSentOrder) {//синхронизируемся на очереди
                            while(ordersForDeliveryMan.size() != deliverymanArray[i].pizzasCapacity()
                                    && waitingToBeSentOrder.getNumberOfElements() != 0) {
                                //пока не заполнился рюкзак и не закончились заказы
                                try {
                                    //добавляем элемент из очереди
                                    ordersForDeliveryMan.add(waitingToBeSentOrder.dequeue());
                                } catch (InterruptedException e) {

                                }
                            }
                        }
                        deliverymanThreads[i] = new Thread(
                                new DeliverymanThread(
                                        ordersForDeliveryMan,
                                        deliverymanArray[i].timeToOnePizza()));
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
        System.out.println("Delivery completed all orders");

    }


    private boolean isDeliverymanAlive() {
        for (int i = 0; i < deliverymanThreads.length; i++) {
            if (deliverymanThreads[i].isAlive()) {
                return  true;
            }
        }
        return false;
    }
}
