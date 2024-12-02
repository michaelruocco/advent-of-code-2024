package uk.co.mruoc.day1;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

class LocationIdPairsTest {

    @Test
    void shouldSumDistanceBetweenAllPairs() {
        LocationIdPair pair1 = givenPairWithDistance(1);
        LocationIdPair pair2 = givenPairWithDistance(2);
        LocationIdPairs pairs = new LocationIdPairs(pair1, pair2);

        int distance = pairs.calculateTotalDistance();

        assertThat(distance).isEqualTo(3);
    }

    private LocationIdPair givenPairWithDistance(int distance) {
        LocationIdPair pair = mock(LocationIdPair.class);
        when(pair.calculateDistance()).thenReturn(distance);
        return pair;
    }
}
