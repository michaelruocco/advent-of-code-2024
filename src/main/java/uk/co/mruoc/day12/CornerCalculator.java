package uk.co.mruoc.day12;

import java.util.Collection;
import java.util.List;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import uk.co.mruoc.Point;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CornerCalculator {

    public static int calculateCorners(Point point, Collection<Point> others) {
        return calculateInnerCorners(point, others) + calculateOuterCorners(point, others);
    }

    private static int calculateInnerCorners(Point point, Collection<Point> others) {
        int innerCorners = 0;
        if (!others.contains(point.east()) && others.contains(point.northEast())) {
            innerCorners++;
        }
        if (!others.contains(point.east()) && others.contains(point.southEast())) {
            innerCorners++;
        }
        if (!others.contains(point.west()) && others.contains(point.northWest())) {
            innerCorners++;
        }
        if (!others.contains(point.west()) && others.contains(point.southWest())) {
            innerCorners++;
        }
        return innerCorners;
    }

    private static int calculateOuterCorners(Point point, Collection<Point> others) {
        int outerCorners = 0;
        if (containsNone(others, point.north(), point.northEast(), point.east())) {
            outerCorners++;
        }
        if (containsNone(others, point.east(), point.southEast(), point.south())) {
            outerCorners++;
        }
        if (containsNone(others, point.south(), point.southWest(), point.west())) {
            outerCorners++;
        }
        if (containsNone(others, point.west(), point.northWest(), point.north())) {
            outerCorners++;
        }
        return outerCorners;
    }

    private static boolean containsNone(Collection<Point> others, Point... pointsToFind) {
        return others.stream().noneMatch(other -> List.of(pointsToFind).contains(other));
    }
}
