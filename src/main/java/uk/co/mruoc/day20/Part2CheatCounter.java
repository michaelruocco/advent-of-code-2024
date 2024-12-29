package uk.co.mruoc.day20;

import java.util.Map;
import uk.co.mruoc.Point;

public class Part2CheatCounter implements CheatCounter {

    @Override
    public long toCheatCount(Map<Point, Long> pathDistances, Point point, long distance, long threshold) {
        return pathDistances.entrySet().stream()
                .filter(entry -> {
                    long pathLength = entry.getKey().getVectorTo(point).getManhattanDistance();
                    long saving = entry.getValue() - distance - pathLength;
                    return pathLength <= 20 && saving >= threshold;
                })
                .count();
    }
}
