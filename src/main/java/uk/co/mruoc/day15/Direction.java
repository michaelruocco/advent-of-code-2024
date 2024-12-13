package uk.co.mruoc.day15;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Direction {
    NORTH('^'),
    EAST('>'),
    SOUTH('v'),
    WEST('<');

    final char token;

    public Point move(Point point) {
        return switch (this) {
            case NORTH -> point.north();
            case EAST -> point.east();
            case SOUTH -> point.south();
            default -> point.west();
        };
    }

    public static Direction build(char c) {
        return switch (c) {
            case '^' -> NORTH;
            case '>' -> EAST;
            case 'v' -> SOUTH;
            default -> WEST;
        };
    }
}
