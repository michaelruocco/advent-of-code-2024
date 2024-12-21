package uk.co.mruoc.day6;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import uk.co.mruoc.Point;

@RequiredArgsConstructor
@Getter
public class Result {

    private final boolean stuck;
    private final Collection<Step> steps;
    private final Set<Point> visitedLocations;

    public Result(boolean stuck, Collection<Step> steps) {
        this.stuck = stuck;
        this.steps = steps;
        this.visitedLocations = steps.stream().map(step -> step.location).collect(Collectors.toSet());
    }

    public int getVisitedLocationCount() {
        return visitedLocations.size();
    }
}
