package org.example;

import groovy.lang.Binding;
import groovy.util.GroovyScriptEngine;

import java.util.Scanner;

/**

 */
public class Parser {
    public static void start() {
        String commands = """
                    \"exit\" - stop working
                    \"help\" - calling help
                    \"clone\" - clone repositories
                    \"build\" - build checker
                    \"html\" - generate html result""";
        System.out.println("Hello!\n"
                + "This is Task_2_4_1 laboratory work.\n"
                + commands);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            switch (input) {
                case "exit":
                    System.out.println("Bye, bye!");
                    System.exit(0);
                    break;
                case "help":
                    System.out.println(commands);
                    break;
                case "clone":
                    try {
                        GroovyScriptEngine engine =
                                new GroovyScriptEngine("src/main/resources/scripts");
                        Binding binding = new Binding();
                        engine.run("cloneRepositories.groovy", binding);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case "build":
                    try {
                        GroovyScriptEngine engine =
                                new GroovyScriptEngine("src/main/resources/scripts");
                        Binding binding = new Binding();
                        engine.run("buildChecker.groovy", binding);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case "html":
                    try {
                        GroovyScriptEngine engine =
                                new GroovyScriptEngine("src/main/resources/scripts");
                        Binding binding = new Binding();
                        engine.run("HTMLWrite.groovy", binding);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    System.out.println("I don't know this command,"
                            + " enter \"help\" for more information.");
            }
        }
    }
}
