package uk.co.mruoc.day6;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.With;
import uk.co.mruoc.Point;

@Builder
public class LabMap {

    private final int height;
    private final int width;

    @With(AccessLevel.PRIVATE)
    private final Set<Point> walls;

    public boolean isWallAt(Point location) {
        return walls.contains(location);
    }

    public LabMap addWallAt(Point location) {
        Set<Point> newWalls = new HashSet<>(walls);
        newWalls.add(location);
        return withWalls(newWalls);
    }

    public boolean exists(Point location) {
        return location.y > -1 && location.y < height && location.x > -1 && location.x < width;
    }

    public String toState(Collection<Point> visited) {
        return IntStream.range(0, height)
                .mapToObj(y -> formatRow(y, visited))
                .collect(Collectors.joining(System.lineSeparator()));
    }

    private String formatRow(int y, Collection<Point> visited) {
        return IntStream.range(0, width)
                .mapToObj(x -> new Point(y, x))
                .map(location -> toToken(location, visited))
                .collect(Collectors.joining());
    }

    private String toToken(Point location, Collection<Point> visited) {
        if (visited.contains(location)) {
            return "X";
        }
        if (isWallAt(location)) {
            return "#";
        }
        return ".";
    }
}
