package uk.co.mruoc.day10;

import java.util.Collection;

public interface PointAccumulator {
    void accumulate(Collection<Point> nextPoints, Collection<Point> allSummits);
}
