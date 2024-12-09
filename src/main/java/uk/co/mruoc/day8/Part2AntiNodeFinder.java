package uk.co.mruoc.day8;

import java.util.Collection;
import java.util.HashSet;
import java.util.stream.IntStream;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Part2AntiNodeFinder implements AntiNodeFinder {

    private final GridBoundsChecker boundsChecker;

    public Part2AntiNodeFinder() {
        this(new GridBoundsChecker());
    }

    @Override
    public Collection<Location> toAntiNodes(Pair pair, int gridSize) {
        Location l1 = pair.l1;
        Location l2 = pair.l2;
        if (l1.x == l2.x) {
            return calculateVerticalLineAntiNodes(l1, gridSize);
        }
        if (l1.y == l2.y) {
            return calculateHorizontalLineAntiNodes(l1, gridSize);
        }
        return calculateDiagonalLineAntiNodes(l1, l2, gridSize);
    }

    private static Collection<Location> calculateVerticalLineAntiNodes(Location l1, int gridSize) {
        return IntStream.range(0, gridSize).mapToObj(y -> new Location(y, l1.x)).toList();
    }

    private static Collection<Location> calculateHorizontalLineAntiNodes(Location l1, int gridSize) {
        return IntStream.range(0, gridSize).mapToObj(x -> new Location(l1.y, x)).toList();
    }

    private Collection<Location> calculateDiagonalLineAntiNodes(Location l1, Location l2, int gridSize) {
        return getAllLinePoints(l1, l2, gridSize);
    }

    private Collection<Location> getAllLinePoints(Location l1, Location l2, int gridSize) {
        int x1 = l1.x;
        int y1 = l1.y;

        int x2 = l2.x;
        int y2 = l2.y;

        // Calculate the differences between the two points
        int dx = x2 - x1;
        int dy = y2 - y1;

        // Calculate the greatest common divisor (GCD) of dx and dy to step through the grid
        int gcd = gcd(Math.abs(dx), Math.abs(dy));
        dx /= gcd; // Normalize the step size for x
        dy /= gcd; // Normalize the step size for y

        int x = x1;
        int y = y1;
        Collection<Location> antiNodes = new HashSet<>();
        antiNodes.add(new Location(y, x));

        // Step in the direction of (x2, y2)
        while (boundsChecker.isInGridBounds(gridSize, y, x)) {
            x += dx;
            y += dy;
            if (x >= 0 && x < gridSize && y >= 0 && y < gridSize) {
                antiNodes.add(new Location(y, x));
            }
        }

        // We also need to extend the line beyond the given points, starting from (x2, y2)
        // Iterate backwards (for the case of reversed input)
        x = x1;
        y = y1;
        while (boundsChecker.isInGridBounds(gridSize, y, x)) {
            x -= dx;
            y -= dy;
            if (x >= 0 && x < gridSize && y >= 0 && y < gridSize) {
                antiNodes.add(new Location(y, x));
            }
        }

        return antiNodes;
    }

    private static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}
