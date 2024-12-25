package uk.co.mruoc.day16;

import java.util.function.UnaryOperator;
import uk.co.mruoc.Direction;
import uk.co.mruoc.Point;

public interface ScoredMove {
    Move getMove();

    ScoredMove continueAhead();

    ScoredMove rotate(UnaryOperator<Direction> rotation);

    Point getLocation();

    long getScore();
}
