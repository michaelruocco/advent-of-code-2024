package uk.co.mruoc.day10;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class Point {
    final int y;
    final int x;

    public String key() {
        return String.format("%s-%s", y, x);
    }
}
