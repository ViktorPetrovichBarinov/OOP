package org.example;

/**
 * Some text.
 */
public class Main {
    public static void main(String[] args) {
//        PrefixCalculator prefixCalculator = new PrefixCalculator();
//        prefixCalculator.calculator();
        SolidPrefixCalculator.ConsoleExpressionReader expressionReader = new SolidPrefixCalculator.ConsoleExpressionReader();
        SolidPrefixCalculator.OperationFactory operationFactory = new SolidPrefixCalculator.DefaultOperationFactory();

        ExpressionProcessor expressionProcessor = new ExpressionProcessor(expressionReader, operationFactory);
        expressionProcessor.calculate();
    }
}