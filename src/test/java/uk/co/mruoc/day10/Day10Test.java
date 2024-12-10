package uk.co.mruoc.day10;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class Day10Test {

    private static final String PATH = "day-10/map.txt";

    private final TrailMapLoader loader = new TrailMapLoader();

    @ParameterizedTest
    @MethodSource("pathAndExpectedScore")
    void shouldCalculateMapScore(String path, long expectedScore) {
        TrailMap map = loader.load(path);
        TrailFinder finder = new TrailFinder(map);

        long score = finder.findScore();

        assertThat(score).isEqualTo(expectedScore);
    }

    private static Stream<Arguments> pathAndExpectedScore() {
        return Stream.of(
                Arguments.of(examplePath(1), 1),
                Arguments.of(examplePath(2), 2L),
                Arguments.of(examplePath(3), 4L),
                Arguments.of(examplePath(4), 3L),
                Arguments.of(examplePath(5), 36L),
                Arguments.of(PATH, -1L));
    }

    private static String examplePath(int number) {
        return String.format("day-10/example-map-%d.txt", number);
    }
}
