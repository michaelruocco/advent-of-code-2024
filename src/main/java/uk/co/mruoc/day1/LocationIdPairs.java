package uk.co.mruoc.day1;

import java.util.Iterator;
import java.util.List;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class LocationIdPairs implements Iterable<LocationIdPair> {

    private final List<LocationIdPair> pairs;

    public LocationIdPairs(LocationIdPair... pairs) {
        this(List.of(pairs));
    }

    @Override
    public Iterator<LocationIdPair> iterator() {
        return pairs.iterator();
    }

    public Integer calculateTotalDistance() {
        return pairs.stream().mapToInt(LocationIdPair::calculateDistance).sum();
    }
}
