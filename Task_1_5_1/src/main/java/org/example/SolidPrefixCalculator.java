package org.example;



import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

/**
 * Some text.
 */
public class SolidPrefixCalculator {
    interface Operation {
        double perform(double... args);
    }

    static class Addition implements Operation {
        @Override
        public double perform(double... args) {
            if (args.length != 2) {
                throw new IllegalArgumentException("Incorrect number of arguments");
            } else {
                return args[0] + args[1];
            }
        }
    }

    static class Subtraction implements Operation {
        @Override
        public double perform(double... args) {
            if (args.length != 2) {
                throw new IllegalArgumentException("Incorrect number of arguments");
            } else {
                return args[0] - args[1];
            }
        }
    }

    static class Multiplication implements Operation {
        @Override
        public double perform(double... args) {
            if (args.length != 2) {
                throw new IllegalArgumentException("Incorrect number of arguments");
            } else {
                return args[0] * args[1];
            }
        }
    }

    static class Division implements Operation {
        @Override
        public double perform(double... args) {
            if (args.length != 2) {
                throw new IllegalArgumentException("Incorrect number of arguments");
            } else {
                if (args[1] == 0) {
                    throw new IllegalArgumentException("Division by 0");
                }
                return args[0] / args[1];
            }
        }
    }

    static class Power implements Operation {
        @Override
        public double perform(double... args) {
            if (args.length != 2) {
                throw new IllegalArgumentException("Incorrect number of arguments");
            } else {
                return Math.pow(args[0], args[1]);
            }
        }
    }

    static class Logarithm implements Operation {
        @Override
        public double perform(double... args) {
            if (args.length != 2) {
                throw new IllegalArgumentException("Incorrect number of arguments");
            } else {
                double a = args[0];
                double b = args[1];

                if (a <= 0 || a == 1 || b <= 0) {
                    throw new IllegalArgumentException("Logarithm constraints are not satisfied");
                }
                return Math.log10(b) / Math.log10(a);
            }
        }
    }

    static class SquareRoot implements Operation {
        @Override
        public double perform(double... args) {
            if (args.length != 1) {
                throw new IllegalArgumentException("Incorrect number of arguments");
            } else {
                return Math.sqrt(args[0]);
            }
        }
    }

    static class Sine implements Operation {
        @Override
        public double perform(double... args) {
            if (args.length != 1) {
                throw new IllegalArgumentException("Incorrect number of arguments");
            } else {
                return Math.sin(args[0]);
            }
        }
    }

    static class Cosine implements Operation {
        @Override
        public double perform(double... args) {
            if (args.length != 1) {
                throw new IllegalArgumentException("Incorrect number of arguments");
            } else {
                return Math.cos(args[0]);
            }
        }
    }

    interface OperationFactory {
        Operation createOperation(String operator);
    }

    static class DefaultOperationFactory implements OperationFactory {
        @Override
        public Operation createOperation(String operator) {
            switch (operator) {
                case "+":
                    return new Addition();
                case "-":
                    return new Subtraction();
                case "*":
                    return new Multiplication();
                case "/":
                    return new Division();
                case "pow":
                    return new Power();
                case "log":
                    return new Logarithm();
                case "sin":
                    return new Sine();
                case "cos":
                    return new Cosine();
                case "sqrt":
                    return new SquareRoot();

                // Добавь остальные операции
                default:
                    throw new IllegalArgumentException("Unsupported operation: " + operator);
            }
        }
    }

    interface ExpressionReader {
        String readExpression();
    }

    static class ConsoleExpressionReader implements ExpressionReader {
        private Scanner scanner = new Scanner(System.in);

        @Override
        public String readExpression() {
            System.out.print("Enter your expression in 1 string and press \"Enter\".\n"
                    + "If you want to exit press \"Ctrl + D\".\n");
            if (scanner.hasNextLine()) {
                return scanner.nextLine().toLowerCase();
            }
            return null;
        }
    }

}

/**
 * Some text.
 */
class ExpressionProcessor {
    private Stack<SolidPrefixCalculator.Operation> operations;
    private LinkedList<Double> numbers = new LinkedList<>();
    private SolidPrefixCalculator.ExpressionReader expressionReader;
    private SolidPrefixCalculator.OperationFactory operationFactory;

    public ExpressionProcessor(
            SolidPrefixCalculator.ExpressionReader expressionReader,
            SolidPrefixCalculator.OperationFactory operationFactory) {
        this.expressionReader = expressionReader;
        this.operationFactory = operationFactory;
        this.operations = new Stack<>();
        this.numbers = new LinkedList<>();
    }

    public void calculate() {
        while (true) {
            String inputExpression = expressionReader.readExpression();
            if (inputExpression == null) {
                System.out.print("The end.\n");
                break;
            }
            processExpression(inputExpression);
        }
    }

    private void processExpression(String inputExpression) {
        String[] tokens = inputExpression.split("\\s+");
        boolean flag = true; // true - операции, false - числа
        for (String token : tokens) {
            if (Character.isDigit(token.charAt(0))) {
                flag = false;
            }
            if (flag) {
                operationConditionForParser(token);
            } else {
                Double number = Double.parseDouble(token);
                numbers.add(number);
            }
        }

        calculateAnswer();
        System.out.print("result : " + numbers.pop() + '\n');
    }

    private void operationConditionForParser(String atom) {
        SolidPrefixCalculator.Operation operation = operationFactory.createOperation(atom);
        operations.push(operation);
    }

    private void calculateAnswer() {
        while (!operations.isEmpty()) {
            SolidPrefixCalculator.Operation currentOperation = operations.pop();
            if (currentOperation instanceof SolidPrefixCalculator.Addition
                    || currentOperation instanceof SolidPrefixCalculator.Subtraction
                    || currentOperation instanceof SolidPrefixCalculator.Multiplication
                    || currentOperation instanceof SolidPrefixCalculator.Division
                    || currentOperation instanceof SolidPrefixCalculator.Logarithm
                    || currentOperation instanceof SolidPrefixCalculator.Power) {
                double operand1 = numbers.pop();
                double operand2 = numbers.pop();
                double result = currentOperation.perform(operand1, operand2);
                numbers.addFirst(result);
            }

            if (currentOperation instanceof SolidPrefixCalculator.SquareRoot
                || currentOperation instanceof SolidPrefixCalculator.Sine
                || currentOperation instanceof SolidPrefixCalculator.Cosine) {
                double operand1 = numbers.pop();
                double result = currentOperation.perform(operand1);
                numbers.addFirst(result);
            }
        }
    }
}

