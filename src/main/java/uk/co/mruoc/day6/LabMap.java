package uk.co.mruoc.day6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class LabMap {

    private static final char AVAILABLE = '.';
    private static final char VISITED = 'X';
    private static final char ADDED_OBSTRUCTION = 'O';

    private final char[][] tokens;
    private final char[][] originalTokens;

    public LabMap(char[][] tokens) {
        this(tokens, deepCopy(tokens));
    }

    public long countSingleObstructionsCausingLoop() {
        performPatrol();
        return getVisitedLocations().filter(this::isStuckWhenObstructionAdded).count();
    }

    private boolean isStuckWhenObstructionAdded(Location location) {
        reset();
        if (!isAvailable(location)) {
            return false;
        }
        addObstruction(location);
        Guard guard = performPatrol();
        return guard.isStuck();
    }

    private Guard performPatrol() {
        Guard guard = new Guard(this);
        guard.patrol();
        return guard;
    }

    public void visited(Location location) {
        setToken(location.y, location.x, VISITED);
    }

    public void update(Guard guard) {
        Location location = guard.getLocation();
        setToken(location.y, location.x, guard.getDirection());
    }

    public void addObstruction(Location location) {
        if (isAvailable(location)) {
            setToken(location.y, location.x, ADDED_OBSTRUCTION);
        }
    }

    public boolean exists(Location location) {
        return location.y > -1
                && location.y < tokens.length
                && location.x > -1
                && location.x < tokens[location.y].length;
    }

    public boolean isAvailable(Location location) {
        return List.of(AVAILABLE, VISITED).contains(getToken(location.y, location.x));
    }

    public long countVisitedLocations() {
        return getVisitedLocations().count();
    }

    public Stream<Location> getVisitedLocations() {
        return findAll(this::isVisited);
    }

    public Optional<Location> find(Predicate<Character> predicate) {
        return findAll(predicate).findFirst();
    }

    public Stream<Location> findAll(Predicate<Character> predicate) {
        Collection<Location> locations = new ArrayList<>();
        for (int y = 0; y < tokens.length; y++) {
            for (int x = 0; x < tokens[y].length; x++) {
                if (predicate.test(getToken(y, x))) {
                    locations.add(new Location(y, x));
                }
            }
        }
        return locations.stream();
    }

    public char getToken(Location location) {
        return getToken(location.y, location.x);
    }

    public String getState() {
        return IntStream.range(0, tokens.length)
                .mapToObj(this::formatRow)
                .collect(Collectors.joining(System.lineSeparator()));
    }

    public void reset() {
        IntStream.range(0, tokens.length).forEach(this::resetRow);
    }

    private char getToken(int y, int x) {
        return tokens[y][x];
    }

    private void resetRow(int y) {
        IntStream.range(0, tokens[y].length).forEach(x -> tokens[y][x] = originalTokens[y][x]);
    }

    private String formatRow(int y) {
        return IntStream.range(0, tokens[y].length)
                .mapToObj(x -> getToken(y, x))
                .map(c -> Character.toString(c))
                .collect(Collectors.joining());
    }

    private boolean isVisited(char token) {
        return token == VISITED;
    }

    private void setToken(int y, int x, char token) {
        tokens[y][x] = token;
    }

    private static char[][] deepCopy(char[][] matrix) {
        return Arrays.stream(matrix).map(char[]::clone).toArray(a -> matrix.clone());
    }
}
