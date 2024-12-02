package uk.co.mruoc.day1;

import java.util.Collection;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class LocationIds {

    private final Collection<Integer> one;
    private final Collection<Integer> two;

    public LocationIds sort() {
        return LocationIds.builder()
                .one(one.stream().sorted().toList())
                .two(two.stream().sorted().toList())
                .build();
    }
}
