package uk.co.mruoc.day12;

import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@ToString
public class Plot {
    final int y;
    final int x;

    public boolean adjacentTo(Plot otherPlot) {
        return horizontallyAdjacent(otherPlot) || verticallyAdjacent(otherPlot);
    }

    private boolean horizontallyAdjacent(Plot otherPlot) {
        return y == otherPlot.y && (x == otherPlot.x - 1 || x == otherPlot.x + 1);
    }

    private boolean verticallyAdjacent(Plot otherPlot) {
        return x == otherPlot.x && (y == otherPlot.y - 1 || y == otherPlot.y + 1);
    }
}
