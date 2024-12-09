package uk.co.mruoc.day8;

import static org.assertj.core.api.Assertions.assertThat;
import static uk.co.mruoc.file.FileLoader.loadContentFromClasspath;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class Day8Test {

    private static final String EXAMPLE_A_PATH = "day-8/example-antennas-a.txt";
    private static final String EXAMPLE_A_EXPECTED_STATE =
            loadContentFromClasspath("day-8/example-antennas-a-expected-state.txt");

    private static final String EXAMPLE_B_PATH = "day-8/example-antennas-b.txt";
    private static final String EXAMPLE_B_EXPECTED_STATE =
            loadContentFromClasspath("day-8/example-antennas-b-expected-state.txt");

    private static final String EXAMPLE_C_PATH = "day-8/example-antennas-c.txt";
    private static final String EXAMPLE_C_EXPECTED_STATE_PART_1 =
            loadContentFromClasspath("day-8/example-antennas-c-expected-state-part-1.txt");
    private static final String EXAMPLE_C_EXPECTED_STATE_PART_2 =
            loadContentFromClasspath("day-8/example-antennas-c-expected-state-part-2.txt");

    private static final String EXAMPLE_D_PATH = "day-8/example-antennas-d.txt";
    private static final String EXAMPLE_D_EXPECTED_STATE =
            loadContentFromClasspath("day-8/example-antennas-d-expected-state.txt");

    private static final String PATH = "day-8/antennas.txt";
    private static final String EXPECTED_STATE_PART_1 =
            loadContentFromClasspath("day-8/antennas-expected-state-part-1.txt");
    private static final String EXPECTED_STATE_PART_2 =
            loadContentFromClasspath("day-8/antennas-expected-state-part-2.txt");

    private static final AntiNodeFinder PART_1_FINDER = new Part1AntiNodeFinder();
    private static final AntiNodeFinder PART_2_FINDER = new Part2AntiNodeFinder();

    @ParameterizedTest
    @MethodSource("pathAndFinderAndExpectedAntiNodeCount")
    void shouldCalculateCorrectNumberOfAntiNodes(String path, AntiNodeFinder finder, long expectedCount) {
        AntennaMapLoader loader = new AntennaMapLoader(finder);
        AntennaMap map = loader.load(path);

        long count = map.countAntiNodes();
        System.out.println(map.getState());
        System.out.println();
        map.populateAntiNodes();
        System.out.println(map.getState());
        System.out.println();

        assertThat(count).isEqualTo(expectedCount);
    }

    @ParameterizedTest
    @MethodSource("pathAndFinderAndExpectedState")
    void shouldUpdateMapStateWithAntiNodes(String path, AntiNodeFinder finder, String expectedState) {
        AntennaMapLoader loader = new AntennaMapLoader(finder);
        AntennaMap map = loader.load(path);

        map.populateAntiNodes();

        assertThat(map.getState()).isEqualTo(expectedState);
    }

    private static Stream<Arguments> pathAndFinderAndExpectedAntiNodeCount() {
        return Stream.of(
                Arguments.of(EXAMPLE_A_PATH, PART_1_FINDER, 2L),
                Arguments.of(EXAMPLE_B_PATH, PART_1_FINDER, 4L),
                Arguments.of(EXAMPLE_C_PATH, PART_1_FINDER, 14L),
                Arguments.of(PATH, PART_1_FINDER, 247L),
                Arguments.of(EXAMPLE_D_PATH, PART_2_FINDER, 9L),
                Arguments.of(EXAMPLE_C_PATH, PART_2_FINDER, 34L),
                Arguments.of(PATH, PART_2_FINDER, 861L));
    }

    private static Stream<Arguments> pathAndFinderAndExpectedState() {
        return Stream.of(
                Arguments.of(EXAMPLE_A_PATH, PART_1_FINDER, EXAMPLE_A_EXPECTED_STATE),
                Arguments.of(EXAMPLE_B_PATH, PART_1_FINDER, EXAMPLE_B_EXPECTED_STATE),
                Arguments.of(EXAMPLE_C_PATH, PART_1_FINDER, EXAMPLE_C_EXPECTED_STATE_PART_1),
                Arguments.of(PATH, PART_1_FINDER, EXPECTED_STATE_PART_1),
                Arguments.of(EXAMPLE_D_PATH, PART_2_FINDER, EXAMPLE_D_EXPECTED_STATE),
                Arguments.of(EXAMPLE_C_PATH, PART_2_FINDER, EXAMPLE_C_EXPECTED_STATE_PART_2),
                Arguments.of(PATH, PART_2_FINDER, EXPECTED_STATE_PART_2));
    }
}
