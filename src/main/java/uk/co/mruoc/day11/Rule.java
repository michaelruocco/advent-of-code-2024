package uk.co.mruoc.day11;

import java.util.Arrays;
import java.util.Collection;
import java.util.function.LongFunction;

public class Rule implements LongFunction<Collection<Long>> {

    @Override
    public Collection<Long> apply(long stone) {
        return Arrays.stream(blink(stone)).boxed().toList();
    }

    public long[] blink(long stone) {
        if (stone == 0) {
            return new long[] {1};
        }

        var digits = numberOfDigits(stone);
        if (isOdd(digits)) {
            return new long[] {stone * 2024};
        }

        return split(stone, digits);
    }

    private static int numberOfDigits(long i) {
        return (int) (Math.log10(i) + 1);
    }

    private static boolean isOdd(long i) {
        return (i & 1) != 0;
    }

    private static long[] split(long i, long digits) {
        var halfDigits = digits / 2;
        var left = i / (long) Math.pow(10, halfDigits);
        var right = i % (long) Math.pow(10, halfDigits);
        return new long[] {left, right};
    }
}
