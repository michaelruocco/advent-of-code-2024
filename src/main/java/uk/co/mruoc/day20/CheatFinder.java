package uk.co.mruoc.day20;

import java.util.Map;
import lombok.RequiredArgsConstructor;
import uk.co.mruoc.Maze;
import uk.co.mruoc.Point;

@RequiredArgsConstructor
public class CheatFinder {

    private final PathDistanceFinder distanceFinder;
    private final CheatCounter counter;

    public CheatFinder(CheatCounter counter) {
        this(new PathDistanceFinder(), counter);
    }

    public long countCheatsThatSave(Maze track, long threshold) {
        Map<Point, Long> pathDistances = distanceFinder.getPathDistances(track);
        return pathDistances.entrySet().stream()
                .map(entry -> counter.toCheatCount(pathDistances, entry.getKey(), entry.getValue(), threshold))
                .mapToLong(i -> i)
                .sum();
    }
}
