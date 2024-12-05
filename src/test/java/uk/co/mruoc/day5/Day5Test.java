package uk.co.mruoc.day5;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class Day5Test {

    private static final String EXAMPLE_PATH = "day-5/example-page-rules.txt";
    private static final String PATH = "day-5/page-rules.txt";

    private final PageRulesLoader loader = new PageRulesLoader();

    @ParameterizedTest
    @MethodSource("pathAndExpectedResult")
    void shouldCalculateMiddleSumOfCorrectlyOrderedPage(String path, int expectedResult) {
        PageRules rules = loader.loadRules(path);
        Pages pages = loader.loadPages(path);

        int result = pages.findCorrectlyOrdered(rules).getMiddlePageSum();

        assertThat(result).isEqualTo(expectedResult);
    }

    @ParameterizedTest
    @MethodSource("pathAndExpectedCorrectedResult")
    void shouldCorrectIncorrectlyOrderedPages(String path, int expectedResult) {
        PageRules rules = loader.loadRules(path);
        Pages pages = loader.loadPages(path);

        int result = pages.findIncorrectlyOrdered(rules).correct(rules).getMiddlePageSum();

        assertThat(result).isEqualTo(expectedResult);
    }

    private static Stream<Arguments> pathAndExpectedResult() {
        return Stream.of(Arguments.of(EXAMPLE_PATH, 143), Arguments.of(PATH, 5762));
    }

    private static Stream<Arguments> pathAndExpectedCorrectedResult() {
        return Stream.of(Arguments.of(EXAMPLE_PATH, 123), Arguments.of(PATH, 4130));
    }
}
