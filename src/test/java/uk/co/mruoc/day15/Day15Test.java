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
    private static final String EXAMPLE_3_PATH = "day-15/example-3.txt";
    private static final String PUZZLE_PATH = "day-15/puzzle.txt";

    @ParameterizedTest
    @MethodSource("pathAndScaleAndExpectedState")
    void shouldExecuteExamples(String path, int scale, String expectedState) {
        WarehouseMap map = new WarehouseMapLoader().load(path, scale);
        Directions directions = new DirectionsLoader().load(path);

        map.move(directions);

        assertThat(map.getState()).isEqualToIgnoringWhitespace(expectedState);
    }

    @ParameterizedTest
    @MethodSource("pathAndScaleAndExpectedGPS")
    void shouldCalculateGPS(String path, int scale, int expectedGPS) {
        WarehouseMap map = new WarehouseMapLoader().load(path, scale);
        Directions directions = new DirectionsLoader().load(path);

        map.move(directions);

        assertThat(map.sumOfAllBoxesGPS()).isEqualTo(expectedGPS);
    }

    private static Stream<Arguments> pathAndScaleAndExpectedState() {
        return Stream.of(
                Arguments.of(EXAMPLE_1_PATH, 1, loadContentFromClasspath("day-15/example-1-expected-state.txt")),
                Arguments.of(
                        EXAMPLE_2_PATH, 1, loadContentFromClasspath("day-15/example-2-part-1-end-expected-state.txt")),
                Arguments.of(EXAMPLE_3_PATH, 2, loadContentFromClasspath("day-15/example-3-expected-state.txt")),
                Arguments.of(
                        EXAMPLE_2_PATH, 2, loadContentFromClasspath("day-15/example-2-part-2-end-expected-state.txt")));
    }

    private static Stream<Arguments> pathAndScaleAndExpectedGPS() {
        return Stream.of(
                Arguments.of(EXAMPLE_1_PATH, 1, 2028),
                Arguments.of(EXAMPLE_2_PATH, 1, 10092),
                Arguments.of(PUZZLE_PATH, 1, 1415498),
                Arguments.of(PUZZLE_PATH, 2, 1432898));
    }
}
