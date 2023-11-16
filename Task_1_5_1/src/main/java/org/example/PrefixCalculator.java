package org.example;

import java.util.Scanner;

public class PrefixCalculator {

    public static void calculator() {
        System.out.println("Enter your expression in 1 string and press \"Enter\"");
        Scanner scanner = new Scanner(System.in);
        String inputText = scanner.nextLine();
        System.out.println("Вы ввели: " + inputText);
        scanner.close();


    }

    public static void main(String[] args) {
        calculator();
    }
}
