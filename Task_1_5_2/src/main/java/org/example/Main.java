package org.example;

import java.nio.file.Files;
import java.nio.file.Path;


import static org.example.JsonWriter.initJsonFile;

public class Main {
    public static void main(String[] args) {
        // Указываем кодировку cp1251
        String consoleEncoding = "Cp1251";

        // Устанавливаем кодировку консоли
        System.setProperty("console.encoding", consoleEncoding);
        String filePath = "notebook.json";
        if (!Files.exists(Path.of(filePath))) {
            initJsonFile(filePath);
        }

        CommandParser commandParser = new CommandParser(filePath);
        commandParser.parse(args);
    }
}