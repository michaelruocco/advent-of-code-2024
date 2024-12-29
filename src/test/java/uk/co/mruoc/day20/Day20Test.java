package uk.co.mruoc.day20;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class Day20Test {

    private static final String EXAMPLE_PATH = "day-20/example-1.txt";
    private static final String PUZZLE_PATH = "day-20/puzzle.txt";

    private static final TrackLoader LOADER = new TrackLoader();

    @ParameterizedTest
    @MethodSource("trackAndCounterAndRequiredSavingAndExpectedResult")
    void shouldReturnNumberOfCheatsThatSave(
            Track track, CheatCounter counter, long requiredSaving, long expectedResult) {
        CheatFinder finder = new CheatFinder(counter);

        long result = finder.countCheatsThatSave(track, requiredSaving);

        assertThat(result).isEqualTo(expectedResult);
    }

    private static Stream<Arguments> trackAndCounterAndRequiredSavingAndExpectedResult() {
        Track exampleTrack = LOADER.load(EXAMPLE_PATH);
        Track puzzleTrack = LOADER.load(PUZZLE_PATH);
        CheatCounter part1Counter = new Part1CheatCounter();
        CheatCounter part2Counter = new Part2CheatCounter();
        return Stream.of(
                Arguments.of(exampleTrack, part1Counter, 64, 1),
                Arguments.of(puzzleTrack, part1Counter, 100, 1346),
                Arguments.of(exampleTrack, part2Counter, 76, 3),
                Arguments.of(puzzleTrack, part2Counter, 100, 985482));
    }
}
