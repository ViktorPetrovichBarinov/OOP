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
            System.out.println("Записали");
        } catch (IOException e) {
            System.err.println("Не записали");
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

    public static void main(String[] args) {
        Date currentDate = new Date();
        Note note1 = new Note("Name1", "Body1", currentDate);
        Note note2 = new Note("Name2", "Body2", currentDate);
        Note note3 = new Note("Name3", "Body3", currentDate);
        Note note4 = new Note("Name3", "Body3", currentDate);
        List<Note> notes = new ArrayList<>();
        notes.add(note1);
        notes.add(note2);
        notes.add(note3);

        addObjectToJson(note4, "test.json");


    }

}
