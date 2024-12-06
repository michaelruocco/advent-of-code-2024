package uk.co.mruoc.day6;

import java.util.Collection;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.With;

@RequiredArgsConstructor
@Builder
@Getter
public class Guard {

    private static final char NORTH = '^';
    private static final char EAST = '>';
    private static final char SOUTH = 'V';
    private static final char WEST = '<';

    private static final Collection<Character> ALL = List.of(NORTH, EAST, SOUTH, WEST);

    @With
    private final Location location;

    @With
    private final char direction;

    public static boolean isGuardToken(char token) {
        return ALL.contains(token);
    }

    public Guard(Location location) {
        this(location, location.getToken());
    }

    public String getNextLocationKey() {
        return getNextPoint(direction).getKey();
    }

    public Point getNextPoint(char nextDirection) {
        Point point = location.getPoint();
        return switch (nextDirection) {
            case NORTH -> point.northOf();
            case EAST -> point.eastOf();
            case SOUTH -> point.southOf();
            default -> point.westOf();
        };
    }

    public Guard rotate() {
        return switch (direction) {
            case NORTH -> new Guard(location, EAST);
            case EAST -> new Guard(location, SOUTH);
            case SOUTH -> new Guard(location, WEST);
            default -> new Guard(location, NORTH);
        };
    }
}
