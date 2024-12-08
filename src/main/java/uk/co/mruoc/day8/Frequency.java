package uk.co.mruoc.day8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Frequency {

    private final int gridSize;

    @Getter
    private final Collection<Location> locations;

    public Frequency(int gridSize) {
        this(gridSize, new ArrayList<>());
    }

    public void add(Location location) {
        locations.add(location);
    }

    public Collection<Location> toAntiNodes() {
        return getUniquePairs().stream()
                .map(this::toAntiNodes)
                .flatMap(Collection::stream)
                .toList();
    }

    private Set<Pair> getUniquePairs() {
        Set<Pair> uniquePairs = new HashSet<>();
        List<Location> list = new ArrayList<>(locations);
        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) {
                uniquePairs.add(new Pair(list.get(i), list.get(j)));
            }
        }
        return uniquePairs;
    }

    private Collection<Location> toAntiNodes(Pair pair) {
        Location l1 = pair.l1;
        Location l2 = pair.l2;

        int dy = l2.y - l1.y;
        int dx = l2.x - l1.x;

        Collection<Location> antiNodes = new ArrayList<>();
        int yP1 = l2.y - 2 * dy;
        int xP1 = l2.x - 2 * dx;
        if (isInGridBounds(yP1, xP1)) {
            antiNodes.add(new Location(yP1, xP1));
        }

        int yP2 = l1.y + 2 * dy;
        int xP2 = l1.x + 2 * dx;
        if (isInGridBounds(yP2, xP2)) {
            antiNodes.add(new Location(yP2, xP2));
        }

        return antiNodes;
    }

    private boolean isInGridBounds(int... coordinates) {
        return Arrays.stream(coordinates).allMatch(i -> i > -1 && i < gridSize);
    }
}
