package uk.co.mruoc.day6;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.With;

@AllArgsConstructor
@Builder
@Getter
@EqualsAndHashCode
public class Location {

    private static final char AVAILABLE = '.';
    private static final char OBSTRUCTION = '#';
    private static final char VISITED = 'X';
    private static final char ADDED_OBSTRUCTION = 'O';

    private final Point point;

    @With
    private final char token;

    public boolean isAvailable() {
        return AVAILABLE == token || VISITED == token;
    }

    public boolean isVisited() {
        return VISITED == token;
    }

    public String getKey() {
        return point.getKey();
    }

    public Location toVisited() {
        return withToken(VISITED);
    }

    public Location addObstruction() {
        return withToken(ADDED_OBSTRUCTION);
    }
}
