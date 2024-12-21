package uk.co.mruoc.day15;

import java.util.Collection;
import java.util.Deque;
import java.util.LinkedList;
import lombok.RequiredArgsConstructor;
import uk.co.mruoc.Direction;

@RequiredArgsConstructor
public class Directions {

    private final Deque<Direction> values;

    public Directions(Collection<Direction> values) {
        this(new LinkedList<>(values));
    }

    public boolean isEmpty() {
        return values.isEmpty();
    }

    public Direction next() {
        return values.pop();
    }
}
