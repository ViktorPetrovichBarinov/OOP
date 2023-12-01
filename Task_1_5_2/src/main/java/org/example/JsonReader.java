package org.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Some text.
 */
public class JsonReader {
    public static List<Note> readObjectsFromJason(String filePath) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(new File(filePath), new TypeReference<List<Note>>() {});
        } catch (IOException e) {
            System.err.println("Error reading from file");
            return null;
        }
    }

}
