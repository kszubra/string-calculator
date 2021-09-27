package com.example.string.calculator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;


@Service
@Slf4j
public class DefaultTestCalculator implements StringCalculator {

    @Override
    public int add(String input) {
        log.info("Summing input: {}", input);
        List<Integer> negativeNumbers = new ArrayList<>();

        String[] numbers = NumberExtractorFactory.getNumberExtractor(input).apply(input);

        int result = Stream.of(numbers)
                .filter(this::isNumber)
                .map(String::trim)
                .map(Integer::parseInt)
                .filter(this::isBelowMaxValue)
                .peek(number -> {
                    if(number < 0) {
                        negativeNumbers.add(number);
                    }
                })
                .mapToInt(Integer::intValue)
                .sum();

        if(!negativeNumbers.isEmpty()) {
            log.info("Negative numbers: {}", negativeNumbers);
            throw new NegativeNumberException(negativeNumbers.toString());
        }

        log.info("Calculated result: {}", result);
        return result;
    }

    private Boolean isBelowMaxValue(Integer value) {
        return value <= StringCalculatorMapping.CALCULATOR_MAX_VALUE;
    }

    private Boolean isNumber(String maybeNumber) {
        if (maybeNumber == null) {
            return false;
        }
        return StringCalculatorMapping.NUMERIC_PATTERN.matcher(maybeNumber).matches();
    }
}
