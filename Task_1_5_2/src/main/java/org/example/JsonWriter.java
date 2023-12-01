package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.example.JsonReader.readObjectsFromJason;

public class JsonWriter {

    public static void initJsonFile(String filePath) {
        List<Note> notes = new ArrayList<>();
        writeObjectsToJson(notes, filePath);
    }

    public static void writeObjectsToJson(List<Note> notes, String filePath) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            objectMapper.writeValue(new File(filePath), notes);
            System.out.println("Write out");
        } catch (IOException e) {
            System.err.println("Didn't write out");
        }

    }

    public static void addObjectToJson(Note note, String filePath) {
        List<Note> notes = readObjectsFromJason(filePath);

        if (notes == null) {
            notes = new ArrayList<>();
        }
        notes.add(note);

        writeObjectsToJson(notes, filePath);
    }

}
