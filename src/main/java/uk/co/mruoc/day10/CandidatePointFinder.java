package uk.co.mruoc.day10;

import java.util.Arrays;
import java.util.Collection;
import lombok.RequiredArgsConstructor;
import uk.co.mruoc.Direction;
import uk.co.mruoc.Point;

@RequiredArgsConstructor
public class CandidatePointFinder {

    public Collection<Point> find(TrailMap map, Point point, int height) {
        return Arrays.stream(Direction.values())
                .map(direction -> direction.move(point))
                .filter(map::isInBounds)
                .filter(nextPoint -> map.getHeight(nextPoint) == height)
                .toList();
    }
}
