package uk.co.mruoc.day1;

import java.util.List;
import java.util.stream.IntStream;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class LocationIds {

    private final List<Integer> one;
    private final List<Integer> two;

    public LocationIdPairs toPairs() {
        return new LocationIdPairs(
                IntStream.range(0, one.size()).mapToObj(this::toPair).toList());
    }

    public LocationIds sort() {
        return LocationIds.builder()
                .one(one.stream().sorted().toList())
                .two(two.stream().sorted().toList())
                .build();
    }

    private LocationIdPair toPair(int index) {
        return new LocationIdPair(one.get(index), two.get(index));
    }
}
