package uk.co.mruoc.day12;

import java.util.Collection;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import uk.co.mruoc.Point;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PerimeterCalculator {

    public static int calculatePerimeter(Point point, Collection<Point> otherPoints) {
        int perimeter = 4;
        for (Point other : otherPoints) {
            if (point.northOf(other)) {
                perimeter--;
            }
            if (point.eastOf(other)) {
                perimeter--;
            }
            if (point.southOf(other)) {
                perimeter--;
            }
            if (point.westOf(other)) {
                perimeter--;
            }
        }
        return perimeter;
    }
}
