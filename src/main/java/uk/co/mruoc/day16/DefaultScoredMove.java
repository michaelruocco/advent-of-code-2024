package uk.co.mruoc.day16;

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
public class DefaultScoredMove implements ScoredMove {
    private final Move move;

    @Getter
    private final long score;

    @Override
    public Move getMove() {
        return move;
    }

    @Override
    public DefaultScoredMove continueAhead() {
        return new DefaultScoredMove(move.perform(), score + 1);
    }

    @Override
    public DefaultScoredMove rotate(UnaryOperator<Direction> rotation) {
        return new DefaultScoredMove(move.perform(rotation), score + 1000);
    }

    @Override
    public Point getLocation() {
        return move.location;
    }
}
