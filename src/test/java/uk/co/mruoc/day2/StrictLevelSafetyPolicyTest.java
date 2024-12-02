package uk.co.mruoc.day2;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class StrictLevelSafetyPolicyTest {

    @ParameterizedTest
    @MethodSource("levelsAndExpectedResult")
    void shouldReturnReportSafetyResult(Collection<Integer> levels, boolean expectedResult) {
        LevelSafetyPolicy policy = new StrictLevelSafetyPolicy();

        boolean safe = policy.isSafe(levels);

        assertThat(safe).isEqualTo(expectedResult);
    }

    private static Stream<Arguments> levelsAndExpectedResult() {
        return Stream.of(
                Arguments.of(List.of(), true),
                Arguments.of(List.of(1), true),
                Arguments.of(List.of(1, 4), true),
                Arguments.of(List.of(4, 1), true),
                Arguments.of(List.of(1, 1), false),
                Arguments.of(List.of(1, 5), false),
                Arguments.of(List.of(5, 1), false),
                Arguments.of(List.of(1, 4, 3), false),
                Arguments.of(List.of(3, 4, 1), false));
    }
}
