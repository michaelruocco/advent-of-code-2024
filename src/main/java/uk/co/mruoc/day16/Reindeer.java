package uk.co.mruoc.day16;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import uk.co.mruoc.Direction;

@RequiredArgsConstructor
public class Reindeer {

    private final Maze maze;

    @Getter
    private final Move initialMove;

    public Reindeer(Maze maze) {
        this(maze, new Move(maze.getStart(), Direction.EAST));
    }

    public long findLowestScore() {
        return toMinScore(findEndingMoves(new DefaultScoredMove(initialMove, 0)));
    }

    public long findNumberOfLocationsOnAnyBestPath() {
        return toUniqueLocations(findEndingMoves(new PathRetainingScoredMove(initialMove, 0)));
    }

    private <T extends ScoredMove<T>> Collection<T> findEndingMoves(T initialMove) {
        Queue<T> moves = new PriorityQueue<>(Comparator.comparing(ScoredMove::getScore));
        moves.add(initialMove);
        Set<Move> seen = new HashSet<>();
        Set<T> endingMoves = new HashSet<>();

        while (!moves.isEmpty()) {
            T currentMove = moves.poll();
            seen.add(currentMove.getMove());
            if (maze.endsAt(currentMove.getLocation())) {
                endingMoves.add(currentMove);
            }
            long minScore = toMinScore(endingMoves);

            T nextMove = currentMove.continueAhead();
            if (nextMove.getScore() <= minScore
                    && !seen.contains(nextMove.getMove())
                    && maze.pathAt(nextMove.getLocation())) {
                moves.add(nextMove);
            }

            nextMove = currentMove.rotate(Direction::rotateClockwise);
            if (nextMove.getScore() <= minScore
                    && !seen.contains(nextMove.getMove())
                    && maze.pathAt(nextMove.getLocation())) {
                moves.add(nextMove);
            }

            nextMove = currentMove.rotate(Direction::rotateAntiClockwise);
            if (nextMove.getScore() <= minScore
                    && !seen.contains(nextMove.getMove())
                    && maze.pathAt(nextMove.getLocation())) {
                moves.add(nextMove);
            }
        }
        return endingMoves;
    }

    private static <T extends ScoredMove<T>> long toMinScore(Collection<T> moves) {
        return moves.stream()
                .sorted(Comparator.comparing(ScoredMove::getScore))
                .map(ScoredMove::getScore)
                .findFirst()
                .orElse(Long.MAX_VALUE);
    }

    private static int toUniqueLocations(Collection<PathRetainingScoredMove> moves) {
        return moves.stream()
                .collect(Collectors.groupingBy(ScoredMove::getScore, TreeMap::new, Collectors.toList()))
                .firstEntry()
                .getValue()
                .stream()
                .map(PathRetainingScoredMove::getPath)
                .flatMap(Collection::stream)
                .collect(Collectors.toSet())
                .size();
    }
}
