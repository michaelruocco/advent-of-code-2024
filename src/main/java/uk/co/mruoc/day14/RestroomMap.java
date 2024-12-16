package uk.co.mruoc.day14;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RestroomMap {

    private final int[][] grid;

    public RestroomMap(int height, int width) {
        this(buildPopulatedGrid(height, width));
    }

    public int getHeight() {
        return grid.length;
    }

    public int getWidth() {
        return grid[0].length;
    }

    public void move(Point from, Point to) {
        grid[from.y][from.x]--;
        move(to);
    }

    public void move(Point point) {
        grid[point.y][point.x]++;
    }

    public String getState() {
        return getState(0, getWidth(), 0, getHeight());
    }

    public int getSafetyFactor() {
        int width = getWidth();
        int height = getHeight();
        int midWidth = width / 2;
        int midHeight = height / 2;

        int q1 = getQuadrantRobotCount(0, midWidth, 0, midHeight);
        int q2 = getQuadrantRobotCount(midWidth + 1, width, 0, midHeight);
        int q3 = getQuadrantRobotCount(0, midWidth, midHeight + 1, height);
        int q4 = getQuadrantRobotCount(midWidth + 1, width, midHeight + 1, height);

        return q1 * q2 * q3 * q4;
    }

    public boolean containsHorizontalLineWithMinLength(int minLength) {
        for (int y = 0; y < getHeight(); y++) {
            int count = 0;
            for (int x = 0; x < getWidth(); x++) {
                if (grid[y][x] != 0) {
                    count++;
                } else {
                    count = 0;
                }
                if (count > minLength) {
                    return true;
                }
            }
        }
        return false;
    }

    private int getQuadrantRobotCount(int startX, int endX, int startY, int endY) {
        int count = 0;
        for (int y = startY; y < endY; y++) {
            for (int x = startX; x < endX; x++) {
                count += grid[y][x];
            }
        }
        return count;
    }

    private String getState(int startX, int endX, int startY, int endY) {
        StringBuilder state = new StringBuilder();
        for (int y = startY; y < endY; y++) {
            StringBuilder row = new StringBuilder();
            for (int x = startX; x < endX; x++) {
                int i = grid[y][x];
                row.append(toChar(i));
            }
            row.append(System.lineSeparator());
            state.append(row);
        }
        return state.toString();
    }

    private static char toChar(int i) {
        if (i == 0) {
            return '.';
        }
        return (char) (i + '0');
    }

    private static int[][] buildPopulatedGrid(int height, int width) {
        int[][] grid = new int[height][width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                grid[y][x] = 0;
            }
        }
        return grid;
    }
}
