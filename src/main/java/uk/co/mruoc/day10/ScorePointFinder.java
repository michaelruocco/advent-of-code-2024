package uk.co.mruoc.day10;

import static uk.co.mruoc.day10.Height.SUMMIT;

import java.util.ArrayList;
import java.util.Collection;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ScorePointFinder implements PointFinder {

    private final CandidatePointFinder candidatePointFinder;

    public ScorePointFinder() {
        this(new CandidatePointFinder());
    }

    @Override
    public Collection<Point> find(TrailMap map, Collection<Point> points, Collection<Point> accumulator, int height) {
        Collection<Point> summits = new ArrayList<>();
        for (Point point : points) {
            Collection<Point> candidates = candidatePointFinder.find(map, point, height);
            if (height == SUMMIT && !candidates.isEmpty()) {
                summits.addAll(candidates);
            } else {
                Collection<Point> nextPoints = find(map, candidates, accumulator, height + 1);
                for (Point nextPoint : nextPoints) {
                    if (accumulator.stream().noneMatch(p -> p.matches(nextPoint))) {
                        accumulator.add(nextPoint);
                    }
                }
            }
        }
        return summits;
    }
}
