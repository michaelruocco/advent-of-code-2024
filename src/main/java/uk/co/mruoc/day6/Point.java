package uk.co.mruoc.day6;

import lombok.Builder;
import lombok.RequiredArgsConstructor;

@Builder
@RequiredArgsConstructor
public class Point {

    final int x;
    final int y;

    public Point northOf() {
        return new Point(x, y - 1);
    }

    public Point eastOf() {
        return new Point(x + 1, y);
    }

    public Point southOf() {
        return new Point(x, y + 1);
    }

    public Point westOf() {
        return new Point(x - 1, y);
    }

    public String getKey() {
        return String.format("%d-%d", x, y);
    }
}
