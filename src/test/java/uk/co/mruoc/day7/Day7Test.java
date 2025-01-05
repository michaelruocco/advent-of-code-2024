package uk.co.mruoc.day7;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class Day7Test {

    @ParameterizedTest
    @MethodSource("pathAndOperatorsAndExpectedResult")
    void shouldCalculateTotalCalibrationResult(String path, Collection<Operator> operations, long expectedResult) {
        CalibrationsLoader loader = new CalibrationsLoader();
        Collection<Calibration> calibrations = loader.load(path);
        CalibrationCalculator calculator = new CalibrationCalculator(operations);

        long result = calculator.calculate(calibrations);

        assertThat(result).isEqualTo(expectedResult);
    }

    private static Stream<Arguments> pathAndOperatorsAndExpectedResult() {
        String examplePath = "day-7/example-1.txt";
        String puzzlePath = "day-7/puzzle.txt";
        Collection<Operator> part1Operators = List.of(new Sum(), new Multiply());
        Collection<Operator> part2Operators = List.of(new Sum(), new Multiply(), new Concatenate());
        return Stream.of(
                Arguments.of(examplePath, part1Operators, 3749L),
                Arguments.of(puzzlePath, part1Operators, 5030892084481L),
                Arguments.of(examplePath, part2Operators, 11387L),
                Arguments.of(puzzlePath, part2Operators, 91377448644679L));
    }
}
