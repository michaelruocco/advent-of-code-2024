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
    void shouldCalculateMapResult(String path, PointAccumulator accumulator, long expectedResult) {
        TrailMap map = loader.load(path);

        long result = new TrailFinder(accumulator).findResult(map);

        assertThat(result).isEqualTo(expectedResult);
    }

    private static Stream<Arguments> pathAndFinderAndExpectedResult() {
        PointAccumulator scoreAccumulator = new ScorePointAccumulator();
        PointAccumulator ratingAccumulator = new RatingPointAccumulator();
        return Stream.of(
                Arguments.of(examplePath(1), scoreAccumulator, 1),
                Arguments.of(examplePath(2), scoreAccumulator, 2L),
                Arguments.of(examplePath(3), scoreAccumulator, 4L),
                Arguments.of(examplePath(4), scoreAccumulator, 3L),
                Arguments.of(examplePath(5), scoreAccumulator, 36L),
                Arguments.of(PATH, scoreAccumulator, 566L),
                Arguments.of(examplePath(6), ratingAccumulator, 3L),
                Arguments.of(examplePath(7), ratingAccumulator, 13L),
                Arguments.of(examplePath(8), ratingAccumulator, 227L),
                Arguments.of(PATH, ratingAccumulator, 1324L));
    }

    private static String examplePath(int number) {
        return String.format("day-10/example-map-%d.txt", number);
    }
}
