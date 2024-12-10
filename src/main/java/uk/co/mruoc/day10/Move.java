package uk.co.mruoc.day10;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Move {
    final Point from;
    final Point to;
    final Direction direction;

    public String key() {
        return String.format("%s-%s-%s", from.key(), to.key(), direction);
    }
}
