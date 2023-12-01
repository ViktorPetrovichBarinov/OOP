package org.example;

import static org.example.JsonReader.readObjectsFromJason;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



public class JsonWriter {
    /**
     * Some text.
     *
     * @param filePath  - Some text.
     */
    protected static void initJsonFile(String filePath) {
        List<Note> notes = new ArrayList<>();
        writeObjectsToJson(notes, filePath);
    }

    /**
     * Some text.
     *
     * @param notes     - Some text.
     * @param filePath  - Some text.
     */
    protected static void writeObjectsToJson(List<Note> notes, String filePath) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            objectMapper.writeValue(new File(filePath), notes);
            System.out.println("Write out");
        } catch (IOException e) {
            System.err.println("Didn't write out");
        }

    }

    /**
     * Some text.
     *
     * @param note      - Some text.
     * @param filePath  - Some text.
     */
    protected static void addObjectToJson(Note note, String filePath) {
        List<Note> notes = readObjectsFromJason(filePath);

        if (notes == null) {
            notes = new ArrayList<>();
        }
        notes.add(note);

        writeObjectsToJson(notes, filePath);
    }

}
