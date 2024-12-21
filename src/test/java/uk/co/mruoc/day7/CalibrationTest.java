package uk.co.mruoc.day7;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class CalibrationTest {

    @ParameterizedTest
    @MethodSource("calibrationAndExpectedResult")
    void shouldCalculateCouldBeTrueResult(String input, boolean expectedResult) {
        Calibration calibration = new CalibrationsLoader().toCalibration(input);

        boolean result = calibration.couldBeTrue();

        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void shouldThrowExceptionForUnrecognisedOperator() {
        Calibration calibration = Calibration.builder()
                .testValue(1)
                .numbers(List.of(2L, 3L))
                .operators(List.of('+', '-'))
                .build();

        Throwable error = catchThrowable(calibration::couldBeTrue);

        assertThat(error).isInstanceOf(IllegalArgumentException.class).hasMessage("- operator not supported");
    }

    private static Stream<Arguments> calibrationAndExpectedResult() {
        return Stream.of(
                Arguments.of("190: 10 19", true),
                Arguments.of("3267: 81 40 27", true),
                Arguments.of("292: 11 6 16 20", true),
                Arguments.of("21037: 9 7 18 13", false),
                Arguments.of("192: 17 8 14", false));
    }
}
