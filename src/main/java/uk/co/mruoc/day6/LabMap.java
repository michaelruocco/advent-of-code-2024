package uk.co.mruoc.day6;

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

    public LabMap performPatrol() {
        LabMap map = this;
        while (!map.isComplete()) {
            map = map.performNextMove();
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
                return Optional.of(Move.builder()
                        .previous(guard.getLocation())
                        .next(candidate)
                        .direction(nextGuard.getDirection())
                        .build());
            }
            nextGuard = nextGuard.rotate();
            candidate = locations.get(nextGuard.getNextLocationKey());
        }
        return Optional.empty();
    }

    private LabMap perform(Move move) {
        Map<String, Location> updatedLocations = new HashMap<>(locations);
        Location previous = move.getPrevious();
        updatedLocations.put(previous.getKey(), previous.toVisited());
        Location next = move.getNext();
        updatedLocations.put(next.getKey(), next.withToken(move.getDirection()));
        return toBuilder()
                .locations(updatedLocations)
                .guard(new Guard(next, move.getDirection()))
                .build();
    }

    private LabMap performLastMove() {
        Map<String, Location> updatedLocations = new HashMap<>(locations);
        Location last = guard.getLocation();
        updatedLocations.put(last.getKey(), last.toVisited());
        return toBuilder().locations(updatedLocations).build();
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
