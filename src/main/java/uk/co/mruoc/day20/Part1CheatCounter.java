package uk.co.mruoc.day20;

import java.util.Arrays;
import java.util.Map;
import uk.co.mruoc.Direction;
import uk.co.mruoc.Point;

public class Part1CheatCounter implements CheatCounter {

    @Override
    public long toCheatCount(Map<Point, Long> pathDistances, Point point, long distance, long threshold) {
        return Arrays.stream(Direction.values())
                .map(direction -> direction.move(point, 2))
                .filter(cheat -> pathDistances.getOrDefault(cheat, 0L) - distance - 2 >= threshold)
                .count();
    }
}
