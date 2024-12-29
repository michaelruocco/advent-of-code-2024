package uk.co.mruoc.day20;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;
import uk.co.mruoc.Direction;
import uk.co.mruoc.Point;

public class PathDistanceFinder {

    public Map<Point, Long> getPathDistances(Track track) {
        Point location = track.getStart();
        long distance = 0;
        Map<Point, Long> pathDistances = new HashMap<>(Map.of(location, distance));
        while (!track.endsAt(location)) {
            location = toNextLocation(location, pathDistances.keySet(), track);
            distance++;
            pathDistances.put(location, distance);
        }
        return pathDistances;
    }

    private static Point toNextLocation(Point location, Collection<Point> visited, Track track) {
        return Stream.of(Direction.values())
                .map(direction -> direction.move(location))
                .filter(candidateLocation -> !visited.contains(candidateLocation) && track.pathAt(candidateLocation))
                .findFirst()
                .orElseThrow();
    }
}
