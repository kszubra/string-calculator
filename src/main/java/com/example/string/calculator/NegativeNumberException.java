package com.example.string.calculator;

public class NegativeNumberException extends RuntimeException {

    public NegativeNumberException(String message) {
        super(String.format(StringCalculatorMapping.NEGATIVE_NUMBER_MESSAGE, message));
    }
}
