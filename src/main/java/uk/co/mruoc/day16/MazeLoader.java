package uk.co.mruoc.day16;

import java.util.HashSet;
import java.util.Set;
import uk.co.mruoc.GridLoader;
import uk.co.mruoc.Point;

public class MazeLoader {

    public Maze load(String path) {
        char[][] tokens = GridLoader.load(path);
        return toMaze(tokens);
    }

    private static Maze toMaze(char[][] tokens) {
        int height = tokens.length;
        int width = tokens[0].length;
        Set<Point> paths = new HashSet<>();
        Point start = null;
        Point end = null;
        for (int y = 0; y < tokens.length; y++) {
            for (int x = 0; x < tokens[0].length; x++) {
                char token = tokens[y][x];
                if (token != '#') {
                    Point point = new Point(y, x);
                    paths.add(point);
                    if (token == 'S') {
                        start = point;
                    } else if (token == 'E') {
                        end = point;
                    }
                }
            }
        }
        return new Maze(height, width, paths, start, end);
    }
}
