package uk.co.mruoc.day16;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import uk.co.mruoc.Direction;
import uk.co.mruoc.Point;

@RequiredArgsConstructor
@EqualsAndHashCode
public class ScoredMove {
    final Move move;
    final long score;

    public ScoredMove(Point point, Direction direction, long score) {
        this(new Move(point, direction), score);
    }
}
