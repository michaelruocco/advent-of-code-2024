package uk.co.mruoc.day6;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Move {

    private final Location previous;
    private final Location next;
    private final char direction;

    public Location getPrevious() {
        return previous;
    }

    public Location getNext() {
        return next;
    }

    public char getDirection() {
        return direction;
    }
}
