package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


/**
 * Some text.
 */
public class PrefixCalculatorTest {

    @Test
    @DisplayName("Default test")
    void test1() {
        String inputString = "sin + - 1 2 1\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(inputString.getBytes());
        System.setIn(inputStream);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(byteArrayOutputStream);
        System.setOut(printStream);

        PrefixCalculator prefixCalculator = new PrefixCalculator();
        prefixCalculator.calculator();
        String outputString = byteArrayOutputStream.toString();
        String answer = """
Enter your expression in 1 string and press "Enter".
If you want to exit press "Ctrl + D".
result : 0.0
Enter your expression in 1 string and press "Enter".
If you want to exit press "Ctrl + D".
The end.
            """;
        assertEquals(answer, outputString);

        System.setIn(System.in);
        System.setOut(new PrintStream(System.out));

    }

    @Test
    @DisplayName("Operations more then numbers")
    void test2() {
        String inputString = "sin + - 2 1\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(inputString.getBytes());
        System.setIn(inputStream);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(byteArrayOutputStream);
        System.setOut(printStream);

        PrefixCalculator prefixCalculator = new PrefixCalculator();
        prefixCalculator.calculator();
        String outputString = byteArrayOutputStream.toString();
        String answer = """
Enter your expression in 1 string and press "Enter".
If you want to exit press "Ctrl + D".
Incorrect input expression
Enter your expression in 1 string and press "Enter".
If you want to exit press "Ctrl + D".
The end.
            """;
        assertEquals(answer, outputString);

        System.setIn(System.in);
        System.setOut(new PrintStream(System.out));

    }

    @Test
    @DisplayName("Numbers more then operations")
    void test3() {
        String inputString = "+ 1 2 3 4\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(inputString.getBytes());
        System.setIn(inputStream);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(byteArrayOutputStream);
        System.setOut(printStream);

        PrefixCalculator prefixCalculator = new PrefixCalculator();
        prefixCalculator.calculator();
        String outputString = byteArrayOutputStream.toString();
        String answer = """
Enter your expression in 1 string and press "Enter".
If you want to exit press "Ctrl + D".
Incorrect input expression
Enter your expression in 1 string and press "Enter".
If you want to exit press "Ctrl + D".
The end.
            """;
        assertEquals(answer, outputString);

        System.setIn(System.in);
        System.setOut(new PrintStream(System.out));

    }

    @Test
    @DisplayName("Operations then numbers then operations")
    void test4() {
        String inputString = "pow log sin 3 2 1 1 2 3 + - \n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(inputString.getBytes());
        System.setIn(inputStream);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(byteArrayOutputStream);
        System.setOut(printStream);

        PrefixCalculator prefixCalculator = new PrefixCalculator();
        prefixCalculator.calculator();
        String outputString = byteArrayOutputStream.toString();
        String answer = """
Enter your expression in 1 string and press "Enter".
If you want to exit press "Ctrl + D".
Incorrect input expression
Enter your expression in 1 string and press "Enter".
If you want to exit press "Ctrl + D".
The end.
            """;
        assertEquals(answer, outputString);

        System.setIn(System.in);
        System.setOut(new PrintStream(System.out));

    }

    @Test
    @DisplayName("Division and multiplication")
    void test5() {
        String inputString = "* / 6 3 7\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(inputString.getBytes());
        System.setIn(inputStream);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(byteArrayOutputStream);
        System.setOut(printStream);

        PrefixCalculator prefixCalculator = new PrefixCalculator();
        prefixCalculator.calculator();
        String outputString = byteArrayOutputStream.toString();
        String answer = """
Enter your expression in 1 string and press "Enter".
If you want to exit press "Ctrl + D".
result : 14.0
Enter your expression in 1 string and press "Enter".
If you want to exit press "Ctrl + D".
The end.
            """;
        assertEquals(answer, outputString);

        System.setIn(System.in);
        System.setOut(new PrintStream(System.out));

    }

    @Test
    @DisplayName("sin cos")
    void test6() {
        String inputString = """
                sin 3.14
                cos 3.14
                """;
        ByteArrayInputStream inputStream = new ByteArrayInputStream(inputString.getBytes());
        System.setIn(inputStream);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(byteArrayOutputStream);
        System.setOut(printStream);

        PrefixCalculator prefixCalculator = new PrefixCalculator();
        prefixCalculator.calculator();
        String outputString = byteArrayOutputStream.toString();
        String answer = """
Enter your expression in 1 string and press "Enter".
If you want to exit press "Ctrl + D".
result : 0.0015926529164868282
Enter your expression in 1 string and press "Enter".
If you want to exit press "Ctrl + D".
result : -0.9999987317275395
Enter your expression in 1 string and press "Enter".
If you want to exit press "Ctrl + D".
The end.
            """;
        assertEquals(answer, outputString);

        System.setIn(System.in);
        System.setOut(new PrintStream(System.out));

    }

    @Test
    @DisplayName("pow sqrt")
    void test7() {
        String inputString = """
                sqrt pow 5 2
                log 2 8
                """;
        ByteArrayInputStream inputStream = new ByteArrayInputStream(inputString.getBytes());
        System.setIn(inputStream);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(byteArrayOutputStream);
        System.setOut(printStream);

        PrefixCalculator prefixCalculator = new PrefixCalculator();
        prefixCalculator.calculator();
        String outputString = byteArrayOutputStream.toString();
        String answer = """
Enter your expression in 1 string and press "Enter".
If you want to exit press "Ctrl + D".
result : 5.0
Enter your expression in 1 string and press "Enter".
If you want to exit press "Ctrl + D".
result : 3.0
Enter your expression in 1 string and press "Enter".
If you want to exit press "Ctrl + D".
The end.
            """;
        assertEquals(answer, outputString);

        System.setIn(System.in);
        System.setOut(new PrintStream(System.out));

    }

    @Test
    @DisplayName("incorrect input then correct input")
    void test8() {
        String inputString = "sqrt pow 5 2 7 8 32.2 88 1\n" +
                "log 2 8\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(inputString.getBytes());
        System.setIn(inputStream);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(byteArrayOutputStream);
        System.setOut(printStream);

        PrefixCalculator prefixCalculator = new PrefixCalculator();
        prefixCalculator.calculator();
        String outputString = byteArrayOutputStream.toString();
        String answer = """
Enter your expression in 1 string and press "Enter".
If you want to exit press "Ctrl + D".
Incorrect input expression
Enter your expression in 1 string and press "Enter".
If you want to exit press "Ctrl + D".
result : 3.0
Enter your expression in 1 string and press "Enter".
If you want to exit press "Ctrl + D".
The end.
            """;
        assertEquals(answer, outputString);

        System.setIn(System.in);
        System.setOut(new PrintStream(System.out));

    }
}
