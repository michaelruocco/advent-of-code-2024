package uk.co.mruoc.day10;

import java.util.Collection;

public class ScorePointAccumulator implements PointAccumulator {

    @Override
    public void accumulate(Collection<Point> nextPoints, Collection<Point> allSummits) {
        for (Point nextPoint : nextPoints) {
            if (allSummits.stream().noneMatch(p -> p.matches(nextPoint))) {
                allSummits.add(nextPoint);
            }
        }
    }
}
