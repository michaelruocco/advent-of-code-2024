package uk.co.mruoc.day16;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import uk.co.mruoc.Direction;
import uk.co.mruoc.Point;

@RequiredArgsConstructor
@EqualsAndHashCode
@Getter
public class Move {
    private final Point location;
    private final Direction direction;
}
