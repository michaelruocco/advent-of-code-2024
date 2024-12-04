package uk.co.mruoc.day3;

import static org.assertj.core.api.Assertions.assertThat;
import static uk.co.mruoc.file.FileLoader.loadContentFromClasspath;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class Day3Test {

    private final MulCalculator calculator = new MulCalculator();

    @ParameterizedTest
    @MethodSource("inputAndExpectedResult")
    void shouldCalculateDistanceCorrectly(String input, int expectedResult) {
        int result = calculator.calculate(input);

        assertThat(result).isEqualTo(expectedResult);
    }

    private static Stream<Arguments> inputAndExpectedResult() {
        return Stream.of(
                Arguments.of(loadContentFromClasspath("day-3/example-mul.txt"), 161),
                Arguments.of(loadContentFromClasspath("day-3/example-undo-mul.txt"), 48),
                Arguments.of(loadContentFromClasspath("day-3/mul.txt"), 100189366));
    }
}
