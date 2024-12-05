package uk.co.mruoc.day5;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class Day5Test {

    private final PageRulesLoader loader = new PageRulesLoader();

    @ParameterizedTest
    @MethodSource("pathAndExpectedResult")
    void shouldSearchForAllOccurrencesOfWord(String path, int expectedResult) {
        PageRules rules = loader.loadRules(path);
        Pages pages = loader.loadPages(path);

        int result = pages.findCorrectlyOrdered(rules).getMiddlePageSum();

        assertThat(result).isEqualTo(expectedResult);
    }

    private static Stream<Arguments> pathAndExpectedResult() {
        return Stream.of(Arguments.of("day-5/example-page-rules.txt", 143), Arguments.of("day-5/page-rules.txt", 5762));
    }
}
