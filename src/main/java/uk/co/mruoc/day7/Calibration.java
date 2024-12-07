package uk.co.mruoc.day7;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Calibration {

    @Getter
    private final long testValue;

    private final List<Long> numbers;
    private final Collection<Character> operators;

    public boolean couldBeTrue() {
        return generateCombinations(numbers.size(), operators).stream()
                .map(this::evaluate)
                .anyMatch(result -> result == testValue);
    }

    private long evaluate(String combination) {
        long result = numbers.get(0);
        for (int i = 0; i < combination.length() - 1; i++) {
            char operator = combination.charAt(i);
            long number = numbers.get(i + 1);
            if (operator == '+') {
                result += number;
            } else if (operator == '*') {
                result *= number;
            } else if (operator == '|') {
                result = Long.parseLong(String.valueOf(result) + number);
            }
        }
        return result;
    }

    private static Collection<String> generateCombinations(int length, Collection<Character> operators) {
        if (length == 0) {
            return Collections.singletonList("");
        }
        Collection<String> shorterCombinations = generateCombinations(length - 1, operators);
        Collection<String> combinations = new ArrayList<>();
        for (String combination : shorterCombinations) {
            operators.forEach(operator -> combinations.add(combination + operator));
        }
        return combinations;
    }
}
