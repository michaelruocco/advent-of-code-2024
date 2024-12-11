package uk.co.mruoc.day10;

import java.util.Collection;

public interface PointFinder {
    Collection<Point> find(TrailMap map, Collection<Point> points, Collection<Point> accumulator, int height);
}
