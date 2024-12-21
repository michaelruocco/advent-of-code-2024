package uk.co.mruoc.day12;

import uk.co.mruoc.Point;

public enum Direction {
    NORTH,
    EAST,
    SOUTH,
    WEST;

    public Point move(Point plot) {
        return switch (this) {
            case NORTH -> plot.north();
            case EAST -> plot.east();
            case SOUTH -> plot.south();
            default -> plot.west();
        };
    }
}
