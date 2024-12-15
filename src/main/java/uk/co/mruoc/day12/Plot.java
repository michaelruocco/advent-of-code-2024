package uk.co.mruoc.day12;

import java.util.Collection;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@EqualsAndHashCode
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

    public int calculateCorners(Collection<Plot> otherPlots) {
        return calculateInnerCorners(otherPlots) + calculateOuterCorners(otherPlots);
    }

    private int calculateOuterCorners(Collection<Plot> otherPlots) {
        int outerCorners = 0;
        if (containsNone(otherPlots, northOf(), northEastOf(), eastOf())) {
            outerCorners++;
        }
        if (containsNone(otherPlots, eastOf(), southEastOf(), southOf())) {
            outerCorners++;
        }
        if (containsNone(otherPlots, southOf(), southWestOf(), westOf())) {
            outerCorners++;
        }
        if (containsNone(otherPlots, westOf(), northWestOf(), northOf())) {
            outerCorners++;
        }
        return outerCorners;
    }

    private int calculateInnerCorners(Collection<Plot> otherPlots) {
        int innerCorners = 0;
        if (!otherPlots.contains(eastOf()) && otherPlots.contains(northEastOf())) {
            innerCorners++;
        }
        if (!otherPlots.contains(eastOf()) && otherPlots.contains(southEastOf())) {
            innerCorners++;
        }
        if (!otherPlots.contains(westOf()) && otherPlots.contains(northWestOf())) {
            innerCorners++;
        }
        if (!otherPlots.contains(westOf()) && otherPlots.contains(southWestOf())) {
            innerCorners++;
        }
        return innerCorners;
    }

    private boolean isNorthOf(Plot otherPlot) {
        return x == otherPlot.x && y - 1 == otherPlot.y;
    }

    private boolean isEastOf(Plot otherPlot) {
        return x + 1 == otherPlot.x && y == otherPlot.y;
    }

    private boolean isSouthOf(Plot otherPlot) {
        return x == otherPlot.x && y + 1 == otherPlot.y;
    }

    private boolean isWestOf(Plot otherPlot) {
        return x - 1 == otherPlot.x && y == otherPlot.y;
    }

    private Plot northOf() {
        return new Plot(y - 1, x);
    }

    private Plot northEastOf() {
        return new Plot(y - 1, x + 1);
    }

    private Plot northWestOf() {
        return new Plot(y - 1, x - 1);
    }

    private Plot eastOf() {
        return new Plot(y, x + 1);
    }

    private Plot southEastOf() {
        return new Plot(y + 1, x + 1);
    }

    private Plot southWestOf() {
        return new Plot(y + 1, x - 1);
    }

    private Plot southOf() {
        return new Plot(y + 1, x);
    }

    private Plot westOf() {
        return new Plot(y, x - 1);
    }

    private static boolean containsNone(Collection<Plot> otherPlots, Plot... plotsToFind) {
        return otherPlots.stream().noneMatch(p -> List.of(plotsToFind).contains(p));
    }
}
