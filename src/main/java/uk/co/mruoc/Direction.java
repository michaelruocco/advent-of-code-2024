package uk.co.mruoc;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
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

    private static final Map<Character, Direction> MAP = toMap();

    public static Direction build(char c) {
        return Optional.ofNullable(MAP.get(c)).orElseThrow();
    }

    public static boolean isDirection(char c) {
        return MAP.containsKey(c);
    }

    private static Map<Character, Direction> toMap() {
        return Arrays.stream(values()).collect(Collectors.toMap(d -> d.token, Function.identity()));
    }
}
