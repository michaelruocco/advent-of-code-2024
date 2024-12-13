package uk.co.mruoc.day12;

import java.util.ArrayList;
import java.util.Collection;
import uk.co.mruoc.GridLoader;

public class GardenLoader {
    public Garden load(String path) {
        char[][] grid = GridLoader.load(path);
        return new Garden(toRegions(grid));
    }

    public static Collection<Region> toRegions(char[][] grid) {
        Collection<Region> regions = new ArrayList<>();
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[0].length; x++) {
                if (!visited[y][x]) {
                    char currentChar = grid[y][x];
                    Region region = new Region(currentChar);
                    dfs(y, x, grid, visited, currentChar, region);
                    regions.add(region);
                }
            }
        }
        return regions;
    }

    private static void dfs(int y, int x, char[][] grid, boolean[][] visited, char c, Region region) {
        visited[y][x] = true;
        region.add(new Plot(y, x));
        int[] directions = {-1, 0, 1, 0, 0, -1, 0, 1}; // up, down, left, right
        for (int i = 0; i < 8; i += 2) {
            int ny = y + directions[i];
            int nx = x + directions[i + 1];
            if (ny >= 0
                    && ny < grid.length
                    && nx >= 0
                    && nx < grid[0].length
                    && !visited[ny][nx]
                    && grid[ny][nx] == c) {
                dfs(ny, nx, grid, visited, c, region);
            }
        }
    }
}
