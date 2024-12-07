package uk.co.mruoc.day6;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class Location {
    final int y;
    final int x;

    public Location north() {
        return new Location(y - 1, x);
    }

    public Location east() {
        return new Location(y, x + 1);
    }

    public Location south() {
        return new Location(y + 1, x);
    }

    public Location west() {
        return new Location(y, x - 1);
    }
}
