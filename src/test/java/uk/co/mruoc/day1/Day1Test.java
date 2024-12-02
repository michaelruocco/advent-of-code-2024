package uk.co.mruoc.day1;

import static org.assertj.core.api.Assertions.assertThat;
import static uk.co.mruoc.day1.FilePaths.EXAMPLE_PATH;
import static uk.co.mruoc.day1.FilePaths.REAL_PATH;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class Day1Test {

    private final LocationIdsLoader loader = new LocationIdsLoader();

    @ParameterizedTest
    @MethodSource("filePathAndExpectedDistance")
    void shouldCalculateDistanceCorrectly(String path, int expectedDistance) {
        LocationIds ids = loader.load(path);

        int totalDistance = ids.sort().toPairs().calculateTotalDistance();

        assertThat(totalDistance).isEqualTo(expectedDistance);
    }

    private static Stream<Arguments> filePathAndExpectedDistance() {
        return Stream.of(Arguments.of(EXAMPLE_PATH, 11), Arguments.of(REAL_PATH, 2378066));
    }
}
