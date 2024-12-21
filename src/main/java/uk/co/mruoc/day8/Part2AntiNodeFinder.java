package uk.co.mruoc.day8;

import java.util.Collection;
import java.util.HashSet;
import java.util.stream.IntStream;
import lombok.RequiredArgsConstructor;
import uk.co.mruoc.Point;

@RequiredArgsConstructor
public class Part2AntiNodeFinder implements AntiNodeFinder {

    private final GridBoundsChecker boundsChecker;

    public Part2AntiNodeFinder() {
        this(new GridBoundsChecker());
    }

    @Override
    public Collection<Point> toAntiNodes(Pair pair, int gridSize) {
        Point p1 = pair.p1;
        Point p2 = pair.p2;
        if (p1.x == p2.x) {
            return calculateVerticalLineAntiNodes(p1, gridSize);
        }
        if (p1.y == p2.y) {
            return calculateHorizontalLineAntiNodes(p1, gridSize);
        }
        return calculateDiagonalLineAntiNodes(p1, p2, gridSize);
    }

    private static Collection<Point> calculateVerticalLineAntiNodes(Point p1, int gridSize) {
        return IntStream.range(0, gridSize).mapToObj(y -> new Point(y, p1.x)).toList();
    }

    private static Collection<Point> calculateHorizontalLineAntiNodes(Point p1, int gridSize) {
        return IntStream.range(0, gridSize).mapToObj(x -> new Point(p1.y, x)).toList();
    }

    private Collection<Point> calculateDiagonalLineAntiNodes(Point p1, Point p2, int gridSize) {
        return getAllLinePoints(p1, p2, gridSize);
    }

    private Collection<Point> getAllLinePoints(Point p1, Point p2, int gridSize) {
        int x1 = p1.x;
        int y1 = p1.y;

        int x2 = p2.x;
        int y2 = p2.y;

        int dx = x2 - x1;
        int dy = y2 - y1;

        int gcd = gcd(Math.abs(dx), Math.abs(dy));
        dx /= gcd;
        dy /= gcd;

        int x = x1;
        int y = y1;

        Collection<Point> antiNodes = new HashSet<>();
        antiNodes.add(new Point(y, x));

        while (boundsChecker.isInGridBounds(gridSize, y, x)) {
            x += dx;
            y += dy;
            if (x >= 0 && x < gridSize && y >= 0 && y < gridSize) {
                antiNodes.add(new Point(y, x));
            }
        }

        x = x1;
        y = y1;
        while (boundsChecker.isInGridBounds(gridSize, y, x)) {
            x -= dx;
            y -= dy;
            if (x >= 0 && x < gridSize && y >= 0 && y < gridSize) {
                antiNodes.add(new Point(y, x));
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
