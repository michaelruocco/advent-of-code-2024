package uk.co.mruoc.day16;

import java.util.function.UnaryOperator;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import uk.co.mruoc.Direction;
import uk.co.mruoc.Point;

@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class Move {
    final Point location;
    final Direction direction;

    public Move perform() {
        return new Move(direction.move(location), direction);
    }

    public Move perform(UnaryOperator<Direction> rotation) {
        return new Move(location, rotation.apply(direction));
    }
}
