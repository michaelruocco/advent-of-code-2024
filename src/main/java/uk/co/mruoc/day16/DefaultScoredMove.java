package uk.co.mruoc.day16;

import static uk.co.mruoc.day16.MoveScoreCalculator.toAheadScore;
import static uk.co.mruoc.day16.MoveScoreCalculator.toRotateScore;

import java.util.function.UnaryOperator;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import uk.co.mruoc.Direction;

@RequiredArgsConstructor
@EqualsAndHashCode
@Getter
@ToString
public class DefaultScoredMove implements ScoredMove<DefaultScoredMove> {

    private final Move move;
    private final long score;

    @Override
    public DefaultScoredMove continueAhead() {
        return new DefaultScoredMove(move.perform(), toAheadScore(score));
    }

    @Override
    public DefaultScoredMove rotate(UnaryOperator<Direction> rotation) {
        return new DefaultScoredMove(move.perform(rotation), toRotateScore(score));
    }
}
