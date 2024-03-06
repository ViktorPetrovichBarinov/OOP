package org.example.deliviryLogic;

import org.example.BackerLogic.BakerThread;
import org.example.Queu.MyBlockingQueue;
import org.example.ordersLogic.Order;

import java.util.ArrayList;

import static org.example.ordersLogic.State.NULL;

public class DeliveryController extends Thread{
    private final MyBlockingQueue<Order> waitingToBeSentOrder;
    private final int[] deliverymansArray;
    private final Thread[] deliverymansThreads;

    public DeliveryController(MyBlockingQueue<Order> waitingToBeSentOrder, int[] deliverymansArray) {
        this.waitingToBeSentOrder = waitingToBeSentOrder;
        this.deliverymansArray = deliverymansArray;
        this.deliverymansThreads = new Thread[deliverymansArray.length];

        for (int i = 0; i < deliverymansArray.length; i++) {
            Order fakeOrder = new Order("", -1, NULL);
            deliverymansThreads[i] = new Thread(new BakerThread(waitingToBeSentOrder, deliverymansArray[i], fakeOrder));
        }
    }


    @Override
    public void run() {
        while(true) {
            boolean isFound = false;
            Order currentOrder;
            if (waitingToBeSentOrder.getNumberOfElements() != 0) {//проверяем есть ли пицца на складе, есл инету ожидаем
                for (int i = 0; i < deliverymansThreads.length && !isFound; i++) {//Если есть, то ищем доставщика
                    if (!deliverymansThreads[i].isAlive()) {//если нашли свободного доставщика
                        ArrayList<Order> ordersForDeliveryMan = new ArrayList<>();//начинаем заполнять егэ рюкзак
                        synchronized (waitingToBeSentOrder) {//синхронизируемся на очереди
                            while(ordersForDeliveryMan.size() != deliverymansArray[i]
                                    && waitingToBeSentOrder.getNumberOfElements() != 0) {//пока не заполнился рюкзак и не закончились заказы
                                try {
                                    ordersForDeliveryMan.add(waitingToBeSentOrder.dequeue());//добавляем элемент из очереди
                                } catch (InterruptedException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                        }
                        deliverymansThreads[i] = new Thread(new DeliverymanThread(waitingToBeSentOrder, ordersForDeliveryMan));
                        deliverymansThreads[i].start();
                        isFound = true;
                    }
                }
            }
            if (!isFound) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }

}
