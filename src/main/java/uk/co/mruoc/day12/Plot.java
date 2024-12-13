package uk.co.mruoc.day12;

import java.util.Collection;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@ToString
public class Plot {
    final int y;
    final int x;

    public int calculatePerimeter(Collection<Plot> otherPlots) {
        int perimeter = 4;
        for (Plot otherPlot : otherPlots) {
            if (isNorthOf(otherPlot)) {
                perimeter--;
            }
            if (isEastOf(otherPlot)) {
                perimeter--;
            }
            if (isSouthOf(otherPlot)) {
                perimeter--;
            }
            if (isWestOf(otherPlot)) {
                perimeter--;
            }
        }
        return perimeter;
    }

    private boolean isNorthOf(Plot otherPlot) {
        return x == otherPlot.x && y + 1 == otherPlot.y;
    }

    private boolean isEastOf(Plot otherPlot) {
        return x + 1 == otherPlot.x && y == otherPlot.y;
    }

    private boolean isSouthOf(Plot otherPlot) {
        return x == otherPlot.x && y - 1 == otherPlot.y;
    }

    private boolean isWestOf(Plot otherPlot) {
        return x - 1 == otherPlot.x && y == otherPlot.y;
    }
}
