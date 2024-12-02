package uk.co.mruoc.day1;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class LocationIdPair {

    private final Integer id1;
    private final Integer id2;

    public Integer calculateDistance() {
        return Math.abs(id1 - id2);
    }
}
