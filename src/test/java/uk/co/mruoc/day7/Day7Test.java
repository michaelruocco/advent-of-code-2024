package uk.co.mruoc.day7;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class Day7Test {

    private static final String EXAMPLE_PATH = "day-7/example-calibrations.txt";
    private static final String PATH = "day-7/calibrations.txt";

    private static final Collection<Character> PART_1_OPERATORS = List.of('+', '*');
    private static final Collection<Character> PART_2_OPERATORS = List.of('+', '*', '|');

    @ParameterizedTest
    @MethodSource("pathAndOperatorsAndExpectedResult")
    void shouldCalculateTotalCalibrationResult(String path, Collection<Character> operators, long expectedResult) {
        CalibrationsLoader loader = new CalibrationsLoader(operators);
        Calibrations calibrations = loader.load(path);

        long result = calibrations.getTotalResult();

        assertThat(result).isEqualTo(expectedResult);
    }

    private static Stream<Arguments> pathAndOperatorsAndExpectedResult() {

        return Stream.of(
                Arguments.of(EXAMPLE_PATH, PART_1_OPERATORS, 3749L),
                Arguments.of(PATH, PART_1_OPERATORS, 5030892084481L),
                Arguments.of(EXAMPLE_PATH, PART_2_OPERATORS, 11387L),
                Arguments.of(PATH, PART_2_OPERATORS, 91377448644679L));
    }
}
