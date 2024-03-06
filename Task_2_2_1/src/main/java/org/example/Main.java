package org.example;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.config.Config;
import org.example.config.JsonConfig;
import org.example.deliviryLogic.DeliveryMan;

import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Logger;

public class Main {

    private static final Logger logger = Logger.getLogger(Main.class.getName());
    private static final ObjectMapper objectMapper = new ObjectMapper();
    public static void main(String[] args) throws InterruptedException, IOException {

        JsonConfig jsonConfig = new JsonConfig();


        Config config = jsonConfig.jsonFileToPojo("C:\\Users\\chydi\\IdeaProjects\\OOP\\Task_2_2_1\\src\\main\\resources\\config.json");
        System.out.println(config.getMaxSizeOfWaitingForCookingOrder());
        System.out.println(config.getMaxSizeOfWaitingToBeSentOrder());
        System.out.println(config.getBakersArray());
        System.out.println(config.getDeliverymanArray());

        Controller controller = new Controller(config);
        controller.startWorking();
    }
}