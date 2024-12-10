package uk.co.mruoc.day10;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class Day10Test {

    private static final String EXAMPLE_PATH_1 = "day-10/example-map-1.txt";
    private static final String EXAMPLE_PATH_2 = "day-10/example-map-2.txt";

    private final TrailMapLoader loader = new TrailMapLoader();
    private final TrailFinder finder = new TrailFinder();

    @ParameterizedTest
    @MethodSource("pathAndExpectedScore")
    void shouldCalculateMapScore(String path, long expectedScore) {
        TrailMap trailMap = loader.load(path);

        long score = finder.findScore(trailMap);

        assertThat(score).isEqualTo(expectedScore);
    }

    private static Stream<Arguments> pathAndExpectedScore() {
        return Stream.of(Arguments.of(EXAMPLE_PATH_1, 1), Arguments.of(EXAMPLE_PATH_2, 36L));
    }
}
