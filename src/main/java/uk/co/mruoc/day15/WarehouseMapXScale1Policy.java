package uk.co.mruoc.day15;

import static uk.co.mruoc.day15.Tokens.BOX;
import static uk.co.mruoc.day15.Tokens.EMPTY;
import static uk.co.mruoc.day15.Tokens.ROBOT;
import static uk.co.mruoc.day15.Tokens.WALL;

import uk.co.mruoc.Point;

public class WarehouseMapXScale1Policy implements WarehouseMapScalePolicy {

    public int getXScale() {
        return 1;
    }

    public void move(WarehouseMap map, Direction direction) {
        Point nextRobotLocation = direction.move(map.getRobotLocation());
        if (map.wallAt(nextRobotLocation)) {
            return;
        }
        if (map.boxAt(nextRobotLocation)) {
            Point pointToMoveBoxTo = direction.move(nextRobotLocation);
            while (map.boxAt(pointToMoveBoxTo)) {
                pointToMoveBoxTo = direction.move(pointToMoveBoxTo);
            }
            if (map.wallAt(pointToMoveBoxTo)) {
                return;
            }
            map.moveBox(nextRobotLocation, pointToMoveBoxTo);
        }
        map.setRobotLocation(nextRobotLocation);
    }

    public char getToken(WarehouseMap map, Point point) {
        if (map.wallAt(point)) {
            return WALL;
        }
        if (map.boxAt(point)) {
            return BOX;
        }
        if (map.robotAt(point)) {
            return ROBOT;
        }
        return EMPTY;
    }
}
