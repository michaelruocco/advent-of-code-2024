package uk.co.mruoc.day3;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MulCalculator {

    private static final String REGEX = "mul\\((\\d{1,3}),(\\d{1,3})\\)|(do\\(\\))|(don't\\(\\))";
    private static final Pattern PATTERN = Pattern.compile(REGEX);

    public int calculate(String input) {
        Matcher matcher = PATTERN.matcher(input);

        int currentIndex = 0;
        boolean enabled = true;
        int result = 0;

        while (matcher.find(currentIndex)) {
            String instruction = matcher.group();
            if (isDo(instruction)) {
                enabled = true;
            } else if (isDont(instruction)) {
                enabled = false;
            } else if (enabled) {
                result += calculateMul(matcher);
            }
            currentIndex = matcher.end();
        }

        return result;
    }

    private static boolean isDo(String instruction) {
        return instruction.equals("do()");
    }

    private static boolean isDont(String instruction) {
        return instruction.equals("don't()");
    }

    private static int calculateMul(Matcher matcher) {
        int multiplicand = Integer.parseInt(matcher.group(1));
        int multiplier = Integer.parseInt(matcher.group(2));
        return multiplicand * multiplier;
    }
}
