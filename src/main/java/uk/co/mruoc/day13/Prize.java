package uk.co.mruoc.day13;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EqualsAndHashCode
public class Prize {
    final long x;
    final long y;

    public Prize toPart2() {
        return addToAll(10000000000000L);
    }

    public Prize addToAll(long value) {
        return new Prize(x + value, y + value);
    }
}
