package uk.co.mruoc.day10;

import uk.co.mruoc.Point;

public enum Direction {
    NORTH,
    EAST,
    SOUTH,
    WEST;

    public Point move(Point point) {
        return switch (this) {
            case NORTH -> new Point(point.y - 1, point.x);
            case EAST -> new Point(point.y, point.x + 1);
            case SOUTH -> new Point(point.y + 1, point.x);
            default -> new Point(point.y, point.x - 1);
        };
    }
}
