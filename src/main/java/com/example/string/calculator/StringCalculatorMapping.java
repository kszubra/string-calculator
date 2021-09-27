package com.example.string.calculator;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class StringCalculatorMapping {

    public final static String COMMA_DELIMITER = ",";
    public final static String NEW_LINE = "\\n";
    public final static String SLASH_OPENING = "//";
    public final static List<String> DECLARED_DELIMITERS = Arrays.asList(NEW_LINE);
    public final static String WHITESPACE = " ";
    public final static String BLANK = "";
    public final static Pattern NUMERIC_PATTERN = Pattern.compile("-?\\d+(\\.\\d+)?");
    public final static String SPECIAL_CHARACTERS_REGEX = "[^a-zA-Z0-9]";
    public final static String NON_NUMERIC_CHARACTERS_REGEX = "[a-zA-Z]";
    public final static String NEGATIVE_PLACEHOLDER = "NEGATIVE";
    public final static String MINUS_CHARACTER = "-";
    public final static int CALCULATOR_MAX_VALUE = 1000;
    public final static String NEGATIVE_NUMBER_MESSAGE = "Negatives not allowed: %s";
}
