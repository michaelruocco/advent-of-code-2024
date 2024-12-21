package uk.co.mruoc.day6;

import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import uk.co.mruoc.Point;

public class Grid {

    private final char[][] tokens;

    @Getter
    private final int width;

    @Getter
    private final int height;

    public Grid(char[][] tokens) {
        this.tokens = tokens;
        this.height = tokens.length;
        this.width = tokens[0].length;
    }

    public Set<Point> getWalls() {
        Set<Point> walls = new HashSet<>();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (isWall(tokens[y][x])) {
                    walls.add(new Point(y, x));
                }
            }
        }
        return walls;
    }

    public Guard findGuard() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                char token = tokens[y][x];
                if (Direction.isDirection(token)) {
                    return new Guard(new Point(y, x), Direction.build(token));
                }
            }
        }
        throw new IllegalStateException("guard not found in grid");
    }

    private static boolean isWall(char token) {
        return '#' == token || 'O' == token;
    }
}
