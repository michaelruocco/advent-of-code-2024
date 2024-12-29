package uk.co.mruoc.day21;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class Day21Test {

    private final ComplexityCalculator calculator = new ComplexityCalculator();

    @ParameterizedTest
    @MethodSource("commandsAndDepthAndExpectedResult")
    void shouldReturnCorrectResult(Collection<String> commands, int depth, long expectedResult) {
        long result = calculator.solve(commands, depth);

        assertThat(result).isEqualTo(expectedResult);
    }

    private static Stream<Arguments> commandsAndDepthAndExpectedResult() {
        Collection<String> exampleCommands = List.of("029A", "980A", "179A", "456A", "379A");
        Collection<String> puzzleCommands = List.of("539A", "964A", "803A", "149A", "789A");
        return Stream.of(
                Arguments.of(exampleCommands, 2, 126384L),
                Arguments.of(puzzleCommands, 2, 231564L),
                Arguments.of(exampleCommands, 25, 154115708116294L),
                Arguments.of(puzzleCommands, 25, 281212077733592L));
    }
}
