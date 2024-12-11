package uk.co.mruoc.day10;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TrailFinder {

    private static final int SUMMIT = 9;

    private final PointAccumulator accumulator;
    private final CandidatePointFinder candidatePointFinder;

    public TrailFinder(PointAccumulator accumulator) {
        this(accumulator, new CandidatePointFinder());
    }

    public long findResult(TrailMap map) {
        long sum = 0;
        for (Point trailhead : map.getTrailheads()) {
            Collection<Point> allSummits = new ArrayList<>();
            find(map, new ArrayList<>(List.of(trailhead)), allSummits, 1);
            sum += allSummits.size();
        }
        return sum;
    }

    private Collection<Point> find(TrailMap map, Collection<Point> points, Collection<Point> allSummits, int height) {
        Collection<Point> summits = new ArrayList<>();
        for (Point point : points) {
            Collection<Point> candidates = candidatePointFinder.find(map, point, height);
            if (height == SUMMIT && !candidates.isEmpty()) {
                summits.addAll(candidates);
            } else {
                Collection<Point> nextPoints = find(map, candidates, allSummits, height + 1);
                accumulator.accumulate(nextPoints, allSummits);
            }
        }
        return summits;
    }
}
