package uk.co.mruoc.day6;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.With;

@Builder(toBuilder = true)
public class LabMap {

    private static int count;

    private final int size;

    @With(AccessLevel.PRIVATE)
    private final Map<String, Location> locations;

    private final Guard guard;
    private final Collection<Location> previousLocations;

    @With(AccessLevel.PRIVATE)
    @Getter
    private final boolean stuck;

    public long getLoopObstructionCount() {
        Collection<Location> visitedLocations = performPatrol().getVisitedLocations();
        return visitedLocations.stream()
                .map(Location::addObstruction)
                .map(obstructedLocation -> this.withLocations(updateLocations(obstructedLocation)))
                .map(LabMap::performPatrol)
                .filter(LabMap::isStuck)
                .count();
    }

    public LabMap performPatrol() {
        Instant start = Instant.now();
        LabMap map = this;
        Move nextMove = calculateNextMove();
        while (!nextMove.isComplete()) {
            map = map.perform(nextMove);
            nextMove = map.calculateNextMove();
        }
        LabMap completedMap = map.performLastMove(nextMove);
        System.out.println("patrol took " + Duration.between(start, Instant.now()) + " for patrol " + count);
        count++;
        return completedMap;
    }

    public long countVisitedPositions() {
        return locations.values().stream().filter(Location::isVisited).count();
    }

    public String getState() {
        return IntStream.range(0, size).mapToObj(this::toRow).collect(Collectors.joining(System.lineSeparator()));
    }

    private Move calculateNextMove() {
        Guard nextGuard = guard;
        Location candidate = locations.get(nextGuard.getNextLocationKey());
        while (Objects.nonNull(candidate)) {
            if (candidate.isAvailable()) {
                Location nextLocation = candidate.withToken(nextGuard.getDirection());
                if (isStuckInLoop(nextLocation)) {
                    return Move.builder().stuck(true).complete(true).build();
                }
                return Move.builder()
                        .previous(guard.getLocation())
                        .next(nextLocation)
                        .direction(nextGuard.getDirection())
                        .build();
            }
            nextGuard = nextGuard.rotate();
            candidate = locations.get(nextGuard.getNextLocationKey());
        }
        return Move.builder().complete(true).build();
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

    private LabMap performLastMove(Move move) {
        return toBuilder()
                .stuck(move.isStuck())
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

    private Collection<Location> getVisitedLocations() {
        return locations.values().stream().filter(Location::isVisited).toList();
    }
}
