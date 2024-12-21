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
        return switch (this) {
            case NORTH -> point.north();
            case EAST -> point.east();
            case SOUTH -> point.south();
            default -> point.west();
        };
    }

    public Direction rotate() {
        return switch (this) {
            case NORTH -> EAST;
            case EAST -> SOUTH;
            case SOUTH -> WEST;
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
