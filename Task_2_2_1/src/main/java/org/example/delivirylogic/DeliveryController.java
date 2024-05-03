package org.example.delivirylogic;

import static org.example.orderslogic.State.NULL;

import java.util.ArrayList;
import org.example.Interrupt;
import org.example.orderslogic.Order;
import org.example.queue.MyBlockingQueue;

/**
 * Микросервис осуществляет работу с доставщиками.
 */
public class DeliveryController extends Thread {
    private final MyBlockingQueue<Order> waitingToBeSentOrder;
    private final DeliveryMan[] deliverymanArray;
    private final Thread[] deliverymanThreads;

    public Interrupt interrupt = Interrupt.NOT_INTERRUPT;

    /**
     * Конструктор.
     *
     * @param waitingToBeSentOrder - очередь пицц ожидающих доставщика.
     * @param deliverymanArray - список доставщиков.
     */
    public DeliveryController(
            MyBlockingQueue<Order> waitingToBeSentOrder,
            DeliveryMan[] deliverymanArray) {
        this.waitingToBeSentOrder = waitingToBeSentOrder;
        this.deliverymanArray = deliverymanArray;
        this.deliverymanThreads = new Thread[deliverymanArray.length];

        for (int i = 0; i < deliverymanArray.length; i++) {
            Order fakeOrder = new Order("", -1, NULL);
            deliverymanThreads[i] = new Thread(
                    new DeliverymanThread(
                            new ArrayList<>(), deliverymanArray[i].timeToFirstPizza()));
        }
    }


    @Override
    public void run() {
        while (true) {
            if (interrupt == Interrupt.INTERRUPT
                    && waitingToBeSentOrder.getNumberOfElements() == 0) {
                while (isDeliverymanAlive()) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                break;
            }
            boolean isFound = false;
            //проверяем есть ли пицца на складе, есл инету ожидаем
            if (waitingToBeSentOrder.getNumberOfElements() != 0) {
                //Если есть, то ищем доставщика
                for (int i = 0; i < deliverymanThreads.length && !isFound; i++) {
                    //если нашли свободного доставщика
                    if (!deliverymanThreads[i].isAlive()) {
                        //начинаем заполнять егэ рюкзак
                        ArrayList<Order> ordersForDeliveryMan = new ArrayList<>();
                        synchronized (waitingToBeSentOrder) { //синхронизируемся на очереди
                            while (ordersForDeliveryMan.size() != deliverymanArray[i].pizzasCapacity()
                                    && waitingToBeSentOrder.getNumberOfElements() != 0) {
                                //пока не заполнился рюкзак и не закончились заказы
                                try {
                                    //добавляем элемент из очереди
                                    ordersForDeliveryMan.add(waitingToBeSentOrder.dequeue());
                                } catch (InterruptedException ignored) {

                                }
                            }
                        }
                        deliverymanThreads[i] = new Thread(
                                new DeliverymanThread(
                                        ordersForDeliveryMan,
                                        deliverymanArray[i].timeToFirstPizza()));
                        deliverymanThreads[i].start();
                        isFound = true;
                    }
                }
            }
            if (!isFound) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ignored) {

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
