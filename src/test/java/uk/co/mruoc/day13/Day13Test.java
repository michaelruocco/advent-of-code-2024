package uk.co.mruoc.day13;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class Day13Test {

    private static final ClawMachinesLoader LOADER = new ClawMachinesLoader();

    @ParameterizedTest
    @CsvSource({"day-13/example-1.txt,480", "day-13/example-2.txt,875318608908", "day-13/puzzle.txt,36250"})
    void shouldCalculateCost(String path, String expectedCost) {
        ClawMachines machines = LOADER.load(path);

        long cost = machines.calculateCost();

        assertThat(cost).isEqualTo(Long.parseLong(expectedCost));
    }

    @Test
    void shouldCalculatePart2PuzzleCost() {
        ClawMachinesLoader part2Loader = new ClawMachinesLoader(Prize::toPart2);
        ClawMachines machines = part2Loader.load("day-13/puzzle.txt");

        long cost = machines.calculateCost();

        assertThat(cost).isEqualTo(83232379451012L);
    }
}
