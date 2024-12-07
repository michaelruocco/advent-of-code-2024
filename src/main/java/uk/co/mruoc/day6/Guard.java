package uk.co.mruoc.day6;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Guard {

    private static final char NORTH = '^';
    private static final char EAST = '>';
    private static final char SOUTH = 'V';
    private static final char WEST = '<';

    private static final Collection<Character> ALL = List.of(NORTH, EAST, SOUTH, WEST);

    private final LabMap map;
    private final Collection<Step> path;

    @Getter
    private char direction;

    @Getter
    private Location location;

    @Getter
    private boolean stuck;

    public Guard(LabMap map) {
        this.map = map;
        this.location = map.find(isGuardToken()).orElseThrow();
        this.direction = map.getToken(location);
        this.path = new ArrayList<>();
    }

    public void patrol() {
        try {
            performPatrol();
        } catch (IsStuckException e) {
            stuck = true;
        }
    }

    private void performPatrol() {
        while (!isComplete()) {
            move();
        }
        recordMove();
    }

    private void move() {
        recordMove();
        rotateUntilCanMove();
        location = nextLocation();
        map.update(this);
    }

    private void recordMove() {
        map.visited(location);
        Step step = new Step(location, direction);
        if (path.contains(step)) {
            throw new IsStuckException(step);
        }
        path.add(step);
    }

    private void rotateUntilCanMove() {
        while (!map.isAvailable(nextLocation())) {
            rotate();
        }
    }

    private boolean isComplete() {
        return !map.exists(nextLocation());
    }

    public long getPathSize() {
        return getPath().size();
    }

    public Collection<Location> getPath() {
        return path.stream().map(Step::getLocation).distinct().toList();
    }

    private Location nextLocation() {
        return switch (direction) {
            case NORTH -> location.north();
            case EAST -> location.east();
            case SOUTH -> location.south();
            default -> location.west();
        };
    }

    public void rotate() {
        switch (direction) {
            case NORTH -> direction = EAST;
            case EAST -> direction = SOUTH;
            case SOUTH -> direction = WEST;
            default -> direction = NORTH;
        }
    }

    private static Predicate<Character> isGuardToken() {
        return ALL::contains;
    }
}
