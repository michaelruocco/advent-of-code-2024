package uk.co.mruoc.day15;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class Point {
    final int y;
    final int x;

    public Point add(Point other) {
        return new Point(y + other.y, x + other.x);
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
}
