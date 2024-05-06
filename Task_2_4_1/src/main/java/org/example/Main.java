package org.example;

import groovy.lang.Binding;
import groovy.lang.GroovyCodeSource;
import groovy.lang.GroovyShell;
import groovy.util.GroovyScriptEngine;
import groovy.util.ResourceException;
import groovy.util.ScriptException;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, ScriptException, ResourceException {
        String commands = """
                    \"exit\" - stop working
                    \"help\" - calling help
                    \"clone\" - clone repositories
                    \"build\" - build checker""";
        System.out.println("Hello!\n" +
                "This is Task_2_4_1 laboratory work.\n"
                + commands);

        GroovyShell shell = new GroovyShell();
        GroovyCodeSource source;
        Scanner scanner = new Scanner(System.in);
        while(true) {
            String input = scanner.nextLine();
            switch (input) {
                case "exit":
                    System.out.println("Bye, bye!");
                    System.exit(0);
                case "help":
                    System.out.println(commands);
                    break;
                case "clone":
                    try {
                        File directory = new File(".");
                        File[] files = directory.listFiles();
                        if (files != null) {
                            for (File file : files) {
                                System.out.println(file.getName());
                            }
                        }


                        source = new GroovyCodeSource(Objects.requireNonNull(
                                Main.class.getResource("/scripts/cloneRepositories.groovy")
                        ));
                        shell.run(source, Collections.singletonList(""));

//                        GroovyScriptEngine engine = new GroovyScriptEngine("C:\\Users\\chydi\\IdeaProjects\\OOP\\Task_2_4_1\\build\\resources\\main\\scripts");
//                        Binding binding = new Binding();
//                        engine.run("cloneRepositories.groovy", binding);
                    } catch (Exception e){
                        e.printStackTrace();
                    }

                    break;
                case "build":
                default:
                    System.out.println("I don't know this command, enter \"help\" for more information.");

            }
        }
    }
}