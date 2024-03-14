package org.example;

import org.example.config.Config;
import org.example.config.JsonConfig;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        JsonConfig jsonConfig = new JsonConfig();
        Config config = jsonConfig.jsonFileToPojo("build/resources/main/config.json");

        Controller controller = new Controller(config);
        controller.startWorking();
    }
}