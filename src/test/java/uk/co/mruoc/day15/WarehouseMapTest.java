package uk.co.mruoc.day15;

import static org.assertj.core.api.Assertions.assertThat;
import static uk.co.mruoc.file.FileLoader.loadContentFromClasspath;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class WarehouseMapTest {

    private final WarehouseMapLoader mapLoader = new WarehouseMapLoader();

    @ParameterizedTest
    @MethodSource("scaleAndExpectedState")
    void shouldReturnCorrectStateForScale(int scale, String expectedState) {
        WarehouseMap map = mapLoader.load("day-15/example-2.txt", scale);

        String state = map.getState();

        assertThat(state).isEqualToIgnoringWhitespace(expectedState);
    }

    private static Stream<Arguments> scaleAndExpectedState() {
        return Stream.of(
                Arguments.of(1, loadContentFromClasspath("day-15/example-2-part-1-initial-expected-state.txt")),
                Arguments.of(2, loadContentFromClasspath("day-15/example-2-part-2-initial-expected-state.txt")));
    }
}
