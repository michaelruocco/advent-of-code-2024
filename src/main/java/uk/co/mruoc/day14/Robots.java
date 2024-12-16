package uk.co.mruoc.day14;

import java.util.Collection;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Robots {

    private final Collection<Robot> values;

    public void setInitialLocations() {
        values.forEach(Robot::setInitialLocation);
    }

    public void move(int seconds) {
        values.forEach(robot -> robot.move(seconds));
    }
}
