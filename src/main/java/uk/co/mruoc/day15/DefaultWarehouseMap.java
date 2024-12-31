package uk.co.mruoc.day15;

import static uk.co.mruoc.day15.Tokens.BOX;
import static uk.co.mruoc.day15.Tokens.EMPTY;
import static uk.co.mruoc.day15.Tokens.ROBOT;
import static uk.co.mruoc.day15.Tokens.WALL;

import java.util.List;
import uk.co.mruoc.Direction;
import uk.co.mruoc.Point;

public class DefaultWarehouseMap extends AbstractWarehouseMap {

    public DefaultWarehouseMap(List<String> lines) {
        super(lines);
    }

    @Override
    public int getXScale() {
        return 1;
    }

    @Override
    public void move(Direction direction) {
        Point nextRobotLocation = direction.move(getRobotLocation());
        if (wallAt(nextRobotLocation)) {
            return;
        }
        if (boxAt(nextRobotLocation)) {
            Point pointToMoveBoxTo = direction.move(nextRobotLocation);
            while (boxAt(pointToMoveBoxTo)) {
                pointToMoveBoxTo = direction.move(pointToMoveBoxTo);
            }
            if (wallAt(pointToMoveBoxTo)) {
                return;
            }
            moveBox(nextRobotLocation, pointToMoveBoxTo);
        }
        setRobotLocation(nextRobotLocation);
    }

    @Override
    public char getToken(Point point) {
        if (wallAt(point)) {
            return WALL;
        }
        if (boxAt(point)) {
            return BOX;
        }
        if (robotAt(point)) {
            return ROBOT;
        }
        return EMPTY;
    }
}
