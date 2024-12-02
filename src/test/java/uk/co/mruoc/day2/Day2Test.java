package uk.co.mruoc.day2;

import static org.assertj.core.api.Assertions.assertThat;
import static uk.co.mruoc.day2.ReportFilePaths.EXAMPLE_PATH;
import static uk.co.mruoc.day2.ReportFilePaths.REAL_PATH;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class Day2Test {

    private final ReportsLoader loader = new ReportsLoader();

    @ParameterizedTest
    @MethodSource("filePathAndExpectedSafeCount")
    void shouldCalculateDistanceCorrectly(String path, long expectedSafeCount) {
        Reports reports = loader.load(path);

        long safeCount = reports.getSafeCount();

        assertThat(safeCount).isEqualTo(expectedSafeCount);
    }

    private static Stream<Arguments> filePathAndExpectedSafeCount() {
        return Stream.of(Arguments.of(EXAMPLE_PATH, 2), Arguments.of(REAL_PATH, 549));
    }
}
