package org.example;

import java.nio.file.Files;
import java.nio.file.Path;


import static org.example.JsonWriter.initJsonFile;

public class Main {
    public static void main(String[] args) {
        String filePath = "notebook.json";
        if (!Files.exists(Path.of(filePath))) {
            initJsonFile(filePath);
        }

        CommandParser commandParser = new CommandParser(filePath);
        commandParser.parse(args);
    }
}