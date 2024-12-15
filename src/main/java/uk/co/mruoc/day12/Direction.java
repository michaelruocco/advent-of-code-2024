package uk.co.mruoc.day12;

public enum Direction {
    NORTH,
    EAST,
    SOUTH,
    WEST;

    public Plot move(Plot point) {
        return switch (this) {
            case NORTH -> new Plot(point.y - 1, point.x);
            case EAST -> new Plot(point.y, point.x + 1);
            case SOUTH -> new Plot(point.y + 1, point.x);
            default -> new Plot(point.y, point.x - 1);
        };
    }
}
