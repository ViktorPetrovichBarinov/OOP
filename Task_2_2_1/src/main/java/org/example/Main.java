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
        Config config = jsonConfig.jsonFileToPojo("build/resources/main/config.json");

        Controller controller = new Controller(config);
        controller.startWorking();
    }
}