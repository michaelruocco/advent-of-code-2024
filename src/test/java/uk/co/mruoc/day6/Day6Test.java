package uk.co.mruoc.day6;

import static org.assertj.core.api.Assertions.assertThat;
import static uk.co.mruoc.file.FileLoader.loadContentFromClasspath;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class Day6Test {

    private static final String EXAMPLE_PATH = "day-6/example-map.txt";
    private static final String EXAMPLE_LOOP_1_PATH = "day-6/example-map-loop-1.txt";
    private static final String EXAMPLE_LOOP_2_PATH = "day-6/example-map-loop-2.txt";

    private final LabMapLoader loader = new LabMapLoader();

    @ParameterizedTest
    @MethodSource("pathAndExpectedVisitedPositionCount")
    void shouldCountMapPositionsVisitedByGuard(String path, int expectedVisitedPositionCount) {
        LabMap initialMap = loader.loadMap(path);

        LabMap completeMap = initialMap.performPatrol();

        assertThat(completeMap.countVisitedPositions()).isEqualTo(expectedVisitedPositionCount);
    }

    @ParameterizedTest
    @MethodSource("pathAndExpectedState")
    void shouldRecordGuardPathCorrectly(String path, String expectedState) {
        LabMap initialMap = loader.loadMap(path);

        LabMap completeMap = initialMap.performPatrol();

        assertThat(completeMap.getState()).isEqualTo(expectedState);
    }

    private static Stream<Arguments> pathAndExpectedVisitedPositionCount() {
        return Stream.of(
                Arguments.of(EXAMPLE_PATH, 41),
                Arguments.of("day-6/map.txt", 4939),
                Arguments.of(EXAMPLE_LOOP_1_PATH, 18),
                Arguments.of(EXAMPLE_LOOP_2_PATH, 26));
    }

    private static Stream<Arguments> pathAndExpectedState() {
        return Stream.of(
                Arguments.of(EXAMPLE_PATH, loadContentFromClasspath("day-6/example-map-final.txt")),
                Arguments.of(EXAMPLE_LOOP_1_PATH, loadContentFromClasspath("day-6/example-map-loop-1-final.txt")),
                Arguments.of(EXAMPLE_LOOP_2_PATH, loadContentFromClasspath("day-6/example-map-loop-2-final.txt")));
    }
}
