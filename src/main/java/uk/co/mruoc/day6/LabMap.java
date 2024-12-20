package uk.co.mruoc.day6;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.With;

@RequiredArgsConstructor
@AllArgsConstructor
public class LabMap {

    private final int height;
    private final int width;

    @With(AccessLevel.PRIVATE)
    private final Set<Location> walls;

    @Getter
    private Guard guard;

    public LabMap(char[][] tokens) {
        this.walls = new HashSet<>();
        this.height = tokens.length;
        this.width = tokens[0].length;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                char token = tokens[y][x];
                if ('#' == token || 'O' == token) {
                    walls.add(new Location(y, x));
                } else if (Direction.isDirection(token)) {
                    guard = new Guard(new Location(y, x), Direction.build(token));
                }
            }
        }
    }

    public boolean isWallAt(Location location) {
        return walls.contains(location);
    }

    public long countSingleObstructionsCausingLoop() {
        return guard.patrol(this).getVisitedLocations().stream()
                .map(this::toNewWalls)
                .map(this::withWalls)
                .map(newMap -> guard.patrol(newMap))
                .filter(Result::isStuck)
                .count();
    }

    private Set<Location> toNewWalls(Location location) {
        Set<Location> newWalls = new HashSet<>(walls);
        newWalls.add(location);
        return newWalls;
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
