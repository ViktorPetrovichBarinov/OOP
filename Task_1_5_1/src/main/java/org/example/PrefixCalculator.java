package org.example;

import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

public class PrefixCalculator {
    private Stack<Operation> operations;
    private LinkedList<Double> numbers;


    public void calculator() {
        while(true) {
            operations = new Stack<>();
            numbers = new LinkedList<>();
            System.out.println("""
                    Enter your expression in 1 string and press "Enter".
                    If you want to exit press "Ctrl + D".
                    """);

            try(Scanner scanner = new Scanner(System.in)) {
                if (!scanner.hasNextLine()) {
                    System.out.println("The end.");
                    break;
                }
                String inputExpression = scanner.nextLine();
                inputExpression = inputExpression.toLowerCase();
                String[] tokens = inputExpression.split("\\s+");
                Integer tokensLength = tokens.length;
                boolean flag = true; // true - операции, false - числа
                for (int i = 0; i < tokensLength; i++) {
                    //смотрим первый символ, если он символ, то предполагаем,
                    // что вся строка это числа и считаем,
                    // что начали рассматривать числа
                    if (Character.isDigit(tokens[i].charAt(0))) {
                        flag = false;
                    }
                    //Если рассматриваем числа
                    if (flag) {
                        operationConditionForParser(tokens[i]);
                    } else {
                        try {
                            Double number = Double.parseDouble(tokens[i]);
                            numbers.add(number);
                        } catch (Exception e) {
                            throw new IllegalArgumentException("Incorrect input expression");
                        }
                    }
                }

                while (!operations.isEmpty()) {
                    Operation currentOperation = operations.pop();
                    switch (currentOperation) {
                        case PLUS:
                            numbers.addFirst(numbers.pop() + numbers.pop());
                            break;
                        case MINUS:
                            numbers.addFirst(numbers.pop() - numbers.pop());
                            break;
                        case MULTIPLICATION:
                            numbers.addFirst(numbers.pop() * numbers.pop());
                            break;
                        case DIVISION:
                            numbers.addFirst(numbers.pop() / numbers.pop());
                            break;
                        case LOGARITHM:
                            double number1 = Math.log(numbers.pop());
                            double number2 = Math.log(numbers.pop());
                            numbers.addFirst(number2 / number1);
                            break;
                        case POWER:
                            numbers.addFirst(Math.pow(numbers.pop(), numbers.pop()));
                            break;
                        case SQUARE_ROOT:
                            numbers.addFirst(Math.sqrt(numbers.pop()));
                            break;
                        case SINE:
                            numbers.addFirst(Math.sin(numbers.pop()));
                            break;
                        case COSINE:
                            numbers.addFirst(Math.cos(numbers.pop()));
                            break;
                    }
                }
                System.out.println(operations);
                System.out.println(numbers);
                System.out.println("result : " + numbers.pop());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void operationConditionForParser(String atom) {
        switch (atom) {
            case "+" -> operations.push(Operation.PLUS);
            case "-" -> operations.push(Operation.MINUS);
            case "*" -> operations.push(Operation.MULTIPLICATION);
            case "/" -> operations.push(Operation.DIVISION);
            case "pow" -> operations.push(Operation.POWER);
            case "log" -> operations.push(Operation.LOGARITHM);
            case "sqrt" -> operations.push(Operation.SQUARE_ROOT);
            case "sin" -> operations.push(Operation.SINE);
            case "cos" -> operations.push(Operation.COSINE);
            default -> throw new IllegalArgumentException("Incorrect input expression");
        }
    }

    enum Operation {
        PLUS,
        MINUS,
        MULTIPLICATION,
        DIVISION,
        LOGARITHM,
        POWER,
        SQUARE_ROOT,
        SINE,
        COSINE
    }

    public static void main(String[] args) {
        PrefixCalculator prefixCalculator = new PrefixCalculator();
        prefixCalculator.calculator();
    }
}
