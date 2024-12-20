package uk.co.mruoc.day15;

import static uk.co.mruoc.day15.Tokens.BOX;
import static uk.co.mruoc.day15.Tokens.ROBOT;
import static uk.co.mruoc.day15.Tokens.WALL;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

public class WarehouseMap {

    private final Set<Point> walls;
    private final Set<Point> boxes;

    private final int height;
    private final WarehouseMapScalePolicy scalePolicy;

    @Setter
    @Getter
    private Point robotLocation;

    public WarehouseMap(List<String> lines, WarehouseMapScalePolicy scalePolicy) {
        this.walls = new HashSet<>();
        this.boxes = new HashSet<>();
        this.height = lines.size();
        this.scalePolicy = scalePolicy;
        for (int y = 0; y < height; y++) {
            String line = lines.get(y);
            for (int x = 0; x < line.length(); x++) {
                char token = line.charAt(x);
                Point point = new Point(y, x * scalePolicy.getXScale());
                handleToken(token, point);
            }
        }
    }

    public void move(Directions directions) {
        while (!directions.isEmpty()) {
            Direction direction = directions.next();
            move(direction);
        }
    }

    public void move(Direction direction) {
        scalePolicy.move(this, direction);
    }

    public String getState() {
        int maxX = getMaxX();
        StringBuilder state = new StringBuilder();
        for (int y = 0; y < height; y++) {
            StringBuilder row = new StringBuilder();
            for (int x = 0; x < maxX + scalePolicy.getXScale(); x++) {
                char token = getToken(new Point(y, x));
                row.append(token);
            }
            row.append(System.lineSeparator());
            state.append(row);
        }
        return state.toString();
    }

    public boolean wallAt(Point point) {
        return walls.contains(point);
    }

    public boolean boxAt(Point point) {
        return boxes.contains(point);
    }

    public boolean robotAt(Point point) {
        return robotLocation.equals(point);
    }

    public void moveBoxes(Collection<Point> boxesToMove, Direction direction) {
        boxesToMove.forEach(boxes::remove);
        boxesToMove.stream().map(direction::move).forEach(boxes::add);
    }

    public void moveBox(Point from, Point to) {
        boxes.remove(from);
        boxes.add(to);
    }

    private void handleToken(char token, Point point) {
        if (token == WALL) {
            walls.add(point);
        } else if (token == BOX) {
            boxes.add(point);
        } else if (token == ROBOT) {
            robotLocation = point;
        }
    }

    private int getMaxX() {
        return walls.stream().mapToInt(wall -> wall.x).max().orElseThrow();
    }

    private char getToken(Point point) {
        return scalePolicy.getToken(this, point);
    }

    public int sumOfAllBoxesGPS() {
        return boxes.stream().mapToInt(this::toGPS).sum();
    }

    private int toGPS(Point point) {
        return (point.y * 100) + point.x;
    }
}
