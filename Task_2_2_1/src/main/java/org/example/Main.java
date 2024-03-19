package org.example;

import org.example.config.Config;
import org.example.config.JsonConfig;

/**
 * Main.
 */
public class Main {

    /**
     * Функция main.
     *
     * @param args - параметры командной строки.
     */
    public static void main(String[] args) {


        JsonConfig jsonConfig = new JsonConfig();
        Config config = jsonConfig.jsonFileToPojo("build/resources/main/config.json");

        Controller controller = new Controller(config);

        try {
            controller.startWorking();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}