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
