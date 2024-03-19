package org.example;

import org.example.backer_logic.BakerController;
import org.example.config.Config;
import org.example.deliviry_logic.DeliveryController;
import org.example.deliviry_logic.DeliveryMan;
import org.example.orders_logic.Order;
import org.example.queue.MyBlockingQueue;
import org.example.user_logic.UserInterface;


/**
 * Класс "Контроллер" ответственнен за работу всех сервисов.
 */
public class Controller {
    private final UserInterface userInterface;
    private final BakerController bakerController;
    private final DeliveryController deliveryController;

    /**
     * Конструктор класса "Контроллер".
     *
     * @param config - S.
     */
    public Controller(Config config) {
        MyBlockingQueue<Order> waitingForCookingOrder = new MyBlockingQueue<>(config.getMaxSizeOfWaitingForCookingOrder());
        MyBlockingQueue<Order> waitingToBeSentOrder = new MyBlockingQueue<>(config.getMaxSizeOfWaitingToBeSentOrder());
        int[] bakersArray = config.getBakersArray().stream().mapToInt(Integer::intValue).toArray();

        String startMessage = "This is pizzeria, we are waiting for your orders";
        this.userInterface = new UserInterface(waitingForCookingOrder, startMessage);
        this.bakerController = new BakerController(waitingForCookingOrder, waitingToBeSentOrder, bakersArray);

        DeliveryMan[] deliverymanArray = config.getDeliverymanArray().toArray(new DeliveryMan[0]);
        this.deliveryController = new DeliveryController(waitingToBeSentOrder, deliverymanArray);
    }


    /**
     * Метод запускает работу каждого сервиса.
     *
     * @throws InterruptedException - S.
     */
    public void startWorking() throws InterruptedException {
        userInterface.start();
        bakerController.start();
        deliveryController.start();

        userInterfaceCheck();
    }

    private void userInterfaceCheck() throws InterruptedException {
        while (userInterface.isAlive()) {
            Thread.sleep(1000);
        }
        bakerController.interrupt = Interrupt.INTERRUPT;
        bakerController.interrupt();
        bakerControllerCheck();
    }

    private void bakerControllerCheck() throws InterruptedException {
        while (bakerController.isAlive()) {
            Thread.sleep(1000);
        }
        deliveryController.interrupt = Interrupt.INTERRUPT;
        deliveryController.interrupt();
        deliveryControllerCheck();
    }

    private void deliveryControllerCheck() throws InterruptedException {
        while (deliveryController.isAlive()) {
            Thread.sleep(1000);
        }
        System.out.println("Success!");
    }
}
