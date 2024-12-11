package uk.co.mruoc.day10;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import uk.co.mruoc.file.FileLoader;

public class TrailMapLoader {

    public TrailMap load(String path) {
        Collection<String> lines = FileLoader.loadContentLinesFromClasspath(path);
        return toMap(new ArrayList<>(lines));
    }

    private static TrailMap toMap(List<String> lines) {
        Collection<Point> trailheads = new ArrayList<>();
        int size = lines.size();
        int[][] heights = new int[size][size];
        for (int y = 0; y < size; y++) {
            String line = lines.get(y);
            for (int x = 0; x < size; x++) {
                int height = toInt(line.charAt(x));
                heights[y][x] = height;
                if (isTrailhead(height)) {
                    trailheads.add(new Point(y, x));
                }
            }
        }
        return new TrailMap(heights, trailheads);
    }

    private static int toInt(char c) {
        return c - '0';
    }

    private static boolean isTrailhead(int height) {
        return height == 0;
    }
}
