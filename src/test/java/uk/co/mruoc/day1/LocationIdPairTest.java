package uk.co.mruoc.day1;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class LocationIdPairTest {

    @ParameterizedTest
    @MethodSource("valuesAndExpectedDistance")
    void shouldCalculateAbsoluteDistanceBetweenTwoValues(int value1, int value2, int expectedDistance) {
        LocationIdPair pair = new LocationIdPair(value1, value2);

        int distance = pair.calculateDistance();

        assertThat(distance).isEqualTo(expectedDistance);
    }

    private static Stream<Arguments> valuesAndExpectedDistance() {
        return Stream.of(
                Arguments.of(10, 0, 10),
                Arguments.of(0, 5, 5),
                Arguments.of(7, 3, 4),
                Arguments.of(-1, -2, 1),
                Arguments.of(-5, 20, 25));
    }
}
