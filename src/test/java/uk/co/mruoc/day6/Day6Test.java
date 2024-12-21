package uk.co.mruoc.day6;

import static org.assertj.core.api.Assertions.assertThat;
import static uk.co.mruoc.file.FileLoader.loadContentFromClasspath;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class Day6Test {

    private static final String EXAMPLE_PATH = "day-6/example-map.txt";
    private static final String MAP_PATH = "day-6/map.txt";

    private static final String EXAMPLE_LOOP_1_PATH = "day-6/example-map-loop-1.txt";
    private static final String EXAMPLE_LOOP_2_PATH = "day-6/example-map-loop-2.txt";

    private final GridLoader loader = new GridLoader();

    @ParameterizedTest
    @MethodSource("pathAndExpectedVisitedLocationCount")
    void shouldCountMapPositionsVisitedByGuard(String path, int expectedVisitedLocationCount) {
        Grid grid = loader.loadGrid(path);
        LabMap map = toLabMap(grid);
        Guard guard = grid.findGuard();

        Result result = guard.patrol(map);

        assertThat(result.getVisitedLocationCount()).isEqualTo(expectedVisitedLocationCount);
    }

    @ParameterizedTest
    @MethodSource("pathAndExpectedState")
    void shouldRecordPathCorrectly(String path, String expectedState) {
        Grid grid = loader.loadGrid(path);
        LabMap map = toLabMap(grid);
        Guard guard = grid.findGuard();

        Result result = guard.patrol(map);

        assertThat(map.toState(result.getVisitedLocations())).isEqualTo(expectedState);
    }

    @ParameterizedTest
    @MethodSource("pathAndExpectedLoopObstructionCount")
    void shouldCountAllSingleObstructionsCausingALoop(String path, long expectedLoopObstructionCount) {
        Grid grid = loader.loadGrid(path);
        LabMap map = toLabMap(grid);
        Guard guard = grid.findGuard();

        long count = guard.countSingleObstructionsCausingLoop(map);

        assertThat(count).isEqualTo(expectedLoopObstructionCount);
    }

    private static LabMap toLabMap(Grid grid) {
        return LabMap.builder()
                .height(grid.getHeight())
                .width(grid.getWidth())
                .walls(grid.getWalls())
                .build();
    }

    private static Stream<Arguments> pathAndExpectedVisitedLocationCount() {
        return Stream.of(
                Arguments.of(EXAMPLE_PATH, 41),
                Arguments.of(MAP_PATH, 4939),
                Arguments.of(EXAMPLE_LOOP_1_PATH, 18),
                Arguments.of(EXAMPLE_LOOP_2_PATH, 26));
    }

    private static Stream<Arguments> pathAndExpectedState() {
        return Stream.of(
                Arguments.of(EXAMPLE_PATH, loadContentFromClasspath("day-6/example-map-final.txt")),
                Arguments.of(EXAMPLE_LOOP_1_PATH, loadContentFromClasspath("day-6/example-map-loop-1-final.txt")),
                Arguments.of(EXAMPLE_LOOP_2_PATH, loadContentFromClasspath("day-6/example-map-loop-2-final.txt")));
    }

    private static Stream<Arguments> pathAndExpectedLoopObstructionCount() {
        return Stream.of(Arguments.of(EXAMPLE_PATH, 6L), Arguments.of(MAP_PATH, 1434L));
    }
}
