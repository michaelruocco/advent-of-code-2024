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
    @MethodSource("pathAndFinderAndExpectedResult")
    void shouldCalculateMapResult(String path, PointFinder pointFinder, long expectedResult) {
        TrailMap map = loader.load(path);

        long result = new TrailFinder(pointFinder).findResult(map);

        assertThat(result).isEqualTo(expectedResult);
    }

    private static Stream<Arguments> pathAndFinderAndExpectedResult() {
        PointFinder scoreFinder = new ScorePointFinder();
        PointFinder ratingFinder = new RatingPointFinder();
        return Stream.of(
                Arguments.of(examplePath(1), scoreFinder, 1),
                Arguments.of(examplePath(2), scoreFinder, 2L),
                Arguments.of(examplePath(3), scoreFinder, 4L),
                Arguments.of(examplePath(4), scoreFinder, 3L),
                Arguments.of(examplePath(5), scoreFinder, 36L),
                Arguments.of(examplePath(6), ratingFinder, 1L),
                Arguments.of(examplePath(7), ratingFinder, 4L),
                Arguments.of(examplePath(8), ratingFinder, 2L),
                Arguments.of(PATH, scoreFinder, 566L));
    }

    private static String examplePath(int number) {
        return String.format("day-10/example-map-%d.txt", number);
    }
}
