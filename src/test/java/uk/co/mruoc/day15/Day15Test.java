package uk.co.mruoc.day15;

import static org.assertj.core.api.Assertions.assertThat;
import static uk.co.mruoc.file.FileLoader.loadContentFromClasspath;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class Day15Test {

    private static final String EXAMPLE_1_PATH = "day-15/example-1.txt";
    private static final String EXAMPLE_2_PATH = "day-15/example-2.txt";

    @ParameterizedTest
    @MethodSource("pathAndExpectedState")
    void shouldExecuteExamples(String path, String expectedState) {
        WarehouseMap map = new WarehouseMapLoader().load(path);
        Directions directions = new DirectionsLoader().load(path);

        map.move(directions);

        assertThat(map.getState()).isEqualToIgnoringWhitespace(expectedState);
    }

    @ParameterizedTest
    @MethodSource("pathAndExpectedGPS")
    void shouldExecuteExamples(String path, int expectedGPS) {
        WarehouseMap map = new WarehouseMapLoader().load(path);
        Directions directions = new DirectionsLoader().load(path);

        map.move(directions);

        assertThat(map.sumOfAllBoxesGPS()).isEqualTo(expectedGPS);
    }

    private static Stream<Arguments> pathAndExpectedState() {
        return Stream.of(
                Arguments.of(EXAMPLE_1_PATH, loadContentFromClasspath("day-15/example-1-expected-state.txt")),
                Arguments.of(EXAMPLE_2_PATH, loadContentFromClasspath("day-15/example-2-expected-state.txt")));
    }

    private static Stream<Arguments> pathAndExpectedGPS() {
        return Stream.of(
                Arguments.of(EXAMPLE_1_PATH, 2028),
                Arguments.of(EXAMPLE_2_PATH, 10092),
                Arguments.of("day-15/puzzle.txt", 1415498));
    }
}
