package uk.co.mruoc.day6;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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

    public int countSingleObstructionsCausingLoop() {
        Guard guard = new Guard(this);
        guard.patrol();
        int count = 0;
        List<LabMap.Point> locations = new ArrayList<>(this.getVisitedLocations());
        for (Point location : locations) {
            this.reset();
            if (this.isAvailable(location)) {
                this.addObstruction(location);
                guard = new Guard(this);
                guard.patrol();
                if (guard.isStuck()) {
                    count++;
                }
            }
        }
        return count;
    }

    public void visited(Point point) {
        setToken(point.y, point.x, VISITED);
    }

    public void update(Guard guard) {
        Point point = guard.getLocation();
        setToken(point.y, point.x, guard.getDirection());
    }

    public void addObstruction(Point point) {
        if (isAvailable(point)) {
            setToken(point.y, point.x, ADDED_OBSTRUCTION);
        }
    }

    public boolean exists(Point point) {
        return point.y > -1 &&
                point.y < tokens.length &&
                point.x > -1 &&
                point.x < tokens[point.y].length;
    }

    public boolean isAvailable(Point point) {
        return List.of(AVAILABLE, VISITED).contains(getToken(point.y, point.x));
    }

    public long countVisitedLocations() {
        return getVisitedLocations().size();
    }

    public Collection<Point> getVisitedLocations() {
        return findAll(this::isVisited).toList();
    }

    public Optional<Point> find(Predicate<Character> predicate) {
        return findAll(predicate).findFirst();
    }

    public Stream<Point> findAll(Predicate<Character> predicate) {
        Collection<Point> points = new ArrayList<>();
        for (int y = 0; y < tokens.length; y++) {
            for (int x = 0; x < tokens[y].length; x++) {
                if (predicate.test(getToken(y, x))) {
                    points.add(new Point(y, x));
                }
            }
        }
        return points.stream();
    }

    public char getToken(Point point) {
        return getToken(point.y, point.x);
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
        IntStream.range(0, tokens[y].length)
                .forEach(x -> tokens[y][x] = originalTokens[y][x]);
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

    @RequiredArgsConstructor
    @EqualsAndHashCode
    @ToString
    public static class Point {
        final int y;
        final int x;

        public Point north() {
            return new Point(y - 1, x);
        }

        public Point east() {
            return new Point(y, x + 1);
        }

        public Point south() {
            return new Point(y + 1, x);
        }

        public Point west() {
            return new Point(y, x - 1);
        }
    }
}
