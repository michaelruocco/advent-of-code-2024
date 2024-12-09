package uk.co.mruoc.day8;

import java.util.Arrays;

public class GridBoundsChecker {

    public boolean isInGridBounds(int gridSize, Location location) {
        return isInGridBounds(gridSize, location.y, location.x);
    }

    public boolean isInGridBounds(int gridSize, int... coordinates) {
        return Arrays.stream(coordinates).allMatch(i -> i > -1 && i < gridSize);
    }
}
