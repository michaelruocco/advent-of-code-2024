package uk.co.mruoc.day15;

import java.util.Optional;

public class WarehouseMap {

    private static final char ROBOT = '@';
    private static final char WALL = '#';
    private static final char FREE = '.';
    private static final char BOX = 'O';
    private static final char EXPANDED_BOX = '[';

    private final char[][] grid;
    private Point robotLocation;

    public WarehouseMap(char[][] grid) {
        this.grid = grid;
        this.robotLocation = findRobotLocation(grid);
    }

    public void move(Directions directions) {
        while (!directions.isEmpty()) {
            Direction direction = directions.next();
            move(direction);
        }
    }

    public void move(Direction direction) {
        switch (direction) {
            case NORTH -> moveNorth();
            case EAST -> moveEast();
            case SOUTH -> moveSouth();
            default -> moveWest();
        }
    }

    public String getState() {
        StringBuilder state = new StringBuilder();
        for (char[] rowChars : grid) {
            StringBuilder row = new StringBuilder();
            for (char c : rowChars) {
                row.append(c);
            }
            row.append(System.lineSeparator());
            state.append(row);
        }
        return state.toString();
    }

    public int sumOfAllBoxesGPS() {
        int sum = 0;
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[0].length; x++) {
                char c = grid[y][x];
                if (c == BOX || c == EXPANDED_BOX) {
                    sum += toGPS(y, x);
                }
            }
        }
        return sum;
    }

    public WarehouseMap expand() {
        char[][] copy = new char[grid.length][grid[0].length * 2];
        for (int y = 0; y < grid.length; y++) {
            int copyX = 0;
            for (int x = 0; x < grid[0].length; x++) {
                char token = grid[y][x];
                char[] expandedToken = expand(token);
                copy[y][copyX] = expandedToken[0];
                copyX++;
                copy[y][copyX] = expandedToken[1];
                copyX++;
            }
        }
        return new WarehouseMap(copy);
    }

    private void moveNorth() {
        getNextNorthFreeLocationBeforeWall().ifPresent(this::moveNorthUpTo);
    }

    private Optional<Point> getNextNorthFreeLocationBeforeWall() {
        int x = robotLocation.x;
        for (int y = robotLocation.y - 1; y > 0 && notWall(y, x); y--) {
            if (isFree(y, x)) {
                return Optional.of(new Point(y, x));
            }
        }
        return Optional.empty();
    }

    private void moveNorthUpTo(Point next) {
        int x = robotLocation.x;
        for (int y = next.y; y < robotLocation.y; y++) {
            char temp = grid[y + 1][x];
            grid[y + 1][x] = grid[y][x];
            grid[y][x] = temp;
        }
        robotLocation = robotLocation.north();
    }

    private void moveEast() {
        getNextEastFreeLocationBeforeWall().ifPresent(this::moveEastUpTo);
    }

    private Optional<Point> getNextEastFreeLocationBeforeWall() {
        int y = robotLocation.y;
        for (int x = robotLocation.x + 1; x < grid[0].length && notWall(y, x); x++) {
            if (isFree(y, x)) {
                return Optional.of(new Point(y, x));
            }
        }
        return Optional.empty();
    }

    private void moveEastUpTo(Point next) {
        int y = robotLocation.y;
        for (int x = next.x; x > robotLocation.x; x--) {
            char temp = grid[y][x - 1];
            grid[y][x - 1] = grid[y][x];
            grid[y][x] = temp;
        }
        robotLocation = robotLocation.east();
    }

    private void moveSouth() {
        getNextSouthFreeLocationBeforeWall().ifPresent(this::moveSouthUpTo);
    }

    private Optional<Point> getNextSouthFreeLocationBeforeWall() {
        int x = robotLocation.x;
        for (int y = robotLocation.y; y < grid.length && notWall(y, x); y++) {
            if (isFree(y, x)) {
                return Optional.of(new Point(y, x));
            }
        }
        return Optional.empty();
    }

    private void moveSouthUpTo(Point next) {
        int x = robotLocation.x;
        for (int y = next.y; y > robotLocation.y; y--) {
            char temp = grid[y - 1][x];
            grid[y - 1][x] = grid[y][x];
            grid[y][x] = temp;
        }
        robotLocation = robotLocation.south();
    }

    private void moveWest() {
        getNextWestFreeLocationBeforeWall().ifPresent(this::moveWestUpTo);
    }

    private Optional<Point> getNextWestFreeLocationBeforeWall() {
        int y = robotLocation.y;
        for (int x = robotLocation.x - 1; x > 0 && notWall(y, x); x--) {
            if (isFree(y, x)) {
                return Optional.of(new Point(y, x));
            }
        }
        return Optional.empty();
    }

    private void moveWestUpTo(Point next) {
        int y = robotLocation.y;
        for (int x = next.x; x < robotLocation.x; x++) {
            char temp = grid[y][x + 1];
            grid[y][x + 1] = grid[y][x];
            grid[y][x] = temp;
        }
        robotLocation = robotLocation.west();
    }

    private boolean notWall(int y, int x) {
        return grid[y][x] != WALL;
    }

    private boolean isFree(int y, int x) {
        return grid[y][x] == FREE;
    }

    private static Point findRobotLocation(char[][] grid) {
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[0].length; x++) {
                if (grid[y][x] == ROBOT) {
                    return new Point(y, x);
                }
            }
        }
        throw new IllegalArgumentException("no robot found in map");
    }

    private static int toGPS(int y, int x) {
        return (y * 100) + x;
    }

    private static char[] expand(char token) {
        if (token == '#') {
            return new char[] {'#', '#'};
        } else if (token == 'O') {
            return new char[] {'[', ']'};
        } else if (token == '.') {
            return new char[] {'.', '.'};
        } else {
            return new char[] {'@', '.'};
        }
    }
}
