package uk.co.mruoc.day16;

import static uk.co.mruoc.day16.MoveScoreCalculator.toAheadScore;
import static uk.co.mruoc.day16.MoveScoreCalculator.toRotateScore;

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
@Getter
@ToString
public class PathRetainingScoredMove implements ScoredMove<PathRetainingScoredMove> {

    private final Move move;
    private final long score;
    private final Collection<Point> path;

    public PathRetainingScoredMove(Move move, long score) {
        this(move, score, Set.of(move.location));
    }

    @Override
    public PathRetainingScoredMove continueAhead() {
        Set<Point> updatedPath = new HashSet<>(path);
        Move updatedMove = move.perform();
        updatedPath.add(updatedMove.location);
        return new PathRetainingScoredMove(updatedMove, toAheadScore(score), updatedPath);
    }

    @Override
    public PathRetainingScoredMove rotate(UnaryOperator<Direction> rotation) {
        return new PathRetainingScoredMove(move.perform(rotation), toRotateScore(score), path);
    }
}
