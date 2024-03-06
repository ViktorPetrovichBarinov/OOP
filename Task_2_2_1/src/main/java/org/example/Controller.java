package org.example;

import org.example.BackerLogic.BackerController;
import org.example.ordersLogic.Order;

import java.lang.reflect.Parameter;
import java.util.LinkedList;
import java.util.Queue;

public class Controller {
    private final int maxSizeOfWaitingForCookingOrder;
    private final Queue<Order> waitingForCookingOrder = new LinkedList<>();


    private final static String START_MESSAGE = "This is pizzeria, we are waiting for your orders";

    public Controller (int maxSizeOfWaitingForCookingOrder) {
        this.maxSizeOfWaitingForCookingOrder = maxSizeOfWaitingForCookingOrder;
    }


    public void startWorking() throws InterruptedException {


        UserInterface ui = new UserInterface(waitingForCookingOrder, maxSizeOfWaitingForCookingOrder, START_MESSAGE);
        ui.start();
        int[] bakersArray = new int[] {5, 5, 5, 5};
        BackerController bakerController = new BackerController(waitingForCookingOrder, maxSizeOfWaitingForCookingOrder, bakersArray);
        bakerController.start();



    }


}
