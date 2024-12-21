package uk.co.mruoc.day12;

public enum Direction {
    NORTH,
    EAST,
    SOUTH,
    WEST;

    public Plot move(Plot plot) {
        return switch (this) {
            case NORTH -> new Plot(plot.y - 1, plot.x);
            case EAST -> new Plot(plot.y, plot.x + 1);
            case SOUTH -> new Plot(plot.y + 1, plot.x);
            default -> new Plot(plot.y, plot.x - 1);
        };
    }
}
