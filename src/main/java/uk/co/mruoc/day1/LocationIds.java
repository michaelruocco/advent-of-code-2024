package uk.co.mruoc.day1;

import java.util.Collection;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class LocationIds {

    private final Collection<Integer> one;
    private final Collection<Integer> two;
}
