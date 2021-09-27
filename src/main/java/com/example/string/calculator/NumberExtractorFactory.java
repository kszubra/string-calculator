package com.example.string.calculator;

import java.util.Objects;
import java.util.function.Function;

public class NumberExtractorFactory {

    public static Function<String, String[]> getNumberExtractor(String input) {
        if(Objects.nonNull(input) && input.startsWith(StringCalculatorMapping.SLASH_OPENING)) {
            return customDelimiterNumberExtractor;
        } else {
            return declaredDelimiterNumberExtractor;
        }
    }

    private static final Function<String, String[]> declaredDelimiterNumberExtractor = input -> {
        if(Objects.nonNull(input)) {
            String string = input.replaceAll(StringCalculatorMapping.WHITESPACE, StringCalculatorMapping.BLANK);
            for(String delimiter : StringCalculatorMapping.DECLARED_DELIMITERS) {
                string = string.replaceAll(delimiter, StringCalculatorMapping.COMMA_DELIMITER);
            }
            return string.split(StringCalculatorMapping.COMMA_DELIMITER);
        } else {
            return new String[0];
        }
    };

    private static final Function<String, String[]> customDelimiterNumberExtractor = input -> {
        String numbers = input.replaceAll(StringCalculatorMapping.SLASH_OPENING, StringCalculatorMapping.BLANK)
                .split(StringCalculatorMapping.NEW_LINE)[1];
        numbers = numbers.replaceAll(StringCalculatorMapping.MINUS_CHARACTER, StringCalculatorMapping.NEGATIVE_PLACEHOLDER);
        numbers = numbers.replaceAll(StringCalculatorMapping.SPECIAL_CHARACTERS_REGEX, StringCalculatorMapping.COMMA_DELIMITER);
        numbers = numbers.replaceAll(StringCalculatorMapping.NEGATIVE_PLACEHOLDER, StringCalculatorMapping.MINUS_CHARACTER);
        numbers = numbers.replaceAll(StringCalculatorMapping.NON_NUMERIC_CHARACTERS_REGEX, StringCalculatorMapping.COMMA_DELIMITER);
        return numbers.split(StringCalculatorMapping.COMMA_DELIMITER);
    };
}
