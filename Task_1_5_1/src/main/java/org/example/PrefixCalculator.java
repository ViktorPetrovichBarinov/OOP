package org.example;

import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;


/**
 * Some text.
 */
public class PrefixCalculator {
    private Stack<Operation> operations;
    private LinkedList<Double> numbers = new LinkedList<>();
    Scanner scanner = new Scanner(System.in);



    /**
     * Some text.
     */
    public void calculator() {
        while (true) {
            operations = new Stack<>();
            numbers = new LinkedList<>();
            System.out.print("Enter your expression in 1 string and press \"Enter\".\n"
                   + "If you want to exit press \"Ctrl + D\".\n");

            try {
                if (!scanner.hasNextLine()) {
                    System.out.print("The end.\n");
                    break;
                }
                String inputExpression = scanner.nextLine();
                inputExpression = inputExpression.toLowerCase();
                String[] tokens = inputExpression.split("\\s+");
                boolean flag = true; // true - операции, false - числа
                for (String token : tokens) {
                    //смотрим первый символ, если он символ, то предполагаем,
                    // что вся строка это числа и считаем,
                    // что начали рассматривать числа
                    if (Character.isDigit(token.charAt(0))) {
                        flag = false;
                    }
                    //Если рассматриваем числа
                    if (flag) {
                        operationConditionForParser(token);
                    } else {
                        Double number = Double.parseDouble(token);
                        numbers.add(number);
                    }
                }

                calculateAnswer();
                System.out.print("result : " + numbers.pop() + '\n');
            } catch (Exception e) {
                System.out.print("Incorrect input expression\n");
            }
        }
    }

    private void operationConditionForParser(String atom) {
        switch (atom) {
            case "+":
                operations.push(Operation.PLUS);
                break;
            case "-":
                operations.push(Operation.MINUS);
                break;
            case "*":
                operations.push(Operation.MULTIPLICATION);
                break;
            case "/":
                operations.push(Operation.DIVISION);
                break;
            case "pow":
                operations.push(Operation.POWER);
                break;
            case "log":
                operations.push(Operation.LOGARITHM);
                break;
            case "sqrt":
                operations.push(Operation.SQUARE_ROOT);
                break;
            case "sin":
                operations.push(Operation.SINE);
                break;
            case "cos":
                operations.push(Operation.COSINE);
                break;
            default: throw new IllegalArgumentException("Incorrect input expression");
        }
    }

    private void calculateAnswer() {
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
                default:
                    throw new RuntimeException();
            }
        }
        if (numbers.size() != 1) {
            throw new IllegalArgumentException();
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
}
