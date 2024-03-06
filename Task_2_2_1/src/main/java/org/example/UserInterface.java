package org.example;

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
    private final int maxSizeOfWaitingForCookingOrder;
    private final Queue<Order> waitingForCookingOrder;
    private final Scanner input = new Scanner(System.in);
    private final AtomicInteger numberOfNextOrder = new AtomicInteger(1);
    public UserInterface(Queue<Order> waitingForCookingOrder, int sizeOfWaitingForCookingOrder, String startMessage) {
        this.waitingForCookingOrder = waitingForCookingOrder;
        this.maxSizeOfWaitingForCookingOrder = sizeOfWaitingForCookingOrder;
        System.out.println(startMessage);
    }


    @Override
    public void run() {
        while(true) {
            System.out.print("Enter name of pizza that you would like: ");
            String pizzaName = input.nextLine();
            System.out.println("Pizza name: " + pizzaName);
            synchronized (waitingForCookingOrder) {
                int currentSizeOfWaitingForCookingOrder = waitingForCookingOrder.size();
                if (currentSizeOfWaitingForCookingOrder == maxSizeOfWaitingForCookingOrder) {
                    System.out.println("Sorry, but we have to much orders, please wait a little while!");
                } else {
                    waitingForCookingOrder.notify();
                    int orderNumber = numberOfNextOrder.getAndIncrement();
                    Order currentOrder = new Order(pizzaName, orderNumber,
                            org.example.ordersLogic.State.WAITING_FOR_COOKING);
                    waitingForCookingOrder.add(currentOrder);
                }
            }
        }
    }


}
