package uk.co.mruoc;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class Point {
    public final int y;
    public final int x;

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public Point north() {
        return new Point(y - 1, x);
    }

    public Point east() {
        return new Point(y, x + 1);
    }

    public Point south() {
        return new Point(y + 1, x);
    }

    public Point west() {
        return new Point(y, x - 1);
    }

    public Point northEast() {
        return new Point(y - 1, x + 1);
    }

    public Point northWest() {
        return new Point(y - 1, x - 1);
    }

    public Point southEast() {
        return new Point(y + 1, x + 1);
    }

    public Point southWest() {
        return new Point(y + 1, x - 1);
    }

    public boolean northOf(Point other) {
        return x == other.x && y - 1 == other.y;
    }

    public boolean eastOf(Point other) {
        return x + 1 == other.x && y == other.y;
    }

    public boolean southOf(Point other) {
        return x == other.x && y + 1 == other.y;
    }

    public boolean westOf(Point other) {
        return x - 1 == other.x && y == other.y;
    }
}
