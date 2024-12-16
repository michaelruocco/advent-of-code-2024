package uk.co.mruoc.day15;

import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@ToString
public class Point {
    final int y;
    final int x;

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
