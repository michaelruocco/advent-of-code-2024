package uk.co.mruoc.day16;

import java.util.Optional;
import java.util.Set;
import lombok.Builder;
import lombok.Getter;
import uk.co.mruoc.Direction;
import uk.co.mruoc.Point;

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
        return toState(new Moves());
    }

    public String toState(Moves moves) {
        StringBuilder state = new StringBuilder();
        for (int y = 0; y < height; y++) {
            StringBuilder row = new StringBuilder();
            for (int x = 0; x < width; x++) {
                char token = getToken(new Point(y, x), moves);
                row.append(token);
            }
            row.append(System.lineSeparator());
            state.append(row);
        }
        return state.toString();
    }

    private char getToken(Point point, Moves moves) {
        if (pathAt(point)) {
            Optional<Direction> direction = moves.getDirectionAt(point);
            if (direction.isPresent()) {
                return direction.get().token;
            } else if (point.equals(start)) {
                return 'S';
            } else if (endsAt(point)) {
                return 'E';
            }
            return '.';
        }
        return '#';
    }
}
