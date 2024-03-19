package org.example;

import org.example.config.Config;
import org.example.config.JsonConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

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
            System.err.println("Incorrect exit.");
            e.printStackTrace();
        }


    }
}