package org.example.UserLogic;

import org.example.Queu.MyBlockingQueue;
import org.example.ordersLogic.Order;
import org.example.ordersLogic.State;

import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * Класс ответветственнен за общение с пользователем.
 * Запускается отдельным потоком.
 * Задача считать имя пиццы, которую хочет заказать пользователь,
 *
 */
public class UserInterface extends Thread{
//    private final int maxSizeOfWaitingForCookingOrder;
//    private final Queue<Order> waitingForCookingOrder;

    private final MyBlockingQueue<Order> waitingForCookingOrder;


    private final AtomicInteger numberOfNextOrder = new AtomicInteger(1);
    public UserInterface(MyBlockingQueue<Order> waitingForCookingOrder, String startMessage) {
        this.waitingForCookingOrder = waitingForCookingOrder;
        System.out.println(startMessage);
    }


    @Override
    public void run() {
        try (Scanner input = new Scanner(System.in)) {
            while(true) {
                System.out.println("Enter name of pizza that you would like: ");
                String pizzaName = input.nextLine();
                System.out.println("Pizza name: " + pizzaName);
                synchronized (waitingForCookingOrder) {
                    if (waitingForCookingOrder.getNumberOfElements() == waitingForCookingOrder.getMaxCapacity()) {
                        System.out.println("Sorry the service is overloaded, please wait a minute.");
                        continue;
                    }
                }
                int orderNumber = numberOfNextOrder.getAndIncrement();
                Order currentOrder = new Order(pizzaName, orderNumber, org.example.ordersLogic.State.WAITING_FOR_COOKING);
                synchronized (waitingForCookingOrder) {
                    try {
                        waitingForCookingOrder.enqueue(currentOrder);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("We do not accept orders");
        }
    }
}
