package org.example;

public class SimplicityChecker {

    public static boolean isNumberSimple(long num) {
        if (num <= 1) {
            return false;
        }
        double squareRoot = Math.sqrt(num);
        for (int i = 2; i < squareRoot; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}
