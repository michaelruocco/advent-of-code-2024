package uk.co.mruoc;

import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Direction {
    NORTH('^'),
    EAST('>'),
    SOUTH('v'),
    WEST('<');

    public final char token;

    public Point move(Point point) {
        return move(point, 1);
    }

    public Point move(Point point, int times) {
        return switch (this) {
            case NORTH -> point.north(times);
            case EAST -> point.east(times);
            case SOUTH -> point.south(times);
            default -> point.west(times);
        };
    }

    public Direction rotateClockwise() {
        return switch (this) {
            case NORTH -> EAST;
            case EAST -> SOUTH;
            case SOUTH -> WEST;
            default -> NORTH;
        };
    }

    public Direction rotateAntiClockwise() {
        return switch (this) {
            case NORTH -> WEST;
            case WEST -> SOUTH;
            case SOUTH -> EAST;
            default -> NORTH;
        };
    }

    public static Direction get(char c) {
        return stream().filter(d -> d.token == c).findFirst().orElseThrow();
    }

    public static boolean isDirection(char c) {
        return stream().map(d -> d.token).toList().contains(c);
    }

    private static Stream<Direction> stream() {
        return Stream.of(Direction.values());
    }
}
