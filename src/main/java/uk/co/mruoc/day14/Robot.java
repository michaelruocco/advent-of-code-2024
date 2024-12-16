package uk.co.mruoc.day14;

import java.util.stream.IntStream;
import lombok.Builder;

@Builder
public class Robot {

    private final RestroomMap map;
    private final Point velocity;
    private Point location;

    public void setInitialLocation() {
        map.move(location);
    }

    public void move(int seconds) {
        IntStream.range(0, seconds).forEach(i -> move());
    }

    public void move() {
        int newY = calculateNewY();
        int newX = calculateNewX();

        Point newLocation = new Point(newX, newY);
        map.move(location, newLocation);
        location = newLocation;
    }

    private int calculateNewY() {
        int newY = location.y + velocity.y;
        int height = map.getHeight();
        if (newY < 0) {
            return height + newY;
        }
        if (newY >= height) {
            return newY - height;
        }
        return newY;
    }

    private int calculateNewX() {
        int newX = location.x + velocity.x;
        int width = map.getWidth();
        if (newX < 0) {
            return width + newX;
        }
        if (newX >= width) {
            return newX - width;
        }
        return newX;
    }
}
