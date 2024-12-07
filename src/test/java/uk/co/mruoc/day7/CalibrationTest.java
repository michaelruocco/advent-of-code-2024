package uk.co.mruoc.day7;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class CalibrationTest {

    private final CalibrationsLoader loader = new CalibrationsLoader();

    @ParameterizedTest
    @MethodSource("calibrationAndExpectedCouldBeTrueResult")
    void shouldCalculateCouldBeTrueResult(String input, boolean expectedResult) {
        Calibration calibration = loader.toCalibration(input);

        boolean result = calibration.couldBeTrue();

        assertThat(result).isEqualTo(expectedResult);
    }

    private static Stream<Arguments> calibrationAndExpectedCouldBeTrueResult() {
        return Stream.of(
                Arguments.of("190: 10 19", true),
                Arguments.of("3267: 81 40 27", true),
                Arguments.of("292: 11 6 16 20", true),
                Arguments.of("21037: 9 7 18 13", false),
                Arguments.of("192: 17 8 14", false));
    }
}
