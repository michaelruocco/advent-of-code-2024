package uk.co.mruoc.day24;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class Day24Test {

    private final WiredGatesLoader loader = new WiredGatesLoader();

    @ParameterizedTest
    @MethodSource("pathAndExpectedDecimalValue")
    void shouldReturnExpectedDecimalNumber(String path, long expectedResult) {
        Wires wires = loader.loadWires(path);
        Gates gates = loader.loadGates(path);

        gates.execute(wires);

        assertThat(wires.getDecimalValue('z')).isEqualTo(expectedResult);
    }

    @ParameterizedTest
    @MethodSource("pathAndExpectedFaultyGates")
    void shouldReturnFaultyGates(String path, String expectedResult) {
        Gates gates = loader.loadGates(path);

        String result = gates.findFaulty();

        assertThat(result).isEqualTo(expectedResult);
    }

    private static Stream<Arguments> pathAndExpectedDecimalValue() {
        return Stream.of(
                Arguments.of("day-24/example-1.txt", 4L),
                Arguments.of("day-24/example-2.txt", 2024L),
                Arguments.of("day-24/puzzle.txt", 56939028423824L));
    }

    private static Stream<Arguments> pathAndExpectedFaultyGates() {
        return Stream.of(Arguments.of("day-24/puzzle.txt", "frn,gmq,vtj,wnf,wtt,z05,z21,z39"));
    }
}
