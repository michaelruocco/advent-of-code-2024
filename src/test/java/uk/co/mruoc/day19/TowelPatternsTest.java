package uk.co.mruoc.day19;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class TowelPatternsTest {

    private final TowelPatterns patterns = new TowelPatterns("r", "wr", "b", "g", "bwu", "rb", "gb", "br");

    @ParameterizedTest
    @CsvSource({
        "brwrr,true",
        "bggr,true",
        "gbbr,true",
        "rrbgbr,true",
        "ubwu,false",
        "bwurrg,true",
        "brgr,true",
        "bbrgwb,false",
    })
    void shouldReturnIfDesignIsPossible(String design, boolean expectedResult) {
        boolean possible = patterns.isPossible(design);

        assertThat(possible).isEqualTo(expectedResult);
    }

    @ParameterizedTest
    @CsvSource({
        "brwrr,2",
        "bggr,1",
        "gbbr,4",
        "rrbgbr,6",
        "ubwu,0",
        "bwurrg,1",
        "brgr,2",
        "bbrgwb,0",
    })
    void shouldReturnPossibleArrangementCountsForDesign(String design, String expectedResult) {
        long result = patterns.countPossibleArrangements(design);

        assertThat(result).isEqualTo(Long.parseLong(expectedResult));
    }
}
