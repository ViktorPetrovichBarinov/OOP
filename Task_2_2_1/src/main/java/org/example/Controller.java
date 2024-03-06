package org.example;

import org.example.BackerLogic.BakerController;
import org.example.Queu.MyBlockingQueue;
import org.example.UserLogic.UserInterface;
import org.example.config.Config;
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

    public Controller (Config config) {
        waitingForCookingOrder = new MyBlockingQueue<>(config.getMaxSizeOfWaitingForCookingOrder());
        int[] bakersArray = config.getBakersArray().stream().mapToInt(Integer::intValue).toArray();
        waitingToBeSentOrder = new MyBlockingQueue<>(config.getMaxSizeOfWaitingToBeSentOrder());

        this.userInterface = new UserInterface(waitingForCookingOrder, START_MESSAGE);


        this.bakerController = new BakerController(waitingForCookingOrder, waitingToBeSentOrder, bakersArray);

        DeliveryMan[] deliverymanArray = config.getDeliverymanArray().toArray(new DeliveryMan[0]);
        this.deliveryController = new DeliveryController(waitingToBeSentOrder,deliverymanArray);


    }


    public void startWorking() throws InterruptedException {
        userInterface.start();
        bakerController.start();
        deliveryController.start();
    }








}
