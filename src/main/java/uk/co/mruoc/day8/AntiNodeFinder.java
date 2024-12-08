package uk.co.mruoc.day8;

import java.util.Collection;

public interface AntiNodeFinder {
    Collection<Location> toAntiNodes(Pair pair, int gridSize);
}
