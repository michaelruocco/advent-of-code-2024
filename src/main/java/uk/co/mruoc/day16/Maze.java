package uk.co.mruoc.day16;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import lombok.Builder;
import lombok.Getter;
import uk.co.mruoc.Point;

@Builder
public class Maze {

    public static final char WALL = '#';
    public static final char START = 'S';
    public static final char END = 'E';

    private final int height;
    private final int width;
    private final Set<Point> paths;

    @Getter
    private final Point start;

    private final Point end;

    public boolean endsAt(Point point) {
        return end.equals(point);
    }

    public boolean pathAt(Point point) {
        return paths.contains(point);
    }

    public String toState() {
        return toState(new ArrayList<>());
    }

    public String toState(Collection<Point> path) {
        StringBuilder state = new StringBuilder();
        for (int y = 0; y < height; y++) {
            StringBuilder row = new StringBuilder();
            for (int x = 0; x < width; x++) {
                char token = getToken(new Point(y, x), path);
                row.append(token);
            }
            row.append(System.lineSeparator());
            state.append(row);
        }
        return state.toString();
    }

    private char getToken(Point point, Collection<Point> path) {
        if (pathAt(point)) {
            if (point.equals(start)) {
                return START;
            } else if (endsAt(point)) {
                return END;
            } else if (path.contains(point)) {
                return 'O';
            }
            return '.';
        }
        return WALL;
    }
}
