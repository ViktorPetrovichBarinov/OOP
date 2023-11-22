package org.example;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Some text.
 */
public class Main {
    /**
     * Some text.
     *
     * @param args  - Some text.
     */
    public static void main(String[] args) {
        ArrayList<Integer> answer = new ArrayList<>();
        String fileName = "src/test/resources/bigDataTest.txt";
        File file = new File(fileName);
        try {
            if (file.createNewFile()) {
                System.out.println("The File was succesfuly created");
            } else {
                System.out.println("Technical chocolates(create)");
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                int countOfElements = 0;
                for (int i = 0; i < 100000000; i++) {
                    writer.write("12345678");
                    countOfElements += 8;
                }
                String pattern = "a";
                writer.write(pattern);
                answer.add(countOfElements);
                System.out.println("Index: " + countOfElements);
            }

        } catch (IOException e) {
            System.out.println("приплыли");
        }

    }
}