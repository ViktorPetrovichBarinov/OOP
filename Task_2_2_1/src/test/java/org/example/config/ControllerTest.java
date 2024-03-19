package org.example.config;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.example.Controller;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

/**
 * Тесты для класса Controller.
 */
public class ControllerTest {
    @Test
    void test1() throws IOException, InterruptedException {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);



        JsonConfig jsonConfig = new JsonConfig();
        Config config = jsonConfig.jsonFileToPojo("build/resources/test/controllerTest1.json");

        Controller controller = new Controller(config);

        Thread cont = new Thread(() -> {
            try {
                controller.startWorking();
            } catch (InterruptedException e) {
                System.err.println("Incorrect exit.");
            }
        });

        StringBuilder str = new StringBuilder()
                .append("1\n2\n3\n4\n5\n6\n7\n8\n9\n")
                .append("10\n11\n12\n13\n14\n15\n16\n17\n18\n19\n20\n");
        String userInput = str.toString();
        ByteArrayInputStream inputStream = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(inputStream);

        cont.start();

        while (cont.isAlive()) {
            Thread.sleep(1000);
        }


        String output = outputStream.toString();
        System.setOut(originalOut);
        assertTrue(output.contains("This is pizzeria, we are waiting for your orders"));
        assertTrue(output.contains("Enter name of pizza that you would like: "));

        assertTrue(output.contains("Pizza name: 1"));
        assertTrue(output.contains("Pizza name: 2"));
        assertTrue(output.contains("Pizza name: 3"));
        assertTrue(output.contains("Pizza name: 4"));
        assertTrue(output.contains("Pizza name: 5"));
        assertTrue(output.contains("Pizza name: 6"));
        assertTrue(output.contains("Pizza name: 7"));
        assertTrue(output.contains("Pizza name: 8"));
        assertTrue(output.contains("Pizza name: 9"));

        assertTrue(output.contains("Start cooking order {1}:1"));
        assertTrue(output.contains("Start cooking order {2}:2"));
        assertTrue(output.contains("Start cooking order {3}:3"));
        assertTrue(output.contains("Start cooking order {4}:4"));
        assertTrue(output.contains("Start cooking order {5}:5"));
        assertTrue(output.contains("Start cooking order {6}:6"));
        assertTrue(output.contains("Start cooking order {7}:7"));
        assertTrue(output.contains("Start cooking order {8}:8"));
        assertTrue(output.contains("Start cooking order {9}:9"));

        assertTrue(output.contains("Sorry the service is overloaded, please wait a minute."));

        assertTrue(output.contains("Baker was found for order {1} \"1\""));
        assertTrue(output.contains("Baker was found for order {2} \"2\""));
        assertTrue(output.contains("Baker was found for order {3} \"3\""));
        assertTrue(output.contains("Baker was found for order {4} \"4\""));
        assertTrue(output.contains("Baker was found for order {5} \"5\""));
        assertTrue(output.contains("Baker was found for order {6} \"6\""));
        assertTrue(output.contains("Baker was found for order {7} \"7\""));
        assertTrue(output.contains("Baker was found for order {8} \"8\""));
        assertTrue(output.contains("Baker was found for order {9} \"9\""));

        assertTrue(output.contains("Pizza: 1 {1} was transferred to the warehouse"));
        assertTrue(output.contains("Pizza: 2 {2} was transferred to the warehouse"));
        assertTrue(output.contains("Pizza: 3 {3} was transferred to the warehouse"));
        assertTrue(output.contains("Pizza: 4 {4} was transferred to the warehouse"));
        assertTrue(output.contains("Pizza: 5 {5} was transferred to the warehouse"));
        assertTrue(output.contains("Pizza: 6 {6} was transferred to the warehouse"));
        assertTrue(output.contains("Pizza: 7 {7} was transferred to the warehouse"));
        assertTrue(output.contains("Pizza: 8 {8} was transferred to the warehouse"));
        assertTrue(output.contains("Pizza: 9 {9} was transferred to the warehouse"));

        assertTrue(output.contains("Deliveryman starting delivery orders:"));

        assertTrue(output.contains("1{1}"));
        assertTrue(output.contains("2{2}"));
        assertTrue(output.contains("3{3}"));
        assertTrue(output.contains("4{4}"));
        assertTrue(output.contains("5{5}"));
        assertTrue(output.contains("6{6}"));
        assertTrue(output.contains("7{7}"));
        assertTrue(output.contains("8{8}"));
        assertTrue(output.contains("9{9}"));


        assertTrue(output.contains("The bakery has fulfilled all orders"));
        assertTrue(output.contains("Delivery completed all orders"));
        assertTrue(output.contains("Success!"));

        System.out.println(output);

        printStream.close();
        outputStream.close();
    }
}
