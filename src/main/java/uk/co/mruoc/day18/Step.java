package uk.co.mruoc.day18;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import uk.co.mruoc.Point;

@RequiredArgsConstructor
@Getter
public class Step {

    private final Point location;
    private final int count;
}
