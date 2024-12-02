package uk.co.mruoc.day1;

import static org.assertj.core.api.Assertions.assertThat;
import static uk.co.mruoc.day1.ExampleTestFile.PATH;

import org.junit.jupiter.api.Test;

class ExampleTest {

    private final LocationIdsLoader loader = new LocationIdsLoader();

    @Test
    void shouldCalculateExampleCorrectly() {
        LocationIds ids = loader.load(PATH);

        int totalDistance = ids.sort().toPairs().calculateTotalDistance();

        assertThat(totalDistance).isEqualTo(11);
    }
}
