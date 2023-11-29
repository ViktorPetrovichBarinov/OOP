package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Locale;


public class Main {
    public static void main(String[] args) throws JsonProcessingException {
        Date localTime = new Date(System.currentTimeMillis());
        System.out.println(localTime);
        ObjectMapper objectMapper = new ObjectMapper();


        try {
            ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
            Path filePath = Path.of("test.json");


            Note note1 = new Note("test note 1", "content 1", new Date());
            String json1 = objectWriter.writeValueAsString(note1);
            System.out.println(json1);
            Files.writeString(filePath, json1, StandardOpenOption.CREATE, StandardOpenOption.APPEND);

            Note note2 = new Note("test note 2", "content 2", new Date());
            String json2 = objectWriter.writeValueAsString(note2);
            System.out.println(json2);
            Files.writeString(filePath, json2, StandardOpenOption.APPEND);


            System.out.println("JSON успешно записан в файл: " + filePath.toAbsolutePath());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }
}