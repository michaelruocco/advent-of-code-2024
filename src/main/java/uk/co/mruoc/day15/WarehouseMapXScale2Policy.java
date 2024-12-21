package uk.co.mruoc.day15;

import static uk.co.mruoc.day15.Tokens.EAST_BOX;
import static uk.co.mruoc.day15.Tokens.EMPTY;
import static uk.co.mruoc.day15.Tokens.ROBOT;
import static uk.co.mruoc.day15.Tokens.WALL;
import static uk.co.mruoc.day15.Tokens.WEST_BOX;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;
import uk.co.mruoc.Point;

public class WarehouseMapXScale2Policy implements WarehouseMapScalePolicy {

    @Override
    public int getXScale() {
        return 2;
    }

    @Override
    public void move(WarehouseMap map, Direction direction) {
        Point nextRobotLocation = direction.move(map.getRobotLocation());
        if (tryFind(map::wallAt, nextRobotLocation).isPresent()) {
            return;
        }
        Optional<Point> nextBoxLocation = tryFind(map::boxAt, nextRobotLocation);
        if (nextBoxLocation.isPresent()) {
            Collection<Point> boxesToMove = new ArrayList<>();
            List<Point> nextBoxes = new ArrayList<>(List.of(nextBoxLocation.get()));
            while (!nextBoxes.isEmpty()) {
                Point nextBox = nextBoxes.remove(0);
                boxesToMove.add(nextBox);
                if (!findInBoxPath(map::wallAt, nextBox, direction).isEmpty()) {
                    return;
                }
                nextBoxes.addAll(findInBoxPath(map::boxAt, nextBox, direction));
            }
            map.moveBoxes(boxesToMove, direction);
        }
        map.setRobotLocation(nextRobotLocation);
    }

    @Override
    public char getToken(WarehouseMap map, Point point) {
        Point westPoint = point.west();
        if (map.wallAt(point) || map.wallAt(westPoint)) {
            return WALL;
        }
        if (map.boxAt(point)) {
            return WEST_BOX;
        }
        if (map.boxAt(westPoint)) {
            return EAST_BOX;
        }
        if (map.getRobotLocation().equals(point)) {
            return ROBOT;
        }
        return EMPTY;
    }

    private static Collection<Point> findInBoxPath(Predicate<Point> found, Point boxLocation, Direction direction) {
        return Stream.of(
                        tryFind(found, direction.move(boxLocation)), tryFind(found, direction.move(boxLocation.east())))
                .flatMap(Optional::stream)
                .filter(p -> !p.equals(boxLocation))
                .toList();
    }

    private static Optional<Point> tryFind(Predicate<Point> found, Point location) {
        if (found.test(location)) {
            return Optional.of(location);
        }
        Point westLocation = location.west();
        if (found.test(westLocation)) {
            return Optional.of(westLocation);
        }
        return Optional.empty();
    }
}
