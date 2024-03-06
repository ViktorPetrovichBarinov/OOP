package org.example;


import java.util.logging.Logger;

public class Main {

    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) throws InterruptedException {


        Controller controller = new Controller(4);
        controller.startWorking();
    }
}