package org.example;

import static org.example.JsonReader.readObjectsFromJason;
import static org.example.JsonWriter.initJsonFile;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;




/**
 * Some text.
 */
public class Tests {
    String filePath = "notebook.json";

    @BeforeEach
    void initialization() {
        if (Files.exists(Path.of(filePath))) {
            File file = new File(filePath);
            file.delete();
        }

        if (Files.exists(Path.of(filePath))) {
            initJsonFile(filePath);
        }
    }

    @Test
    void test1() {


        String[] command1 = new String[]{"-add", "Name1", "Body1"};
        CommandParser commandParser = new CommandParser(filePath);
        commandParser.parse(command1);

        String[] command2 = new String[]{"-add", "Name2"};
        commandParser.parse(command2);

        List<Note> notes = readObjectsFromJason(filePath);

        List<String> answer = new ArrayList<>();
        answer.add("Name1");
        answer.add("Name2");

        for (int i = 0; i < answer.size(); i++) {
            assertEquals(notes.get(i).getNoteName(), answer.get(i));
        }
    }


    @Test
    void test2() {


        String[] command1 = new String[]{"-add", "Name1", "Body1"};
        CommandParser commandParser = new CommandParser(filePath);
        commandParser.parse(command1);

        String[] command2 = new String[]{"-add", "Name2"};
        commandParser.parse(command2);

        String[] command3 = new String[]{"-add"};
        commandParser.parse(command3);

        String[] command4 = new String[]{"-add", "fdsadf", "fffdsdf", "fdsadfd"};
        commandParser.parse(command4);

        List<Note> notes = readObjectsFromJason(filePath);

        List<String> answer = new ArrayList<>();
        answer.add("Name1");
        answer.add("Name2");

        for (int i = 0; i < answer.size(); i++) {
            assertEquals(notes.get(i).getNoteName(), answer.get(i));
        }
    }

    @Test
    void test3() {
        CommandParser commandParser = new CommandParser(filePath);
        String[] command0 = new String[]{"-rm", "Name3"};
        commandParser.parse(command0);

        String[] command1 = new String[]{"-add", "Name1", "Body1"};

        commandParser.parse(command1);

        String[] command2 = new String[]{"-add", "Name2"};
        commandParser.parse(command2);

        String[] command3 = new String[]{"-add", "Name3"};
        commandParser.parse(command3);

        String[] command4 = new String[]{"-rm", "Name2"};
        commandParser.parse(command4);

        List<Note> notes = readObjectsFromJason(filePath);

        List<String> answer = new ArrayList<>();
        answer.add("Name1");
        answer.add("Name3");

        for (int i = 0; i < answer.size(); i++) {
            assertEquals(notes.get(i).getNoteName(), answer.get(i));
        }
    }

    PrintStream originalOut = System.out;

    @Test
    void test4() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        CommandParser commandParser = new CommandParser(filePath);
        String[] command0 = new String[]{"-rm", "Name3"};
        commandParser.parse(command0);

        String[] command1 = new String[]{"-add", "Name1", "Body1"};

        commandParser.parse(command1);

        String[] command2 = new String[]{"-add", "Name2"};
        commandParser.parse(command2);

        String[] command3 = new String[]{"-add", "Name3"};
        commandParser.parse(command3);

        String[] command4 = new String[]{"-rm", "Name2"};
        commandParser.parse(command4);

        String[] command5 = new String[]{"-show"};
        new CommandParser(filePath).parse(command5);

        String capturedOutput = outputStream.toString();

        assertTrue(capturedOutput.contains("Write out"));
        assertTrue(capturedOutput.contains("Note name: Name1"));
        assertTrue(capturedOutput.contains("Note name: Name3"));
        assertTrue(capturedOutput.contains("Note content: Body1"));
        assertTrue(capturedOutput.contains("Note content: "));
        System.setOut(originalOut);
    }

    @Test
    void test5() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        String filePathForCurrentTest = "src/test/java/org/example/forTest.json";
        CommandParser commandParser = new CommandParser(filePathForCurrentTest);

        String[] command1 = new String[]{"-show", "02.12.2023 04:15", "02.12.2023 04:25", "word1", "word3"};
        commandParser.parse(command1);


        String capturedOutput = outputStream.toString();

        assertTrue(capturedOutput.contains("Note name: word1 word2 word3"));
        assertTrue(capturedOutput.contains("Note content: "));
        assertTrue(capturedOutput.contains("CreateDate: 2023-12-02 04:22:55"));

        System.setOut(originalOut);
    }
}
