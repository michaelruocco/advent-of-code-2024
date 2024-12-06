package uk.co.mruoc.day6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lombok.Builder;

@Builder(toBuilder = true)
public class LabMap {

    private final int size;
    private final Map<String, Location> locations;
    private final Guard guard;
    private final Collection<Location> previousLocations;

    public LabMap performPatrol() {
        LabMap map = this;
        int i = 0;
        while (!map.isComplete()) {
            map = map.performNextMove();
            i++;
        }
        return map.performLastMove();
    }

    public long countVisitedPositions() {
        return locations.values().stream().filter(Location::isVisited).count();
    }

    public String getState() {
        return IntStream.range(0, size).mapToObj(this::toRow).collect(Collectors.joining(System.lineSeparator()));
    }

    private boolean isComplete() {
        return calculateNextMove().isEmpty();
    }

    private LabMap performNextMove() {
        return calculateNextMove().map(this::perform).orElse(this);
    }

    private Optional<Move> calculateNextMove() {
        Guard nextGuard = guard;
        Location candidate = locations.get(nextGuard.getNextLocationKey());
        while (Objects.nonNull(candidate)) {
            if (candidate.isAvailable()) {
                Location nextLocation = candidate.withToken(nextGuard.getDirection());
                if (isStuckInLoop(nextLocation)) {
                    return Optional.empty();
                }
                return Optional.of(Move.builder()
                        .previous(guard.getLocation())
                        .next(nextLocation)
                        .direction(nextGuard.getDirection())
                        .build());
            }
            nextGuard = nextGuard.rotate();
            candidate = locations.get(nextGuard.getNextLocationKey());
        }
        return Optional.empty();
    }

    private boolean isStuckInLoop(Location nextLocation) {
        return previousLocations.contains(nextLocation);
    }

    private LabMap perform(Move move) {
        return LabMap.builder()
                .size(size)
                .guard(updateGuard(move))
                .locations(updateLocations(move))
                .previousLocations(updatePreviousLocations(move.getPrevious()))
                .build();
    }

    private Map<String, Location> updateLocations(Move move) {
        Location previous = move.getPrevious();
        Location next = move.getNext();
        return updateLocations(previous.toVisited(), next.withToken(move.getDirection()));
    }

    private Collection<Location> updatePreviousLocations(Location previous) {
        Collection<Location> updatedPreviousLocations = new ArrayList<>(previousLocations);
        updatedPreviousLocations.add(previous);
        return updatedPreviousLocations;
    }

    private Guard updateGuard(Move move) {
        return new Guard(move.getNext(), move.getDirection());
    }

    private LabMap performLastMove() {
        return toBuilder()
                .locations(updateLocations(guard.getLocation().toVisited()))
                .previousLocations(updatePreviousLocations(guard.getLocation()))
                .build();
    }

    private Map<String, Location> updateLocations(Location... locationsToUpdate) {
        Map<String, Location> updatedLocations = new HashMap<>(locations);
        Arrays.stream(locationsToUpdate).forEach(location -> updatedLocations.put(location.getKey(), location));
        return updatedLocations;
    }

    private String toRow(int y) {
        return IntStream.range(0, size)
                .mapToObj(x -> getToken(x, y))
                .map(c -> Character.toString(c))
                .collect(Collectors.joining());
    }

    private char getToken(int x, int y) {
        return locations.get(new Point(x, y).getKey()).getToken();
    }
}
