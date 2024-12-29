package uk.co.mruoc.day20;

import java.util.Map;
import uk.co.mruoc.Point;

public interface CheatCounter {
    long toCheatCount(Map<Point, Long> pathDistances, Point point, long distance, long threshold);
}
