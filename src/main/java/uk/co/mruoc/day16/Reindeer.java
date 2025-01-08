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

    public Optional<Result> search(Maze maze) {
        Set<Point> paths = new HashSet<>();
        int lowest = Integer.MAX_VALUE;
        Map<Move, Integer> moves = new HashMap<>();
        Queue<State> toVisit = new PriorityQueue<>(Comparator.comparingInt(State::getScore));
        toVisit.add(new State(maze.getStart(), Direction.EAST, 0));

        while (!toVisit.isEmpty()) {
            State state = toVisit.poll();
            Move move = state.getMove();
            int score = state.getScore();
            if (score <= moves.getOrDefault(move, Integer.MAX_VALUE)) {
                moves.put(move, score);
                if (maze.endsAt(move.getLocation())) {
                    if (score > lowest) {
                        return Optional.of(new Result(lowest, paths.size() + 1));
                    }
                    paths.addAll(state.getPath());
                    lowest = score;
                }
                handleNextMove(maze, toVisit, state);
            }
        }
        return Optional.empty();
    }

    private void handleNextMove(Maze maze, Collection<State> toVisit, State state) {
        Direction direction = state.getDirection();
        for (ScoredDirection scoredDirection : toScoredDirections(direction)) {
            Direction newDirection = scoredDirection.getDirection();
            Point location = state.getLocation();
            Point newLocation = newDirection.move(location);
            if (maze.pathAt(newLocation)) {
                int newScore = state.getScore() + scoredDirection.getScore();
                Collection<Point> newPath = concat(state.getPath(), location);
                toVisit.add(new State(newLocation, newDirection, newScore, newPath));
            }
        }
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
