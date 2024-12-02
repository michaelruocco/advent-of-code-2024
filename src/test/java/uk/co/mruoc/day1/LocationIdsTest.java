package uk.co.mruoc.day1;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

class LocationIdsTest {

    @Test
    void shouldSortOneIdsAscending() {
        LocationIds ids =
                LocationIds.builder().one(List.of(2, 1, 3)).two(List.of()).build();

        LocationIds sortedIds = ids.sort();

        assertThat(sortedIds.getOne()).containsExactly(1, 2, 3);
    }

    @Test
    void shouldSortTwoIdsAscending() {
        LocationIds ids =
                LocationIds.builder().one(List.of()).two(List.of(9, 7, 8)).build();

        LocationIds sortedIds = ids.sort();

        assertThat(sortedIds.getTwo()).containsExactly(7, 8, 9);
    }
}
