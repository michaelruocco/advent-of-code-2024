package uk.co.mruoc.day14;

import static org.assertj.core.api.Assertions.assertThat;
import static uk.co.mruoc.file.FileLoader.loadContentFromClasspath;

import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class Day14Test {

    private static final String EXAMPLE_1_PATH = "day-14/example-1.txt";
    private static final String PUZZLE_PATH = "day-14/puzzle.txt";
    private static final int SECONDS = 100;

    @Test
    void shouldCalculateStateForPart1Example() {
        RestroomMap map = new ExampleRestroomMap();
        RobotLoader loader = new RobotLoader(map);
        Robots robots = loader.load(EXAMPLE_1_PATH);
        robots.setInitialLocations();

        robots.move(SECONDS);

        assertThat(map.getState())
                .isEqualTo(
                        """
                ......2..1.
                ...........
                1..........
                .11........
                .....1.....
                ...12......
                .1....1....
                """);
    }

    @ParameterizedTest
    @MethodSource("pathAndMapAndExpectedSafetyFactor")
    void shouldCalculateSafetyFactorForPart1Example(String path, RestroomMap map, int expectedSafetyFactor) {
        RobotLoader loader = new RobotLoader(map);
        Robots robots = loader.load(path);
        robots.setInitialLocations();

        robots.move(SECONDS);

        assertThat(map.getSafetyFactor()).isEqualTo(expectedSafetyFactor);
    }

    @Test
    void shouldCalculateStateForPart2() {
        RestroomMap map = new PuzzleRestroomMap();
        RobotLoader loader = new RobotLoader(map);
        Robots robots = loader.load(PUZZLE_PATH);
        robots.setInitialLocations();

        int count = 0;
        while (!map.containsHorizontalLineWithMinLength(10)) {
            count++;
            robots.move(1);
        }

        assertThat(count).isEqualTo(6532);
        assertThat(map.getState())
                .isEqualToIgnoringWhitespace(loadContentFromClasspath("day-14/expected-tree-map.txt"));
    }

    private static Stream<Arguments> pathAndMapAndExpectedSafetyFactor() {
        return Stream.of(
                Arguments.of(EXAMPLE_1_PATH, new ExampleRestroomMap(), 12),
                Arguments.of(PUZZLE_PATH, new PuzzleRestroomMap(), 230900224));
    }
}
