package uk.co.mruoc.day16;

import java.util.function.UnaryOperator;
import uk.co.mruoc.Direction;
import uk.co.mruoc.Point;

public interface ScoredMove<T extends ScoredMove<T>> {
    Move getMove();

    T continueAhead();

    T rotate(UnaryOperator<Direction> rotation);

    long getScore();

    default Point getLocation() {
        return getMove().location;
    }
}
