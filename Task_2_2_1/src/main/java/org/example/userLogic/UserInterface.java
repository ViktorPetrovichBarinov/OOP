package org.example.userLogic;

import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import org.example.queue.MyBlockingQueue;
import org.example.ordersLogic.Order;

/**
 * Класс ответветственнен за общение с пользователем.
 * Запускается отдельным потоком.
 * Задача считать имя пиццы, которую хочет заказать пользователь,
 *
 */
public class UserInterface extends Thread{
    private final MyBlockingQueue<Order> waitingForCookingOrder;
    private final AtomicInteger numberOfNextOrder = new AtomicInteger(1);

    public UserInterface(MyBlockingQueue<Order> waitingForCookingOrder, String startMessage) {
        this.waitingForCookingOrder = waitingForCookingOrder;
        System.out.println(startMessage);
    }


    @Override
    public void run() {
        try (Scanner input = new Scanner(System.in)) {
            while (true) {
                System.out.println("Enter name of pizza that you would like: ");
                String pizzaName = input.nextLine();
                System.out.println("Pizza name: " + pizzaName);
                synchronized (waitingForCookingOrder) {
                    int numberOfElements = waitingForCookingOrder.getNumberOfElements();
                    int maxCapacity = waitingForCookingOrder.getMaxCapacity();
                    if (numberOfElements == maxCapacity) {
                        String output = "Sorry the service is overloaded, please wait a minute.";
                        System.out.println(output);
                        continue;
                    }
                }
                int orderNumber = numberOfNextOrder.getAndIncrement();
                Order currentOrder = new Order(
                        pizzaName, orderNumber, org.example.ordersLogic.State.WAITING_FOR_COOKING);
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
