package org.example;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import org.codehaus.groovy.control.CompilationFailedException;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ConfigReader {
    private final Map<String, Map<String, String>> students;

    public ConfigReader() {
        this.students = new HashMap<>();
    }

    public void readConfig(String filePath) {
        Binding binding = new Binding();
        binding.setVariable("students", students);
        GroovyShell shell = new GroovyShell(binding);

        try {
            shell.evaluate(new File(filePath));
        } catch (IOException e) {
            System.out.println("не получилось");
        }
    }

    public Map<String, String> getStudentInfo(String studentId) {
        return students.get(studentId);
    }

    public static void main(String[] args) {
        ConfigReader configReader = new ConfigReader();
        configReader.readConfig("src/main/resources/students.groovy");

        // Получим информацию о студенте "student1"
        Map<String, String> student1Info = configReader.getStudentInfo("student1");
        if (student1Info != null) {
            System.out.println("Student 1:");
            System.out.println("Username: " + student1Info.get("username"));
            System.out.println("Repository: " + student1Info.get("repository"));
        } else {
            System.out.println("Student 1 not found in configuration.");
        }
    }
}
