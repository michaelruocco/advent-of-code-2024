package uk.co.mruoc.day10;

import java.util.Collection;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import uk.co.mruoc.Point;

@RequiredArgsConstructor
public class TrailMap {

    private final int[][] heights;

    @Getter
    private final Collection<Point> trailheads;

    public int getHeight(Point point) {
        return heights[point.y][point.x];
    }

    public boolean isInBounds(Point point) {
        return point.x > -1 && point.x < heights.length && point.y > -1 && point.y < heights.length;
    }
}
