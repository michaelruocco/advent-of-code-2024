package uk.co.mruoc.day25;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class Day25Test {

    private final SchematicLoader loader = new SchematicLoader();

    private final SchematicChecker checker = new SchematicChecker();

    @ParameterizedTest
    @MethodSource("pathAndExpectedCount")
    void shouldReturnMatchingKeyCount(String path, int expectedCount) {
        Collection<Schematic> schematics = loader.load(path);

        int count = checker.countPotentialLockAndKeyPairs(schematics);

        assertThat(count).isEqualTo(expectedCount);
    }

    private static Stream<Arguments> pathAndExpectedCount() {
        return Stream.of(Arguments.of("day-25/example-1.txt", 3), Arguments.of("day-25/puzzle.txt", 3114));
    }
}
