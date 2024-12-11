package uk.co.mruoc.day10;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TrailFinder {

    private final PointFinder pointFinder;

    public long findResult(TrailMap map) {
        long sum = 0;
        for (Point trailhead : map.getTrailheads()) {
            Collection<Point> allSummits = new ArrayList<>();
            pointFinder.find(map, new ArrayList<>(List.of(trailhead)), allSummits, 1);
            sum += allSummits.size();
        }
        return sum;
    }
}
