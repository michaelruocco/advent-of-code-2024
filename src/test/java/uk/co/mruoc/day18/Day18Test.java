package uk.co.mruoc.day18;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import uk.co.mruoc.Point;

class Day18Test {

    private static final String EXAMPLE_PATH = "day-18/example-1.txt";
    private static final int EXAMPLE_SIZE = 6;

    private static final String PUZZLE_PATH = "day-18/puzzle.txt";
    private static final int PUZZLE_SIZE = 70;

    @ParameterizedTest
    @MethodSource("sizeAndNumberOfBytesAndPathAndExpectedSteps")
    void shouldCalculateMinimumNumberOfSteps(int size, int numberOfBytes, String path, int expectedSteps) {
        Historians historians = new Historians();
        MemorySpace memorySpace =
                MemorySpaceLoader.builder().size(size).build().load(path).withMaximumNumberOfBytes(numberOfBytes);

        int steps = historians.getMinimumNumberOfStepsToEnd(memorySpace);

        assertThat(steps).isEqualTo(expectedSteps);
    }

    @ParameterizedTest
    @MethodSource("sizeAndPathAndExpectedBlocker")
    void shouldCalculateFirstBlocker(int size, String path, Point expectedBlocker) {
        Historians historians = new Historians();
        MemorySpace memorySpace = MemorySpaceLoader.builder().size(size).build().load(path);

        Optional<Point> blocker = historians.findFirstBlocker(memorySpace);

        assertThat(blocker).contains(expectedBlocker);
    }

    private static Stream<Arguments> sizeAndNumberOfBytesAndPathAndExpectedSteps() {
        return Stream.of(
                Arguments.of(EXAMPLE_SIZE, 12, EXAMPLE_PATH, 22), Arguments.of(PUZZLE_SIZE, 1024, PUZZLE_PATH, 380));
    }

    private static Stream<Arguments> sizeAndPathAndExpectedBlocker() {
        return Stream.of(
                Arguments.of(EXAMPLE_SIZE, EXAMPLE_PATH, new Point(1, 6)),
                Arguments.of(PUZZLE_SIZE, PUZZLE_PATH, new Point(50, 26)));
    }
}
