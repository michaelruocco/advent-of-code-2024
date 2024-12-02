package uk.co.mruoc.day1;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class LocationIdsLoaderTest {

    private static final String PATH = "day-1/test-location-ids.txt";

    private final LocationIdsLoader loader = new LocationIdsLoader();

    @Test
    void shouldLoadLocationIdsOne() {
        LocationIds ids = loader.load(PATH);

        assertThat(ids.getOne()).containsExactly(3, 4, 2, 1, 3, 3);
    }

    @Test
    void shouldLoadLocationIdsTwo() {
        LocationIds ids = loader.load(PATH);

        assertThat(ids.getTwo()).containsExactly(4, 3, 5, 3, 9, 3);
    }
}
