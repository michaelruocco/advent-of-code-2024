package uk.co.mruoc.day16;

import static org.assertj.core.api.Assertions.assertThat;
import static uk.co.mruoc.file.FileLoader.loadContentFromClasspath;

import java.util.Optional;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import uk.co.mruoc.Maze;
import uk.co.mruoc.MazeLoader;

class Day16Test {

    private static final String PUZZLE_PATH = "day-16/puzzle.txt";

    @ParameterizedTest
    @MethodSource("pathAndInitialExpectedState")
    void shouldReturnInitialMapState(String path, String expectedState) {
        Maze maze = new MazeLoader().load(path);

        String state = maze.toState();

        assertThat(state).isEqualToIgnoringWhitespace(expectedState);
    }

    @ParameterizedTest
    @MethodSource("pathAndExpectedScore")
    void shouldFindLowestScore(String path, int expectedScore) {
        Maze maze = new MazeLoader().load(path);
        Reindeer reindeer = new Reindeer(maze);

        Optional<Integer> score = reindeer.findLowestScore();

        assertThat(score).contains(expectedScore);
    }

    @ParameterizedTest
    @MethodSource("pathAndExpectedNumberOfTiles")
    void shouldFindNumberOfTilesOnAnyBestPath(String path, int expectedNumberOfTiles) {
        Maze maze = new MazeLoader().load(path);
        Reindeer reindeer = new Reindeer(maze);

        Optional<Integer> numberOfTiles = reindeer.findNumberOfLocationsOnAnyBestPath();

        assertThat(numberOfTiles).contains(expectedNumberOfTiles);
    }

    private static Stream<Arguments> pathAndInitialExpectedState() {
        String example1Path = examplePath(1);
        String example2Path = examplePath(2);
        return Stream.of(
                Arguments.of(example1Path, loadContentFromClasspath(example1Path)),
                Arguments.of(example2Path, loadContentFromClasspath(example2Path)));
    }

    private static Stream<Arguments> pathAndExpectedScore() {
        return Stream.of(
                Arguments.of(examplePath(1), 7036),
                Arguments.of(examplePath(2), 11048),
                Arguments.of(PUZZLE_PATH, 99488));
    }

    private static Stream<Arguments> pathAndExpectedNumberOfTiles() {
        return Stream.of(
                Arguments.of(examplePath(1), 45), Arguments.of(examplePath(2), 64), Arguments.of(PUZZLE_PATH, 516));
    }

    private static String examplePath(int number) {
        return String.format("day-16/example-%d.txt", number);
    }
}
