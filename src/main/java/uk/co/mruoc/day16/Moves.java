package uk.co.mruoc.day16;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import uk.co.mruoc.Direction;
import uk.co.mruoc.Point;

@RequiredArgsConstructor
public class Moves {

    private final Collection<Move> values;

    public Moves() {
        this(new ArrayList<>());
    }

    public Moves(Move move) {
        this();
        add(move);
    }

    public void add(Move move) {
        this.values.add(move);
    }

    public Optional<Direction> getDirectionAt(Point point) {
        return values.stream()
                .filter(move -> move.location.equals(point))
                .map(move -> move.direction)
                .findFirst();
    }
}
