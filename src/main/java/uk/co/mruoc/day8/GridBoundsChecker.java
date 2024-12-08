package uk.co.mruoc.day8;

import java.util.Arrays;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GridBoundsChecker {

    private final int gridSize;

    public boolean isInGridBounds(int... coordinates) {
        return Arrays.stream(coordinates).allMatch(i -> i > -1 && i < gridSize);
    }
}
