package uk.co.mruoc.day8;

import java.util.Collection;
import uk.co.mruoc.Point;

public interface AntiNodeFinder {
    Collection<Point> toAntiNodes(Pair pair, int gridSize);
}
