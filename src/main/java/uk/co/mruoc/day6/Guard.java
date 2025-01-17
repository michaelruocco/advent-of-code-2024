package uk.co.mruoc.day6;

import java.util.HashSet;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import uk.co.mruoc.Direction;
import uk.co.mruoc.Point;

@RequiredArgsConstructor
public class Guard {

    private final Point initialLocation;
    private final Direction initialDirection;

    public long countSingleObstructionsCausingLoop(LabMap map) {
        return patrol(map).getVisitedLocations().stream()
                .map(map::addWallAt)
                .map(this::patrol)
                .filter(Result::isStuck)
                .count();
    }

    public Result patrol(LabMap map) {
        Point location = initialLocation;
        Direction direction = initialDirection;
        Set<Step> steps = new HashSet<>();
        while (map.exists(location)) {
            Point nextLocation = direction.move(location);
            if (map.isWallAt(nextLocation)) {
                direction = direction.rotateClockwise();
            } else {
                Step step = new Step(location, direction);
                if (steps.contains(step)) {
                    return new Result(true, steps);
                }
                steps.add(step);
                location = nextLocation;
            }
        }
        return new Result(false, steps);
    }
}
