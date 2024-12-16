package uk.co.mruoc.day15;

import static org.assertj.core.api.Assertions.assertThat;
import static uk.co.mruoc.file.FileLoader.loadContentFromClasspath;

import org.junit.jupiter.api.Test;

class WarehouseMapTest {

    private final WarehouseMapLoader mapLoader = new WarehouseMapLoader();
    private final DirectionsLoader directionsLoader = new DirectionsLoader();

    @Test
    void shouldExpand() {
        WarehouseMap map = mapLoader.load("day-15/example-2.txt");

        WarehouseMap expanded = map.expand();

        assertThat(expanded.getState())
                .isEqualToIgnoringWhitespace(loadContentFromClasspath("day-15/example-2-expanded-expected-state.txt"));
    }
}
