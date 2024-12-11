package uk.co.mruoc.day10;

import java.util.Collection;

public class RatingPointAccumulator implements PointAccumulator {

    @Override
    public void accumulate(Collection<Point> nextPoints, Collection<Point> allSummits) {
        allSummits.addAll(nextPoints);
    }
}
