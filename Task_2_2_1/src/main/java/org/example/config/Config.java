package org.example.config;

import java.util.List;
import org.example.delivirylogic.DeliveryMan;

/**
 * Класс ответственен за конфигурацию Controller.
 */
public class Config {
    private int maxSizeOfWaitingForCookingOrder;
    private List<Integer> bakersArray;
    private int maxSizeOfWaitingToBeSentOrder;
    private List<DeliveryMan> deliverymanArray;

    /**
     * Конструктор без параметров.
     */
    public Config() {

    }

    /**
     * Конструктор.
     *
     * @param maxSizeOfWaitingForCookingOrder - максимальный размер очереди,
     *                                        ожидающих повара заказов.
     * @param bakersArray - массив пекарей.
     * @param maxSizeOfWaitingToBeSentOrder - максимальный размер очереди,
     *                                      ожидающих доставщика заказов.
     * @param deliverymanArray - массив доставщиков.
     */
    public Config(int maxSizeOfWaitingForCookingOrder,
                  List<Integer> bakersArray,
                  int maxSizeOfWaitingToBeSentOrder,
                  List<DeliveryMan> deliverymanArray) {
        this.maxSizeOfWaitingForCookingOrder = maxSizeOfWaitingForCookingOrder;
        this.bakersArray = bakersArray;
        this.maxSizeOfWaitingToBeSentOrder = maxSizeOfWaitingToBeSentOrder;
        this.deliverymanArray = deliverymanArray;
    }

    public int getMaxSizeOfWaitingForCookingOrder() {
        return maxSizeOfWaitingForCookingOrder;
    }

    public int getMaxSizeOfWaitingToBeSentOrder() {
        return maxSizeOfWaitingToBeSentOrder;
    }

    public List<DeliveryMan> getDeliverymanArray() {
        return deliverymanArray;
    }

    public List<Integer> getBakersArray() {
        return bakersArray;
    }

    public void setBakersArray(List<Integer> bakersArray) {
        this.bakersArray = bakersArray;
    }

    public void setDeliverymanArray(List<DeliveryMan> deliverymanArray) {
        this.deliverymanArray = deliverymanArray;
    }

    public void setMaxSizeOfWaitingToBeSentOrder(int maxSizeOfWaitingToBeSentOrder) {
        this.maxSizeOfWaitingToBeSentOrder = maxSizeOfWaitingToBeSentOrder;
    }

    public void setMaxSizeOfWaitingForCookingOrder(int maxSizeOfWaitingForCookingOrder) {
        this.maxSizeOfWaitingForCookingOrder = maxSizeOfWaitingForCookingOrder;
    }
}
