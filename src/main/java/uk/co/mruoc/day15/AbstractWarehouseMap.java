package uk.co.mruoc.day15;

import static uk.co.mruoc.day15.Tokens.BOX;
import static uk.co.mruoc.day15.Tokens.ROBOT;
import static uk.co.mruoc.day15.Tokens.WALL;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import uk.co.mruoc.Direction;
import uk.co.mruoc.Point;

public abstract class AbstractWarehouseMap implements WarehouseMap {

    private final Set<Point> walls;
    private final Set<Point> boxes;

    private final int height;

    @Setter(AccessLevel.PROTECTED)
    @Getter(AccessLevel.PROTECTED)
    private Point robotLocation;

    protected AbstractWarehouseMap(List<String> lines) {
        this.walls = new HashSet<>();
        this.boxes = new HashSet<>();
        this.height = lines.size();
        for (int y = 0; y < height; y++) {
            String line = lines.get(y);
            for (int x = 0; x < line.length(); x++) {
                char token = line.charAt(x);
                Point point = new Point(y, x * getXScale());
                handleToken(token, point);
            }
        }
    }

    @Override
    public void move(Directions directions) {
        while (!directions.isEmpty()) {
            Direction direction = directions.next();
            move(direction);
        }
    }

    @Override
    public int sumOfAllBoxesGPS() {
        return boxes.stream().mapToInt(this::toGPS).sum();
    }

    @Override
    public String getState() {
        int maxX = getMaxX();
        StringBuilder state = new StringBuilder();
        for (int y = 0; y < height; y++) {
            StringBuilder row = new StringBuilder();
            for (int x = 0; x < maxX + getXScale(); x++) {
                char token = getToken(new Point(y, x));
                row.append(token);
            }
            row.append(System.lineSeparator());
            state.append(row);
        }
        return state.toString();
    }

    protected boolean wallAt(Point point) {
        return walls.contains(point);
    }

    protected boolean boxAt(Point point) {
        return boxes.contains(point);
    }

    protected boolean robotAt(Point point) {
        return robotLocation.equals(point);
    }

    protected void moveBoxes(Collection<Point> boxesToMove, Direction direction) {
        boxesToMove.forEach(boxes::remove);
        boxesToMove.stream().map(direction::move).forEach(boxes::add);
    }

    protected void moveBox(Point from, Point to) {
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

    protected abstract int getXScale();

    protected abstract char getToken(Point point);

    private int toGPS(Point point) {
        return (point.y * 100) + point.x;
    }

    private int getMaxX() {
        return walls.stream().mapToInt(wall -> wall.x).max().orElseThrow();
    }
}
