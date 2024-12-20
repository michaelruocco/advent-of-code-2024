package uk.co.mruoc.day6;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.With;

@RequiredArgsConstructor
public class LabMap {

    private final int height;
    private final int width;

    @With(AccessLevel.PRIVATE)
    private final Set<Location> walls;

    public LabMap(Grid grid) {
        this(grid.getHeight(), grid.getWidth(), grid.getWalls());
    }

    public boolean isWallAt(Location location) {
        return walls.contains(location);
    }

    public LabMap addWallAt(Location location) {
        Set<Location> newWalls = new HashSet<>(walls);
        newWalls.add(location);
        return withWalls(newWalls);
    }

    public boolean exists(Location location) {
        return location.y > -1 && location.y < height && location.x > -1 && location.x < width;
    }

    public String toState(Collection<Location> visited) {
        return IntStream.range(0, height)
                .mapToObj(y -> formatRow(y, visited))
                .collect(Collectors.joining(System.lineSeparator()));
    }

    private String formatRow(int y, Collection<Location> visited) {
        return IntStream.range(0, width)
                .mapToObj(x -> new Location(y, x))
                .map(location -> toToken(location, visited))
                .collect(Collectors.joining());
    }

    private String toToken(Location location, Collection<Location> visited) {
        if (visited.contains(location)) {
            return "X";
        }
        if (isWallAt(location)) {
            return "#";
        }
        return ".";
    }
}
