package uk.co.mruoc.day4;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class Day4Test {

    private final WordSearchLoader loader = new WordSearchLoader();

    @ParameterizedTest
    @MethodSource("pathAndWordAndExpectedResult")
    void shouldSearchForAllOccurrencesOfWord(String path, String word, int expectedResult) {
        WordSearch wordSearch = loader.load(path);

        int result = wordSearch.findAll(word);

        assertThat(result).isEqualTo(expectedResult);
    }

    @ParameterizedTest
    @MethodSource("pathAndWordAndExpectedInXResult")
    void shouldSearchForAllOccurrencesOfWordInX(String path, String word, int expectedResult) {
        WordSearch wordSearch = loader.load(path);

        int result = wordSearch.findAllInX(word);

        assertThat(result).isEqualTo(expectedResult);
    }

    private static Stream<Arguments> pathAndWordAndExpectedResult() {
        String target = "XMAS";
        return Stream.of(
                Arguments.of("day-4/example-word-search.txt", target, 18),
                Arguments.of("day-4/word-search.txt", target, 2344));
    }

    private static Stream<Arguments> pathAndWordAndExpectedInXResult() {
        String target = "MAS";
        return Stream.of(
                Arguments.of("day-4/simple-x-word-search.txt", target, 1),
                Arguments.of("day-4/example-word-search.txt", target, 9),
                Arguments.of("day-4/word-search.txt", target, 1815));
    }
}