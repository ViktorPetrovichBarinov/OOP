package org.example;

import groovy.lang.GroovyCodeSource;
import groovy.lang.GroovyShell;

import java.io.File;
import java.util.Collections;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
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
                    source = new GroovyCodeSource(Objects.requireNonNull(
                            Main.class.getResource("/scripts/cloneRepositories.groovy")
                    ));
                    shell.run(source, Collections.singletonList(""));
                    break;
                case "build":
                default:
                    System.out.println("I don't know this command, enter \"help\" for more information.");

            }
        }
    }
}