package uk.co.mruoc.day8;

import static org.assertj.core.api.Assertions.assertThat;
import static uk.co.mruoc.file.FileLoader.loadContentFromClasspath;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class Day8Test {

    private static final String EXAMPLE_A_PATH = "day-8/example-antennas-a.txt";
    private static final String EXAMPLE_B_PATH = "day-8/example-antennas-b.txt";
    private static final String EXAMPLE_C_PATH = "day-8/example-antennas-c.txt";
    private static final String PATH = "day-8/antennas.txt";

    @ParameterizedTest
    @MethodSource("pathAndExpectedAntiNodeCount")
    void shouldCalculateCorrectNumberOfAntiNodes(String path, long expectedCount) {
        AntennaMapLoader loader = new AntennaMapLoader();
        AntennaMap map = loader.load(path);

        long count = map.countAntiNodes();

        assertThat(count).isEqualTo(expectedCount);
    }

    @ParameterizedTest
    @MethodSource("pathAndExpectedState")
    void shouldUpdateMapStateWithAntiNodes(String path, String expectedState) {
        AntennaMapLoader loader = new AntennaMapLoader();
        AntennaMap map = loader.load(path);

        map.populateAntiNodes();

        assertThat(map.getState()).isEqualTo(expectedState);
    }

    private static Stream<Arguments> pathAndExpectedAntiNodeCount() {
        return Stream.of(
                Arguments.of(EXAMPLE_A_PATH, 2L),
                Arguments.of(EXAMPLE_B_PATH, 4L),
                Arguments.of(EXAMPLE_C_PATH, 14L),
                Arguments.of(PATH, 247L));
    }

    private static Stream<Arguments> pathAndExpectedState() {
        return Stream.of(
                Arguments.of(EXAMPLE_A_PATH, loadContentFromClasspath("day-8/example-antennas-a-expected-state.txt")),
                Arguments.of(EXAMPLE_B_PATH, loadContentFromClasspath("day-8/example-antennas-b-expected-state.txt")),
                Arguments.of(EXAMPLE_C_PATH, loadContentFromClasspath("day-8/example-antennas-c-expected-state.txt")),
                Arguments.of(PATH, loadContentFromClasspath("day-8/antennas-expected-state.txt")));
    }
}
