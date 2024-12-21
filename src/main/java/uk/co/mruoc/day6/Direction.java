package uk.co.mruoc.day6;

import java.util.Set;
import lombok.RequiredArgsConstructor;
import uk.co.mruoc.Point;

@RequiredArgsConstructor
public enum Direction {
    NORTH('^'),
    EAST('>'),
    SOUTH('v'),
    WEST('<');

    final char token;

    public Point move(Point location) {
        return switch (this) {
            case NORTH -> location.north();
            case EAST -> location.east();
            case SOUTH -> location.south();
            default -> location.west();
        };
    }

    public Direction rotate() {
        return switch (this) {
            case NORTH -> Direction.EAST;
            case EAST -> Direction.SOUTH;
            case SOUTH -> Direction.WEST;
            default -> Direction.NORTH;
        };
    }

    public static boolean isDirection(char c) {
        return Set.of('^', '>', 'v', '<').contains(c);
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
