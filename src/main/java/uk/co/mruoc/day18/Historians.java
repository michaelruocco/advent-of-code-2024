package uk.co.mruoc.day18;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import uk.co.mruoc.Direction;
import uk.co.mruoc.Point;

@RequiredArgsConstructor
public class Historians {

    public int getMinimumNumberOfStepsToEnd(MemorySpace memorySpace) {
        Point location = memorySpace.getStart();
        LinkedList<Step> steps = new LinkedList<>(List.of(new Step(location, 0)));
        Set<Point> seen = new HashSet<>(Set.of(location));
        while (!steps.isEmpty()) {
            Step previousStep = steps.pollFirst();
            int stepCount = previousStep.getCount() + 1;
            for (Direction direction : directions()) {
                Point newLocation = direction.move(previousStep.getLocation());
                if (memorySpace.isInBoundsAndFree(newLocation) && !seen.contains(newLocation)) {
                    if (memorySpace.endsAt(newLocation)) {
                        return stepCount;
                    }
                    seen.add(newLocation);
                    steps.add(new Step(newLocation, stepCount));
                }
            }
        }
        return -1;
    }

    public Optional<Point> findFirstBlocker(MemorySpace memorySpace) {
        List<Point> bytes = new ArrayList<>(memorySpace.getBytes());
        int low = 0;
        int high = bytes.size() - 1;

        while (low < high) {
            int mid = ((low + high) / 2);
            MemorySpace segment = memorySpace.withMaximumNumberOfBytes(mid + 1);
            if (getMinimumNumberOfStepsToEnd(segment) == -1) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        System.out.println("low " + low);
        Point point = bytes.get(low);
        System.out.println(point);
        return Optional.of(point);
    }

    private static Collection<Direction> directions() {
        return Set.of(Direction.EAST, Direction.SOUTH, Direction.WEST, Direction.NORTH);
    }
}
