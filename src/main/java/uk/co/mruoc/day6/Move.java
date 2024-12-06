package uk.co.mruoc.day6;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@Getter
@EqualsAndHashCode
public class Move {

    private final Location previous;
    private final Location next;
    private final char direction;
}
