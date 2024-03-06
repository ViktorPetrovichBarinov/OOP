package org.example;

import org.example.BackerLogic.BakerController;
import org.example.Queu.MyBlockingQueue;
import org.example.UserLogic.UserInterface;
import org.example.deliviryLogic.DeliveryController;
import org.example.deliviryLogic.DeliveryMan;
import org.example.ordersLogic.Order;
import org.example.staff.Baker;

public class Controller {
    private final MyBlockingQueue<Order> waitingForCookingOrder;
    private final MyBlockingQueue<Order> waitingToBeSentOrder;

    private final static String START_MESSAGE = "This is pizzeria, we are waiting for your orders";

    private UserInterface userInterface;
    private BakerController bakerController;
    private DeliveryController deliveryController;

    public Controller (int maxSizeOfWaitingForCookingOrder, int maxSizeOfWaitingToBeSentOrder) {
        waitingForCookingOrder = new MyBlockingQueue<>(maxSizeOfWaitingForCookingOrder);
        waitingToBeSentOrder = new MyBlockingQueue<>(maxSizeOfWaitingToBeSentOrder);

        this.userInterface = new UserInterface(waitingForCookingOrder, START_MESSAGE);

        int[] bakersArray = new int[] {5, 5, 5, 5};
        this.bakerController = new BakerController(waitingForCookingOrder, waitingToBeSentOrder, bakersArray);

        DeliveryMan[] deliverymanArray = new DeliveryMan[] {new DeliveryMan(1, 10),
                new DeliveryMan(2, 15),
                new DeliveryMan(3, 20),
                new DeliveryMan(4, 25)};
        this.deliveryController = new DeliveryController(waitingToBeSentOrder,deliverymanArray);


    }


    public void startWorking() throws InterruptedException {
        userInterface.start();
        bakerController.start();
        deliveryController.start();
    }








}
