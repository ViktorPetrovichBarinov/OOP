package org.example.PrimeCalculators;

public class NonNaturalNumberException extends Exception{
    NonNaturalNumberException(){
        super("Number is not a natural number");
    }
    public NonNaturalNumberException(String message) {
        super(message);
    }
}
