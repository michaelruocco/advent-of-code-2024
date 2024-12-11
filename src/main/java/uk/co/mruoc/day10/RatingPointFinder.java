package uk.co.mruoc.day10;

import java.util.ArrayList;
import java.util.Collection;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RatingPointFinder implements PointFinder {

    private final CandidatePointFinder candidatePointFinder;

    public RatingPointFinder() {
        this(new CandidatePointFinder());
    }

    @Override
    public Collection<Point> find(TrailMap map, Collection<Point> points, Collection<Point> accumulator, int height) {
        return new ArrayList<>();
    }
}
