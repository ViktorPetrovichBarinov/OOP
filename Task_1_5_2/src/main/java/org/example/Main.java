package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;


public class Main {
    public static void main(String[] args) {
        // Ваша строка
        String yourString = "Test string";

        // Создаем объект ObjectMapper
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // Преобразуем строку в JSON-объект
            Object json = objectMapper.readValue(yourString, Object.class);

            // Записываем JSON-объект в файл
            objectMapper.writeValue(new File("output.json"), json);

            System.out.println("JSON успешно записан в файл 'output.json'");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}