package uk.co.mruoc.day14;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class RobotTest {

    @Test
    void robotShouldMoveAroundMap() {
        RestroomMap map = new ExampleRestroomMap();
        Robot robot = buildRobot(map);
        robot.setInitialLocation();

        robot.move(5);

        assertThat(map.getState())
                .isEqualTo(
                        """
                ...........
                ...........
                ...........
                .1.........
                ...........
                ...........
                ...........
                """);
    }

    private static Robot buildRobot(RestroomMap map) {
        return Robot.builder()
                .map(map)
                .velocity(new Point(2, -3))
                .location(new Point(2, 4))
                .build();
    }
}
