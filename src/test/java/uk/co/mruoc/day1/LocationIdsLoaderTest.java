package uk.co.mruoc.day1;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class LocationIdsLoaderTest {

    private final LocationIdsLoader loader = new LocationIdsLoader();

    @Test
    void shouldLoadLocationIds() {
        String path = "day-1/test-location-ids.txt";

        LocationIds ids = loader.load(path);

        assertThat(ids.getOne()).containsExactly(3, 4, 2, 1, 3, 3);
        assertThat(ids.getTwo()).containsExactly(4, 3, 5, 3, 9, 3);
    }
}
