package uk.co.mruoc.day16;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Stream;
import uk.co.mruoc.Direction;
import uk.co.mruoc.Maze;
import uk.co.mruoc.Point;

public class Reindeer {

    private final Maze maze;

    public Reindeer(Maze maze) {
        this.maze = maze;
    }

    public Optional<Integer> findLowestScore() {
        return traverse().map(Result::getLowestScore);
    }

    public Optional<Integer> findNumberOfLocationsOnAnyBestPath() {
        return traverse().map(Result::getBestPathLocations);
    }

    private Optional<Result> traverse() {
        Set<Point> paths = new HashSet<>();
        int lowest = Integer.MAX_VALUE;
        Map<Move, Integer> moves = new HashMap<>();
        Queue<State> toVisit = new PriorityQueue<>(Comparator.comparingInt(State::getScore));
        toVisit.add(new State(maze.getStart(), Direction.EAST, 0));

        while (!toVisit.isEmpty()) {
            State state = toVisit.poll();
            Move move = state.getMove();
            int score = state.getScore();
            Collection<Point> path = state.getPath();

            if (score <= moves.getOrDefault(move, Integer.MAX_VALUE)) {
                moves.put(move, score);

                Point location = move.getLocation();
                if (maze.endsAt(location)) {
                    if (score > lowest) {
                        return Optional.of(new Result(lowest, paths.size() + 1));
                    }
                    paths.addAll(path);
                    lowest = score;
                }

                Direction direction = move.getDirection();
                for (ScoredDirection scoredDirection : toScoredDirections(direction)) {
                    Direction newDirection = scoredDirection.getDirection();
                    Point newLocation = newDirection.move(location);
                    if (maze.pathAt(newLocation)) {
                        int newScore = score + scoredDirection.getScore();
                        Collection<Point> newPath = concat(path, location);
                        toVisit.add(new State(newLocation, newDirection, newScore, newPath));
                    }
                }
            }
        }
        return Optional.empty();
    }

    private static Collection<ScoredDirection> toScoredDirections(Direction direction) {
        int rotateScore = 1001;
        return List.of(
                new ScoredDirection(direction, 1),
                new ScoredDirection(direction.rotateClockwise(), rotateScore),
                new ScoredDirection(direction.rotateAntiClockwise(), rotateScore));
    }

    private static Collection<Point> concat(Collection<Point> path, Point point) {
        return Stream.concat(path.stream(), Stream.of(point)).toList();
    }
}
