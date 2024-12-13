package uk.co.mruoc.day12;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class Day12Test {

    private static final GardenLoader LOADER = new GardenLoader();

    private static final Garden GARDEN_1 = LOADER.load(examplePath(1));
    private static final Garden GARDEN_2 = LOADER.load(examplePath(2));
    private static final Garden GARDEN_3 = LOADER.load(examplePath(3));

    @ParameterizedTest
    @MethodSource("gardenAndExpectedRegions")
    void shouldGetRegionsInGarden(Garden garden, Collection<Character> expectedPlantRegions) {
        Collection<Region> regions = garden.getRegions();

        assertThat(regions).map(Region::getPlant).containsExactlyInAnyOrderElementsOf(expectedPlantRegions);
    }

    @ParameterizedTest
    @MethodSource("gardenAndPlantAndExpectedAreas")
    void shouldGetRegionAreas(Garden garden, char region, Collection<Integer> expectedAreas) {
        Collection<Integer> areas = garden.getRegionAreas(region);

        assertThat(areas).containsExactlyInAnyOrderElementsOf(expectedAreas);
    }

    @ParameterizedTest
    @MethodSource("gardenAndPlantAndExpectedPerimeters")
    void shouldGetRegionPerimeters(Garden garden, char region, Collection<Integer> expectedPerimeters) {
        Collection<Integer> perimeters = garden.getRegionPerimeters(region);

        assertThat(perimeters).containsExactlyInAnyOrderElementsOf(expectedPerimeters);
    }

    @ParameterizedTest
    @MethodSource("gardenAndExpectedFencePrice")
    void shouldGetGardenFencePrice(Garden garden, int expectedPrice) {
        int price = garden.getFencePrice();

        assertThat(price).isEqualTo(expectedPrice);
    }

    private static Stream<Arguments> gardenAndExpectedRegions() {
        return Stream.of(
                Arguments.of(GARDEN_1, List.of('A', 'B', 'C', 'D', 'E')),
                Arguments.of(GARDEN_2, List.of('O', 'X', 'X', 'X', 'X')),
                Arguments.of(GARDEN_3, List.of('R', 'I', 'C', 'F', 'V', 'J', 'C', 'E', 'I', 'M', 'S')));
    }

    private static Stream<Arguments> gardenAndPlantAndExpectedAreas() {
        return Stream.of(
                Arguments.of(GARDEN_1, 'A', List.of(4)),
                Arguments.of(GARDEN_1, 'B', List.of(4)),
                Arguments.of(GARDEN_1, 'C', List.of(4)),
                Arguments.of(GARDEN_1, 'D', List.of(1)),
                Arguments.of(GARDEN_1, 'E', List.of(3)),
                Arguments.of(GARDEN_2, 'O', List.of(21)),
                Arguments.of(GARDEN_2, 'X', List.of(1, 1, 1, 1)),
                Arguments.of(GARDEN_3, 'R', List.of(12)),
                Arguments.of(GARDEN_3, 'I', List.of(4, 14)),
                Arguments.of(GARDEN_3, 'C', List.of(14, 1)),
                Arguments.of(GARDEN_3, 'F', List.of(10)),
                Arguments.of(GARDEN_3, 'V', List.of(13)),
                Arguments.of(GARDEN_3, 'J', List.of(11)),
                Arguments.of(GARDEN_3, 'E', List.of(13)),
                Arguments.of(GARDEN_3, 'M', List.of(5)),
                Arguments.of(GARDEN_3, 'S', List.of(3)));
    }

    private static Stream<Arguments> gardenAndPlantAndExpectedPerimeters() {
        return Stream.of(
                Arguments.of(GARDEN_1, 'A', List.of(10)),
                Arguments.of(GARDEN_1, 'B', List.of(8)),
                Arguments.of(GARDEN_1, 'C', List.of(10)),
                Arguments.of(GARDEN_1, 'D', List.of(4)),
                Arguments.of(GARDEN_1, 'E', List.of(8)),
                Arguments.of(GARDEN_2, 'O', List.of(36)),
                Arguments.of(GARDEN_2, 'X', List.of(4, 4, 4, 4)),
                Arguments.of(GARDEN_3, 'R', List.of(18)),
                Arguments.of(GARDEN_3, 'I', List.of(8, 22)),
                Arguments.of(GARDEN_3, 'C', List.of(28, 4)),
                Arguments.of(GARDEN_3, 'F', List.of(18)),
                Arguments.of(GARDEN_3, 'V', List.of(20)),
                Arguments.of(GARDEN_3, 'J', List.of(20)),
                Arguments.of(GARDEN_3, 'E', List.of(18)),
                Arguments.of(GARDEN_3, 'M', List.of(12)),
                Arguments.of(GARDEN_3, 'S', List.of(8)));
    }

    private static Stream<Arguments> gardenAndExpectedFencePrice() {
        return Stream.of(
                Arguments.of(GARDEN_1, 140),
                Arguments.of(GARDEN_2, 772),
                Arguments.of(GARDEN_3, 1930),
                Arguments.of(LOADER.load("day-12/puzzle.txt"), 1461806));
    }

    private static String examplePath(int number) {
        return String.format("day-12/example-%d.txt", number);
    }
}
