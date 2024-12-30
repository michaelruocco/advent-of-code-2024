package uk.co.mruoc;

import java.util.Set;
import lombok.Builder;
import lombok.Getter;

@Builder
public class Maze {

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
        StringBuilder state = new StringBuilder();
        for (int y = 0; y < height; y++) {
            StringBuilder row = new StringBuilder();
            for (int x = 0; x < width; x++) {
                char token = getToken(new Point(y, x));
                row.append(token);
            }
            row.append(System.lineSeparator());
            state.append(row);
        }
        return state.toString();
    }

    private char getToken(Point point) {
        if (pathAt(point)) {
            if (point.equals(start)) {
                return Token.START;
            } else if (endsAt(point)) {
                return Token.END;
            }
            return Token.FREE;
        }
        return Token.WALL;
    }
}
