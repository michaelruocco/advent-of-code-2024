package uk.co.mruoc.day1;

import static org.assertj.core.api.Assertions.assertThat;
import static uk.co.mruoc.day1.FilePaths.EXAMPLE_PATH;

import org.junit.jupiter.api.Test;

class LocationIdsLoaderTest {

    private final LocationIdsLoader loader = new LocationIdsLoader();

    @Test
    void shouldLoadLocationIdsOne() {
        LocationIds ids = loader.load(EXAMPLE_PATH);

        assertThat(ids.getOne()).containsExactly(3, 4, 2, 1, 3, 3);
    }

    @Test
    void shouldLoadLocationIdsTwo() {
        LocationIds ids = loader.load(EXAMPLE_PATH);

        assertThat(ids.getTwo()).containsExactly(4, 3, 5, 3, 9, 3);
    }
}
