package org.example.config.config;

import org.example.config.Config;
import org.example.delivirylogic.DeliveryMan;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConfigTest {

    @Test
    public void testConstructorAndGetters() {
        int maxSizeForCooking = 5;
        List<Integer> bakers = Arrays.asList(1, 2, 3);
        int maxSizeToBeSent = 10;
        List<DeliveryMan> deliveryMen = new ArrayList<>();

        Config config = new Config(maxSizeForCooking, bakers, maxSizeToBeSent, deliveryMen);

        assertEquals(maxSizeForCooking, config.getMaxSizeOfWaitingForCookingOrder());
        assertEquals(bakers, config.getBakersArray());
        assertEquals(maxSizeToBeSent, config.getMaxSizeOfWaitingToBeSentOrder());
        assertEquals(deliveryMen, config.getDeliverymanArray());
    }

    @Test
    public void testSetters() {
        Config config = new Config();

        int maxSizeForCooking = 8;
        config.setMaxSizeOfWaitingForCookingOrder(maxSizeForCooking);
        assertEquals(maxSizeForCooking, config.getMaxSizeOfWaitingForCookingOrder());

        List<Integer> bakers = Arrays.asList(4, 5, 6);
        config.setBakersArray(bakers);
        assertEquals(bakers, config.getBakersArray());

        int maxSizeToBeSent = 15;
        config.setMaxSizeOfWaitingToBeSentOrder(maxSizeToBeSent);
        assertEquals(maxSizeToBeSent, config.getMaxSizeOfWaitingToBeSentOrder());

        List<DeliveryMan> deliveryMen = new ArrayList<>();
        config.setDeliverymanArray(deliveryMen);
        assertEquals(deliveryMen, config.getDeliverymanArray());
    }
}
