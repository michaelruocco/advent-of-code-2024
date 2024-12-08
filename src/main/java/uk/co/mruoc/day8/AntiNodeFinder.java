package uk.co.mruoc.day8;

import java.util.ArrayList;
import java.util.Collection;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AntiNodeFinder {

    private final GridBoundsChecker boundsChecker;

    public AntiNodeFinder() {
        this(new GridBoundsChecker());
    }

    public Collection<Location> toAntiNodes(Pair pair, int gridSize) {
        Location l1 = pair.l1;
        Location l2 = pair.l2;

        int dy = l2.y - l1.y;
        int dx = l2.x - l1.x;

        Collection<Location> antiNodes = new ArrayList<>();
        int yP1 = l2.y - 2 * dy;
        int xP1 = l2.x - 2 * dx;
        if (boundsChecker.isInGridBounds(gridSize, yP1, xP1)) {
            antiNodes.add(new Location(yP1, xP1));
        }

        int yP2 = l1.y + 2 * dy;
        int xP2 = l1.x + 2 * dx;
        if (boundsChecker.isInGridBounds(gridSize, yP2, xP2)) {
            antiNodes.add(new Location(yP2, xP2));
        }

        return antiNodes;
    }
}
