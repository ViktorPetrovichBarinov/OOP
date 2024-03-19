package org.example.config.config;

import org.example.Controller;
import org.example.config.Config;
import org.example.config.JsonConfig;
import org.example.delivirylogic.DeliveryMan;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonConfigTest {

    private Config config;
    private Controller controller;

    @BeforeEach
    void before() {
        JsonConfig jsonConfig = new JsonConfig();
        this.config = jsonConfig.jsonFileToPojo("build/resources/test/config.json");

        this.controller = new Controller(config);
    }

    @Test
    void test1() {
        assertEquals(config.getMaxSizeOfWaitingForCookingOrder(), 25);
        int[] bakersArray = new int[] {2, 3, 4};
        for (int i = 0; i < config.getBakersArray().size(); i++) {
            assertEquals(config.getBakersArray().get(i), bakersArray[i]);
        }
        assertEquals(config.getMaxSizeOfWaitingToBeSentOrder(), 15);
        DeliveryMan[] deliveryMEN = new DeliveryMan[] {
                new DeliveryMan(5, 20),
                new DeliveryMan(8, 15)
        };
        for (int i = 0; i < config.getDeliverymanArray().size(); i++) {
            assertEquals(config.getDeliverymanArray().get(i), deliveryMEN[i]);
        }
    }

}
