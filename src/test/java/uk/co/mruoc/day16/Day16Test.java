package uk.co.mruoc.day16;

import static org.assertj.core.api.Assertions.assertThat;
import static uk.co.mruoc.file.FileLoader.loadContentFromClasspath;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class Day16Test {

    @ParameterizedTest
    @MethodSource("pathAndInitialExpectedState")
    void shouldReturnInitialMapState(String path, String expectedState) {
        Maze maze = new MazeLoader().load(path);

        String state = maze.toState();

        assertThat(state).isEqualToIgnoringWhitespace(expectedState);
    }

    @ParameterizedTest
    @MethodSource("pathAndExpectedScore")
    void shouldFindLowestScore(String path, long expectedScore) {
        Maze maze = new MazeLoader().load(path);
        Reindeer reindeer = new Reindeer(maze);

        long score = reindeer.findLowestScore();

        assertThat(score).isEqualTo(expectedScore);
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
                Arguments.of(examplePath(1), 7036L),
                Arguments.of(examplePath(2), 11048L),
                Arguments.of("day-16/puzzle.txt", 99488L));
    }

    private static String examplePath(int number) {
        return String.format("day-16/example-%d.txt", number);
    }
}
