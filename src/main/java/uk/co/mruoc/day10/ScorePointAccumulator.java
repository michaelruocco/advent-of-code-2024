package uk.co.mruoc.day10;

import java.util.Collection;
import uk.co.mruoc.Point;

public class ScorePointAccumulator implements PointAccumulator {

    @Override
    public void accumulate(Collection<Point> nextPoints, Collection<Point> allSummits) {
        for (Point nextPoint : nextPoints) {
            if (allSummits.stream().noneMatch(p -> p.equals(nextPoint))) {
                allSummits.add(nextPoint);
            }
        }
    }
}
