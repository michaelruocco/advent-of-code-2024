package uk.co.mruoc.day16;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Result {

    private final int lowestScore;
    private final int bestPathLocations;
}
