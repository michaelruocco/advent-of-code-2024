package uk.co.mruoc.day8;

import java.util.*;

public class LinePointsCalculator {

    public static void main(String[] args) {
        // Example input points
        int x1 = 1, y1 = 2;
        int x2 = 3, y2 = 1;

        // Get all points along the line on the 10x10 grid
        List<int[]> pointsOnLine = getAllLinePoints(x1, y1, x2, y2, 10, 10);

        // Print the result
        System.out.println("Points from (1, 2) to (3, 1):");
        for (int[] point : pointsOnLine) {
            System.out.println(Arrays.toString(point));
        }

        System.out.println("------");

        // Reversed points (3, 1) and (1, 2)
        x1 = 3;
        y1 = 1;
        x2 = 1;
        y2 = 2;

        // Get all points along the line on the 10x10 grid
        pointsOnLine = getAllLinePoints(x1, y1, x2, y2, 10, 10);

        // Print the result
        System.out.println("Points from (3, 1) to (1, 2):");
        for (int[] point : pointsOnLine) {
            System.out.println(Arrays.toString(point));
        }
    }

    public static List<int[]> getAllLinePoints(int x1, int y1, int x2, int y2, int gridSizeX, int gridSizeY) {
        List<int[]> points = new ArrayList<>();

        // Ensure we process the line from left to right
        if (x1 > x2 || (x1 == x2 && y1 > y2)) {
            // Swap points to ensure we always move left to right
            int tempX = x1, tempY = y1;
            x1 = x2;
            y1 = y2;
            x2 = tempX;
            y2 = tempY;
        }

        // Step 1: Calculate differences in x and y
        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);
        int sx = (x1 < x2) ? 1 : -1;
        int sy = (y1 < y2) ? 1 : -1;

        // Step 2: Error term
        int err = dx - dy;

        // Step 3: Traverse the line
        while (x1 != x2 || y1 != y2) {
            if (x1 >= 0 && x1 < gridSizeX && y1 >= 0 && y1 < gridSizeY) {
                points.add(new int[] {x1, y1});
            }

            int e2 = err * 2;
            if (e2 > -dy) {
                err -= dy;
                x1 += sx;
            }
            if (e2 < dx) {
                err += dx;
                y1 += sy;
            }
        }

        // Add the last point
        if (x1 >= 0 && x1 < gridSizeX && y1 >= 0 && y1 < gridSizeY) {
            points.add(new int[] {x1, y1});
        }

        return points;
    }

    /**
     * This method calculates all integer grid points along the line passing through two given points
     * within the bounds of a 10x10 grid.
     *
     * @param x1, y1 Starting point of the line
     * @param x2, y2 Ending point of the line (used to calculate the slope)
     * @param gridSizeX, gridSizeY The size of the grid
     * @return List of integer points (x, y) along the line
     */
    public static List<int[]> getAllLinePoints1(int x1, int y1, int x2, int y2, int gridSizeX, int gridSizeY) {
        List<int[]> points = new ArrayList<>();

        // Calculate the differences between the two points
        int dx = x2 - x1;
        int dy = y2 - y1;

        // Calculate the greatest common divisor (GCD) of dx and dy to step through the grid
        int gcd = gcd(Math.abs(dx), Math.abs(dy));
        dx /= gcd; // Normalize the step size for x
        dy /= gcd; // Normalize the step size for y

        // Start at the first point
        int x = x1;
        int y = y1;

        // Add the starting point
        points.add(new int[] {x, y});

        // Step in the direction of (x2, y2)
        while (x != x2 || y != y2) {
            // Update coordinates
            x += dx;
            y += dy;

            // Add the point if within bounds
            if (x >= 0 && x < gridSizeX && y >= 0 && y < gridSizeY) {
                points.add(new int[] {x, y});
            }
        }

        // We also need to extend the line beyond the given points, starting from (x2, y2)
        // Iterate backwards (for the case of reversed input)
        x = x1;
        y = y1;

        while (x != x2 || y != y2) {
            x -= dx;
            y -= dy;
            if (x >= 0 && x < gridSizeX && y >= 0 && y < gridSizeY) {
                points.add(0, new int[] {x, y});
            }
        }

        return points;
    }

    /**
     * This method calculates the greatest common divisor (GCD) using the Euclidean algorithm.
     *
     * @param a First number
     * @param b Second number
     * @return GCD of a and b
     */
    public static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}
