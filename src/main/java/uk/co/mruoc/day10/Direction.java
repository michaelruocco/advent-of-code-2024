package uk.co.mruoc.day10;

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

    public Direction rotate() {
        return switch (this) {
            case NORTH -> EAST;
            case EAST -> SOUTH;
            case SOUTH -> WEST;
            default -> NORTH;
        };
    }
}
