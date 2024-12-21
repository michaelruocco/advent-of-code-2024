package uk.co.mruoc.day12;

import java.util.ArrayList;
import java.util.Collection;
import uk.co.mruoc.GridLoader;
import uk.co.mruoc.Point;

public class GardenLoader {

    public Garden load(String path) {
        char[][] grid = GridLoader.load(path);
        return new Garden(toRegions(grid));
    }

    private static Collection<Region> toRegions(char[][] grid) {
        Collection<Region> regions = new ArrayList<>();
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[0].length; x++) {
                if (!visited[y][x]) {
                    char currentChar = grid[y][x];
                    Region region = new Region(currentChar);
                    dfs(new Point(y, x), grid, visited, currentChar, region);
                    regions.add(region);
                }
            }
        }
        return regions;
    }

    private static void dfs(Point plot, char[][] grid, boolean[][] visited, char c, Region region) {
        visited[plot.y][plot.x] = true;
        region.add(plot);
        for (Direction direction : Direction.values()) {
            Point nextPlot = direction.move(plot);
            if (isInRegion(nextPlot, grid, visited, c)) {
                dfs(nextPlot, grid, visited, c, region);
            }
        }
    }

    private static boolean isInRegion(Point plot, char[][] grid, boolean[][] visited, char c) {
        return plot.y >= 0
                && plot.y < grid.length
                && plot.x >= 0
                && plot.x < grid[0].length
                && !visited[plot.y][plot.x]
                && grid[plot.y][plot.x] == c;
    }
}
