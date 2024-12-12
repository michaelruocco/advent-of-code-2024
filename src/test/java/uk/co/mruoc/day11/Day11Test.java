package uk.co.mruoc.day11;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class Day11Test {

    private static final Stones INPUT = new Stones("125 17");

    private final Blinker blinker = new Blinker();

    @Test
    void shouldBlinkCorrectly() {
        Stones input = new Stones("0 1 10 99 999");

        Stones result = blinker.blink(input);

        assertThat(result.asString()).isEqualTo("1 2024 1 0 9 9 2021976");
    }

    @Test
    void shouldBlinkMultipleTimes() {
        Stones result = blinker.blink(INPUT, 6);

        assertThat(result.asString()).isEqualTo("2097446912 14168 4048 2 0 2 4 40 48 2024 40 48 80 96 2 8 6 7 6 0 3 2");
    }

    @ParameterizedTest
    @MethodSource("inputStonesAndTimesAndExpectedCount")
    void shouldCalculateNumberOfStonesAfterMultipleBlinks(Stones input, int times, long expectedCount) {
        CountingBlinker countingBlinker = new CountingBlinker();

        long count = countingBlinker.blink(input, times);

        assertThat(count).isEqualTo(expectedCount);
    }

    private static Stream<Arguments> inputStonesAndTimesAndExpectedCount() {
        return Stream.of(
                Arguments.of(INPUT, 6, 22L),
                Arguments.of(INPUT, 25, 55312L),
                Arguments.of(new Stones("3935565 31753 437818 7697 5 38 0 123"), 75, 244782991106220L));
    }
}
