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
import uk.co.mruoc.Point;

@RequiredArgsConstructor
public class Reindeer {

    private final Maze maze;

    @Getter
    private final Move initialMove;

    public Reindeer(Maze maze) {
        this(maze, new Move(maze.getStart(), Direction.EAST));
    }

    public long findLowestScore() {
        Queue<ScoredMove> moves = new PriorityQueue<>(Comparator.comparing(ScoredMove::getScore));
        moves.add(new DefaultScoredMove(initialMove, 0));
        Set<Move> seen = new HashSet<>();

        while (!moves.isEmpty()) {
            ScoredMove currentMove = moves.poll();
            seen.add(currentMove.getMove());
            if (maze.endsAt(currentMove.getLocation())) {
                return currentMove.getScore();
            }

            ScoredMove nextMove = currentMove.continueAhead();
            if (!seen.contains(nextMove.getMove()) && maze.pathAt(nextMove.getLocation())) {
                moves.add(nextMove);
            }

            nextMove = currentMove.rotate(Direction::rotateClockwise);
            if (!seen.contains(nextMove.getMove()) && maze.pathAt(nextMove.getLocation())) {
                moves.add(nextMove);
            }

            nextMove = currentMove.rotate(Direction::rotateAntiClockwise);
            if (!seen.contains(nextMove.getMove()) && maze.pathAt(nextMove.getLocation())) {
                moves.add(nextMove);
            }
        }
        return -1;
    }

    public long findNumberOfTilesOnAnyBestPath() {
        Queue<ScoredMoveWithPath> moves = new PriorityQueue<>(Comparator.comparing(ScoredMove::getScore));
        moves.add(new ScoredMoveWithPath(initialMove, 0));
        Set<Move> seen = new HashSet<>();
        Set<ScoredMoveWithPath> endingMoves = new HashSet<>();

        while (!moves.isEmpty()) {
            ScoredMoveWithPath currentMove = moves.poll();
            seen.add(currentMove.getMove());

            if (maze.endsAt(currentMove.getLocation())) {
                endingMoves.add(currentMove);
            }

            ScoredMoveWithPath nextMove = currentMove.continueAhead();
            if (!seen.contains(nextMove.getMove()) && maze.pathAt(nextMove.getLocation())) {
                moves.add(nextMove);
            }

            nextMove = currentMove.rotate(Direction::rotateClockwise);
            if (!seen.contains(nextMove.getMove()) && maze.pathAt(nextMove.getLocation())) {
                moves.add(nextMove);
            }

            nextMove = currentMove.rotate(Direction::rotateAntiClockwise);
            if (!seen.contains(nextMove.getMove()) && maze.pathAt(nextMove.getLocation())) {
                moves.add(nextMove);
            }
        }
        return endingMoves.stream()
                .collect(Collectors.groupingBy(ScoredMove::getScore, TreeMap::new, Collectors.toList()))
                .firstEntry()
                .getValue()
                .stream()
                .map(ScoredMoveWithPath::getPath)
                .flatMap(Collection::stream)
                .collect(Collectors.toSet())
                .size();
    }
}
