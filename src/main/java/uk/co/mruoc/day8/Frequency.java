package uk.co.mruoc.day8;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import uk.co.mruoc.Point;

@RequiredArgsConstructor
public class Frequency {

    private final AntiNodeFinder antiNodeFinder;
    private final int gridSize;

    @Getter
    private final Collection<Point> locations;

    public Frequency(AntiNodeFinder antiNodeFinder, int gridSize) {
        this(antiNodeFinder, gridSize, new ArrayList<>());
    }

    public void add(Point location) {
        locations.add(location);
    }

    public Collection<Point> toAntiNodes() {
        return getUniquePairs().stream()
                .map(pair -> antiNodeFinder.toAntiNodes(pair, gridSize))
                .flatMap(Collection::stream)
                .toList();
    }

    private Set<Pair> getUniquePairs() {
        Set<Pair> uniquePairs = new HashSet<>();
        List<Point> list = new ArrayList<>(locations);
        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) {
                uniquePairs.add(new Pair(list.get(i), list.get(j)));
            }
        }
        return uniquePairs;
    }
}
