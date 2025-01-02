package uk.co.mruoc.day23;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class Day23Test {

    private static final String EXAMPLE_PATH = "day-23/example-1.txt";
    private static final String PUZZLE_PATH = "day-23/puzzle.txt";

    private final ComputerLoader loader = new ComputerLoader();

    private final ComputerFinder finder = new ComputerFinder();

    @ParameterizedTest
    @MethodSource("pathAndExpectedResult")
    void shouldFindTripletsWithOneComputerBeginningWithT(String path, int expectedResult) {
        Map<String, Collection<String>> connectedComputers = loader.load(path);

        Collection<Collection<String>> triplets = finder.findUniqueTriplets(connectedComputers);

        assertThat(triplets).hasSize(expectedResult);
    }

    @ParameterizedTest
    @MethodSource("pathAndExpectedPassword")
    void shouldFindLanPartyPassword(String path, String expectedPassword) {
        Map<String, Collection<String>> connectedComputers = loader.load(path);

        String password = finder.findLanPartyPassword(connectedComputers);

        assertThat(password).isEqualTo(expectedPassword);
    }

    private static Stream<Arguments> pathAndExpectedResult() {
        return Stream.of(Arguments.of(EXAMPLE_PATH, 7), Arguments.of(PUZZLE_PATH, 1248));
    }

    private static Stream<Arguments> pathAndExpectedPassword() {
        return Stream.of(
                Arguments.of(EXAMPLE_PATH, "co,de,ka,ta"),
                Arguments.of(PUZZLE_PATH, "aa,cf,cj,cv,dr,gj,iu,jh,oy,qr,xr,xy,zb"));
    }
}
