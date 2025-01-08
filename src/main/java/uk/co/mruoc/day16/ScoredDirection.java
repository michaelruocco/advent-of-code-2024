package uk.co.mruoc.day16;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import uk.co.mruoc.Direction;

@RequiredArgsConstructor
@Getter
public class ScoredDirection {

    private final Direction direction;
    private final int score;
}
