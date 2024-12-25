package uk.co.mruoc.day16;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.function.UnaryOperator;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import uk.co.mruoc.Direction;
import uk.co.mruoc.Point;

@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class ScoredMoveWithPath implements ScoredMove {
    private final Move move;

    @Getter
    private final long score;

    @Getter
    private final Collection<Point> path;

    public ScoredMoveWithPath(Move move, long score) {
        this(move, score, Set.of(move.location));
    }

    @Override
    public Move getMove() {
        return move;
    }

    @Override
    public ScoredMoveWithPath continueAhead() {
        Set<Point> updatedPath = new HashSet<>(path);
        Move updatedMove = move.perform();
        updatedPath.add(updatedMove.location);
        return new ScoredMoveWithPath(updatedMove, score + 1, updatedPath);
    }

    @Override
    public ScoredMoveWithPath rotate(UnaryOperator<Direction> rotation) {
        return new ScoredMoveWithPath(move.perform(rotation), score + 1000, path);
    }

    @Override
    public Point getLocation() {
        return move.location;
    }
}
