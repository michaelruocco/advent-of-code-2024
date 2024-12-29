package uk.co.mruoc.day19;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class Day19Test {

    private static final String EXAMPLE_PATH = "day-19/example-1.txt";
    private static final String PUZZLE_PATH = "day-19/puzzle.txt";

    private final TowelPatternLoader loader = new TowelPatternLoader();

    @ParameterizedTest
    @MethodSource("pathAndPossibleDesigns")
    void shouldReturnPossibleDesigns(String path, long expectedResult) {
        TowelPatterns patterns = loader.loadTowelPatterns(path);
        Collection<String> targetDesigns = loader.loadTargetDesigns(path);

        long result = patterns.countPossible(targetDesigns);

        assertThat(result).isEqualTo(expectedResult);
    }

    @ParameterizedTest
    @MethodSource("pathAndExpectedPossibleArrangements")
    void shouldCountPossibleArrangementsForEachDesign(String path, long expectedResult) {
        TowelPatterns patterns = loader.loadTowelPatterns(path);
        Collection<String> targetDesigns = loader.loadTargetDesigns(path);

        long result = patterns.countPossibleArrangements(targetDesigns);

        assertThat(result).isEqualTo(expectedResult);
    }

    private static Stream<Arguments> pathAndPossibleDesigns() {
        return Stream.of(Arguments.of(EXAMPLE_PATH, 6), Arguments.of(PUZZLE_PATH, 374));
    }

    private static Stream<Arguments> pathAndExpectedPossibleArrangements() {
        return Stream.of(Arguments.of(EXAMPLE_PATH, 16), Arguments.of(PUZZLE_PATH, 1100663950563322L));
    }
}
