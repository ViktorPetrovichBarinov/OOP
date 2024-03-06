package org.example;

import org.example.BackerLogic.BakerController;
import org.example.Queu.MyBlockingQueue;
import org.example.UserLogic.UserInterface;
import org.example.deliviryLogic.DeliveryController;
import org.example.ordersLogic.Order;

public class Controller {
    private final MyBlockingQueue<Order> waitingForCookingOrder;
    private final MyBlockingQueue<Order> waitingToBeSentOrder;

    private final static String START_MESSAGE = "This is pizzeria, we are waiting for your orders";

    public Controller (int maxSizeOfWaitingForCookingOrder, int maxSizeOfWaitingToBeSentOrder) {
        waitingForCookingOrder = new MyBlockingQueue<>(maxSizeOfWaitingForCookingOrder);
        waitingToBeSentOrder = new MyBlockingQueue<>(maxSizeOfWaitingToBeSentOrder);
    }


    public void startWorking() throws InterruptedException {


        UserInterface ui = new UserInterface(waitingForCookingOrder, START_MESSAGE);
        ui.start();
        int[] bakersArray = new int[] {5, 5, 5, 5};
        BakerController bakerController = new BakerController(waitingForCookingOrder, waitingToBeSentOrder, bakersArray);
        bakerController.start();
        int[] deliverymanArray = new int[] {1,2,3,4};
        DeliveryController deliveryController = new DeliveryController(waitingToBeSentOrder,deliverymanArray);
        deliveryController.start();

    }


}
