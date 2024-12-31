package uk.co.mruoc.day22;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;
import org.junit.jupiter.api.Test;
import uk.co.mruoc.file.FileLoader;

class Day22Test {

    private final SecretCalculator calculator = new SecretCalculator();

    @Test
    void shouldCalculateDailySumOfSecretNumbersForEachBuyer() {
        Collection<Long> inputs = loadInputs();

        long result = calculator.calculateSumOfLastOfDay(inputs);

        assertThat(result).isEqualTo(17577894908L);
    }

    @Test
    void shouldCalculateMostBananasForEachBuyer() {
        Collection<Long> inputs = loadInputs();

        long result = calculator.calculateMostBananas(inputs);

        assertThat(result).isEqualTo(1931);
    }

    private static Collection<Long> loadInputs() {
        return FileLoader.loadContentLinesFromClasspath("day-22/puzzle.txt").stream()
                .map(Long::parseLong)
                .toList();
    }
}
