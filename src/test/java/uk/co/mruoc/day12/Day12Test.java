package uk.co.mruoc.day12;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class Day12Test {

    private final GardenLoader loader = new GardenLoader();

    @ParameterizedTest
    @MethodSource("pathAndRegions")
    void shouldGetRegionsInGarden(String path, Collection<Character> expectedPlantRegions) {
        Garden garden = loader.load(path);

        Collection<Region> regions = garden.getRegions();

        assertThat(regions).map(Region::getPlant).containsExactlyInAnyOrderElementsOf(expectedPlantRegions);
    }

    private static Stream<Arguments> pathAndRegions() {
        return Stream.of(
                Arguments.of(examplePath(1), List.of('A', 'B', 'C', 'D', 'E')),
                Arguments.of(examplePath(2), List.of('O', 'X', 'X', 'X', 'X')),
                Arguments.of(examplePath(3), List.of('R', 'I', 'C', 'F', 'V', 'J', 'C', 'E', 'I', 'M', 'S')));
    }

    private static String examplePath(int number) {
        return String.format("day-10/example-%d.txt", number);
    }
}
