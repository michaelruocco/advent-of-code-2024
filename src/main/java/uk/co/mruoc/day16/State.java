package uk.co.mruoc.day16;

import java.util.ArrayList;
import java.util.Collection;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import uk.co.mruoc.Direction;
import uk.co.mruoc.Point;

@RequiredArgsConstructor
@Builder
@Getter
public class State {

    private final Move move;
    private final int score;
    private final Collection<Point> path;

    public State(Point location, Direction direction, int score) {
        this(new Move(location, direction), score);
    }

    public State(Point location, Direction direction, int score, Collection<Point> path) {
        this(new Move(location, direction), score, path);
    }

    public State(Move move, int score) {
        this(move, score, new ArrayList<>());
    }
}
