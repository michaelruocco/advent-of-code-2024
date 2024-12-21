package uk.co.mruoc.day8;

import java.util.ArrayList;
import java.util.Collection;
import lombok.RequiredArgsConstructor;
import uk.co.mruoc.Point;

@RequiredArgsConstructor
public class Part1AntiNodeFinder implements AntiNodeFinder {

    private final GridBoundsChecker boundsChecker;

    public Part1AntiNodeFinder() {
        this(new GridBoundsChecker());
    }

    @Override
    public Collection<Point> toAntiNodes(Pair pair, int gridSize) {
        Point p1 = pair.p1;
        Point p2 = pair.p2;

        int dy = p2.y - p1.y;
        int dx = p2.x - p1.x;

        Collection<Point> antiNodes = new ArrayList<>();
        int yP1 = p2.y - 2 * dy;
        int xP1 = p2.x - 2 * dx;
        if (boundsChecker.isInGridBounds(gridSize, yP1, xP1)) {
            antiNodes.add(new Point(yP1, xP1));
        }

        int yP2 = p1.y + 2 * dy;
        int xP2 = p1.x + 2 * dx;
        if (boundsChecker.isInGridBounds(gridSize, yP2, xP2)) {
            antiNodes.add(new Point(yP2, xP2));
        }

        return antiNodes;
    }
}
